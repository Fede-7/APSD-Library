package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{
  
  default boolean InsertIfAbsent(Data dat){
    return (!this.IsEmpty() && !this.Exists(dat)) ? this.Insert(dat) : false;
  }

  default void RemoveOccurrences(Data dat){
    if(this.IsEmpty()) return;
    this.Filter(elm -> elm.equals(dat));
  }
  
//TODO: controllore se questo metodo è corretto
  @SuppressWarnings("unchecked")
  default Chain<Data> SubChain(Natural start, Natural finish) {
    
    Chain<Data> subChain;
    try {
      subChain = (Chain<Data>) this.getClass().getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Failed to create subchain instance", e);
    }

    long idx = 0;
    long str = ExcIfOutOfBound(start);
    long fns = ExcIfOutOfBound(finish);

    for (; str <= fns; str++, idx++) {
      subChain.Insert(this.GetAt(Natural.Of(idx)));
    }

    return subChain;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat){
    
  }

}
