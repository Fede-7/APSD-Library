package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> {

  Data Top();
  void Pop();
  void Push(Data element);

  default Data TopNPop(){
    final Data element = this.Top();
    this.Pop();
    return element;
  }

  default void SwapTop(Data element){
    this.Pop();
    this.Push(element);
  }

  default Data TopNSwap(Data element){
    final Data topE = this.Top();
    this.Pop();
    this.Push(element);
    return topE;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear(){
    while(this.Size().ToLong() > 0){
      this.Pop();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data element) {
    if (element == null) return false;
    this.Push(element);
    return true;
  }

}
