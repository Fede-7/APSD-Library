package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data>{

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */
  @Override
  default void InsertAt(Data elem, Natural pos){
    if(pos.compareTo(Size()) <= 0){
      Expand();
      ShiftRight(pos);
      SetAt(elem, pos);
    }
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos){
    Data elem = GetAt(pos);
    ShiftLeft(pos);
    return elem;
  }

  @Override
  default void RemoveAt(Natural pos){
    ShiftLeft(pos);
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
  Vector<Data> SubVector(Natural start, Natural finish);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
