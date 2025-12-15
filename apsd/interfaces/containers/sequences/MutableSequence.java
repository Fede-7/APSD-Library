package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {

  default void SetAt(Data elem, Natural pos){
    if (IsEmpty()) throw new IndexOutOfBoundsException("Index out of bounds");
    long idx = ExcIfOutOfBound(pos);
    final MutableForwardIterator<Data> it = this.FIterator();
    it.Next(idx);
    it.SetCurrent(elem);
  }

  default Data GetNSetAt(Data elem, Natural pos){
    if (IsEmpty()) throw new IndexOutOfBoundsException("Index out of bounds");
    Data dat = this.GetAt(pos);
    SetAt(elem, pos);
    return dat;
  }

  default void SetFirst(Data dat){ SetAt(dat, Natural.ZERO);}

  default Data GetNSetFirst(Data elem){return GetNSetAt(elem, Natural.ZERO);}

  default void SetLast(Data elem){ 
    if (IsEmpty()) throw new IndexOutOfBoundsException("Index out of bounds");
    SetAt(elem, Size().Decrement()); 
  }

  default Data GetNSetLast(Data elem){ 
    if (IsEmpty()) throw new IndexOutOfBoundsException("Index out of bounds");
    return GetNSetAt(elem, Size().Decrement());
  }

  default void Swap(Natural pos1, Natural pos2) {
    if(!IsInBound(pos1) || !IsInBound(pos2)) throw new IndexOutOfBoundsException("out of bound");
    Data temp = GetAt(pos1);
    SetAt(GetAt(pos2), pos1);
    SetAt(temp, pos2);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  MutableSequence<Data> SubSequence(Natural from, Natural to);

}