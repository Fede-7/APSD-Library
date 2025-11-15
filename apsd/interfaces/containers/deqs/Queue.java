package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> {

  Data head();
  void dequeue();
  void enqueue(Data element);

  default Data headNDequeue(){
    final Data element = this.head();
    this.dequeue();
    return element;
  }

  

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear() {
    while (this.Size().ToLong() > 0) {
      this.dequeue();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data element) {
    this.enqueue(element);
    return true;
  }

}
