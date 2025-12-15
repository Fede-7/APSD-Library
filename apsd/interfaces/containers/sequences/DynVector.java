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
    if (pos == null) throw new NullPointerException("Position cannot be null");
    if (pos.compareTo(Size()) > 0) throw new IndexOutOfBoundsException("out of bound");

    long idx = pos.ToLong();
    long curSize = Size().ToLong();

    if (idx == curSize) Expand(Natural.ONE);
    else ShiftRight(pos);

    SetAt(elem, pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos){
    if (pos == null || IsEmpty()) return null;
    if (!IsInBound(pos)) return null;

    Data dat = GetAt(pos);
    ShiftLeft(pos); // gestisce anche la rimozione dell'ultimo elemento
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
