package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> {

  Data Head();
  void Dequeue();
  void Enqueue(Data element);

  default Data HeadNDequeue(){
    final Data element = this.Head();
    this.Dequeue();
    return element;
  }

  

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear() {
    while (this.Size().ToLong() > 0) {
      this.Dequeue();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data element) {
    if (element == null) return false;
    this.Enqueue(element);
    return true;
  }

}
