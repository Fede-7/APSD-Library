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
    if (elem == null || pos == null || !IsInBound(pos)) return;
    Grow();
    if (GetAt(pos) != null) {ShiftRight(pos);}
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
  default DynVector<Data> SubVector(Natural start, Natural end){
    if (!IsInBound(start) || !IsInBound(end) || start.compareTo(end) > 0) return null;
    return (DynVector<Data>) SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
