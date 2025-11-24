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

  default Data GetFirst(){ return IsEmpty() ? null : GetAt(Natural.ZERO); }

  default Data GetLast(){ return IsEmpty() ? null : GetAt(Size().Decrement()); }

  default Natural Search(Data elem){
    final Box<Natural> idx = new Box<>(Natural.ZERO);
    final Box<Boolean> found =new Box<>();
    
    this.TraverseForward(dat -> {
      found.Set(dat == null ? elem == null : dat.equals(elem));
      if (!found.Get()) idx.Set(idx.Get().Increment());
      return found.Get();
    });
    return found.Get() ? idx.Get() : null;
  }

  default boolean IsInBound(Natural idx) {
    if (idx == null) throw new NullPointerException("Natural number cannot be null!"); 

    if (idx.compareTo(Size()) > 0)
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
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
