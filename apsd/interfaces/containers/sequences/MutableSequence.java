package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {

  default void SetAt(Data elem, Natural pos){
    long idx = ExcIfOutOfBound(pos); 
    final MutableForwardIterator<Data> it = this.FMutIterator();
    it.Next(idx);
    it.SetCurrent(elem);    
  }

  default Data GetNSetAt(Data elem, Natural pos){
    Data dat = this.GetAt(pos);
    SetAt(elem, pos);
    return dat;
  }

  default void SetFirst(Data dat){ SetAt(dat, Natural.ZERO);}

  default Data GetNSetFirst(Data elem){return GetNSetAt(elem, Natural.ZERO);}

  default void SetLast(Data elem){SetAt(elem, Size().Decrement());}

  default Data GetNSetLast(Data elem){return GetNSetAt(elem, Size().Decrement()) ;}

  default void Swap(Natural pos1, Natural pos2) {
    Data elemTemp = GetNSetAt(GetAt(pos2), pos1); 
    SetAt(elemTemp, pos2);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  MutableSequence<Data> SubSequence(Natural pos1, Natural pos2);

}