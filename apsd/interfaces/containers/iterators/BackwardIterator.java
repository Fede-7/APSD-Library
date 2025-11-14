package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> {
  
  void Prev(){DataNPrev();}

  void Prev(Natural steps){ Prev(steps.ToLong()); }

  void Prev(long steps){
    for (; steps > 0 && IsValid(); --steps ; Prev()) {}
  }

  Data DataNPrev(){
    Data current = GetCurrent();
    Prev();
    return current;
  }

  default boolean ForEachBackward(Predicate<Data> fun) {
    if (fun != null) {
      while (IsValid()) {
        if (fun.Apply(DataNPrev())) { return true; }
      }
    }
    return false;
  }
}
