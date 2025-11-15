package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data> {

  ForwardIterator<Data> FIterator();
  BackwardIterator<Data> BIterator();

  @Override
  default boolean IsEqual(IterableContainer<Data> con){
    if(con == null || !this.Size().IsEqual(con.Size())) {return false;}
    final ForwardIterator<Data> it1 = this.FIterator();
    final ForwardIterator<Data> it2 = con.FIterator();
    while(it1.IsValid()) {
      if(!it1.DataNNext().equals(it2.DataNNext())) {return false;}
    }
    return true;
  }

  
  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> pred) {
    if (pred != null) {
      final ForwardIterator<Data> it = this.FIterator();
      while (it.IsValid()) { if (pred.Apply(it.DataNNext()))return true; }
    }
    return false;
  }

  @Override
  default boolean TraverseBackward(Predicate<Data> pred) {
    if (pred != null) {
      final BackwardIterator<Data> it = this.BIterator();
      while (it.IsValid()) { if (pred.Apply(it.DataNPrev()))return true; }
      return false;
    }
  }

}
