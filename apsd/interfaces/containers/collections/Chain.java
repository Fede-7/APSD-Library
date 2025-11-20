package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{
  
  default boolean InsertIfAbsent(Data dat){ return (!this.Exists(dat)) ? this.Insert(dat) : false;}

  default void RemoveOccurrences(Data dat){
    if(this.IsEmpty()) return;
    this.Filter(elm -> !elm.equals(dat));
  }
  
//TODO: controllore SubChain
  @SuppressWarnings("unchecked")
  default Chain<Data> SubChain(Natural start, Natural finish) {
    
    Chain<Data> subChain;
    try {
      subChain = (Chain<Data>) this.getClass().getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Failed to create subchain instance", e);
    }

    long str = ExcIfOutOfBound(start);
    long fns = ExcIfOutOfBound(finish);

    for (; str <= fns; str++) {
      subChain.Insert(this.GetAt(Natural.Of(str)));
    }

    return subChain;
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
