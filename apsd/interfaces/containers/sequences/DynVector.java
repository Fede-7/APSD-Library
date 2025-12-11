package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data>{

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  //TODO: usare o no IsInBound?
  @Override
  default void InsertAt(Data elem, Natural pos) {
    if (elem == null || pos == null) return;

    if (pos.compareTo(Size()) >= 0) {
      Realloc(Natural.Of(pos.ToLong() + 1));
    } else {
      Grow(pos);
    }

    if (GetAt(pos) != null) { ShiftRight(pos);}
    SetAt(elem, pos);
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos){
    if (pos == null || IsEmpty() || !IsInBound(pos)) return null;
    Data dat = GetAt(pos);
    if(!pos.equals(Size().Decrement())) ShiftLeft(pos);
    Shrink();
    return dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  @Override
  default void ShiftLeft(Natural pos, Natural num) {
      Vector.super.ShiftLeft(pos, num);
      Shrink();
    }

  @Override
  default void ShiftRight(Natural pos, Natural num) {
    Grow(num);
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
