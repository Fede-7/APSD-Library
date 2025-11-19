package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;
import zapsdtest.testframework.containers.base.ResizableContainerTest;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data>{

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  default void InsertAt(Data elem, Natural pos) {
    if (!IsEmpty() && IsInBound(pos)) {
      if(GetAt(pos)!= null){
        Grow();
        ShiftRight(pos);
      }
      SetAt(elem, pos);
    }
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos){
    if (!IsEmpty() && IsInBound(pos)) {
      Data dat = GetAt(pos);
      ShiftLeft(pos);
      Shrink();
      return dat;
    }
    return null;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  @Override
  default void ShiftLeft(Natural pos, Natural num) {
    // TODO che logica devo usare?
  }

  @Override
  default void ShiftRight(Natural pos, Natural num) {
    // TODO che logica devo usare? 
  }

  @Override
  Vector<Data> SubVector(Natural start, Natural finish);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
