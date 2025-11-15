package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> {
  
  default void Prev(){DataNPrev();}

  default void Prev(Natural steps){ Prev(steps.ToLong()); }

  default void Prev(long steps){
    for (; steps > 0 && IsValid(); --steps , Prev()) {}
  }

  Data DataNPrev();

  default boolean ForEachBackward(Predicate<Data> fun) {
    if (fun != null) {
      while (IsValid()) {
        if (fun.Apply(DataNPrev())) { return true; }
      }
    }
    return false;
  }
}
