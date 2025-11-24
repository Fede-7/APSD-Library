package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{
  
  default boolean InsertIfAbsent(Data dat){ return (!this.Exists(dat)) ? this.Insert(dat) : false;}

  default void RemoveOccurrences(Data dat){
    if(IsEmpty() || dat == null) return;
    Filter(elm -> !elm.equals(dat));
  }
  
  default Chain<Data> SubChain(Natural start, Natural end) {
    if (!IsInBound(start) || !IsInBound(end) || start.compareTo(end) > 0) return null;
    return (Chain<Data>) SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat){
    if(this.IsEmpty() || dat == null)return null;
    long idx = 0;
    while(idx < Size().ToLong() && !this.GetAt(Natural.Of(idx)).equals(dat)){idx++;}
    return (idx < Size().ToLong()) ? Natural.Of(idx) : null;
  }

}
