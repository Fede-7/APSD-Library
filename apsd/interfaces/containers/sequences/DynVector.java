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
      // TODO Auto-generated method stub  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural pos) {
    // TODO Auto-generated method stub
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  @Override
  default Vector<Data> SubVector(Natural start, Natural finish) {
    // TODO Auto-generated method stub
  }

  @Override
  default void @Override
  default void ShiftLeft(Natural pos, Natural num) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ShiftLeft'");
  }

  Shift

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

}
