package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data>{

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  default void InsertAt(Data elem, Natural pos) {
    if (elem == null || pos == null) return;
    long idx = pos.ToLong();
    long curSize = Size().ToLong();

    if (idx > curSize) {
      Expand(Natural.Of(idx - curSize + 1));
    } else if (idx == curSize) {
      Expand(Natural.ONE);
    } else {
      ShiftRight(pos);
    }
    SetAt(elem, pos);
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos){
    if (pos == null || IsEmpty()) return null;
    long idx = pos.ToLong();
    long sz = Size().ToLong();
    if (idx < 0 || idx >= sz) return null;

    Data dat = GetAt(pos);
    if (idx < sz - 1) {
      ShiftLeft(pos);
    } else {
      SetAt(null, pos);
      Reduce(Natural.ONE);
    }
    return dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  @Override
  default void ShiftLeft(Natural pos, Natural num) {
    Vector.super.ShiftLeft(pos, num);
    Reduce(num);
  }

  @Override
  default void ShiftRight(Natural pos, Natural num) {
    Expand(num);
    Vector.super.ShiftRight(pos, num);
  }

  @Override
  default DynVector<Data> SubVector(Natural from, Natural to){
    if (!IsInBound(from) || !IsInBound(to) || from.compareTo(to) > 0) return null;
    return (DynVector<Data>) Vector.super.SubVector(from, to);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
