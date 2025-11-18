package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;
import zapsdtest.testframework.containers.base.ResizableContainerTest;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data>{

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */
  //TODO capire se devo solo inserire i valori o creare spazio e poi inserire
  @Override
  default void InsertAt(Data elem, Natural pos){
    if(!IsEmpty()){
      if(Size().compareTo(pos) > 0){
        if(GetAt(pos) == null){
          SetAt(elem, pos);
        }else{
          if()
         Grow(); 
        }

      }
    }
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  Data AtNRemove(Natural pos);

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  @Override
  default void ShiftLeft(Natural pos, Natural num) {
    // TODO Auto-generated method stub
  }

  @Override
  default void ShiftRight(Natural pos, Natural num) {
      // TODO Auto-generated method stub
  }

  @Override
  Vector<Data> SubVector(Natural start, Natural finish);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
