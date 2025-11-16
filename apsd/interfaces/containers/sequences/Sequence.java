package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data> {

  default Data GetAt(Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    ForwardIterator<Data> ForIt = FIterator();
    ForIt.Next(idx);
    return ForIt.GetCurrent();
  }

  default Data GetFirst(){ return GetAt(Natural.ZERO); }

  default Data GetLast(){return GetAt((IsEmpty()) ? Natural.ZERO : Size().Decrement()) ;}

  default Natural Search(Data elem){
    final Box<Natural> idx = new Box<>(Natural.ZERO);
    this.TraverseForward(dat -> {
      boolean found = (dat == null ? elem == null : dat.equals(elem));
      if (!found) idx.Set(idx.Get().Increment());
      return found;
    });
    return idx.Get();
  }

  default boolean IsInBound(Natural idx){
    return (idx.ToLong() < Size().ToLong());
  }

  default long ExcIfOutOfBound(Natural num) {
    if (num == null) throw new NullPointerException("Natural number cannot be null!");
    long idx = num.ToLong();
    if (idx >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
    return idx;
  }

  Sequence<Data> SubSequence(Natural FirIdx, Natural SecIdx );

}
