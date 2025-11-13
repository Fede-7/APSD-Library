package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data> {

  // FIterator
  ForwardIterator<Data> FIterator();
  // BIterator
  BackwardIterator<Data> BIterator();

  // IsEqual
  @Override
  default boolean IsEqual(IterableContainer<Data> con){
    if(con != null && this.Size().IsEqual(con.Size())){
      final ForwardIterator<Data> it1 = this.FIterator();
      final ForwardIterator<Data> it2 = con.FIterator();
      while(!it1.DataNNext().equals(it2.DataNNext()) && it1.Next() != null && it2.Next() != null){}
      return !it1.HasNext() && !it2.HasNext();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> pred) {
    final ForwardIterator<Data> it = this.FIterator();
    while (it.HasNext()) {
      if (pred.Apply(it.Next())) return true;
    }
    return false;
  } 

  @Override
  default boolean TraverseBackward(Predicate<Data> pred) {
    final BackwardIterator<Data> it = this.BIterator();
    while (it.HasNext()) {
      if (pred.Apply(it.Next())) return true;
    }
    return false;
  }

}
