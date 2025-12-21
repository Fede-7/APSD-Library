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
    if (elem == null) return;
    if (pos.compareTo(Size()) > 0) throw new IndexOutOfBoundsException("out of bound");
    if (pos.compareTo(Size()) == 0) Expand(Natural.ONE);
    else ShiftRight(pos);

    SetAt(elem, pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos){
    if (IsEmpty() || !IsInBound(pos)) return null;
    Data dat = GetAt(pos);
    ShiftLeft(pos);
    return dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @Override
  default void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = Math.min(num.ToLong(), size - idx);
    if (len <= 0) return;

    Natural natLen = Natural.Of(len);
    Vector.super.ShiftLeft(pos, natLen);
    Reduce(natLen);
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
