package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data> {

  default void RemoveAt(Natural pos){
    if(IsEmpty()) return;
    if(IsInBound(pos)) AtNRemove(pos);
  }

  Data AtNRemove(Natural pos);

  default void RemoveFirst(){if(!IsEmpty()) RemoveAt(Natural.ZERO);};
  default Data FirstNRemove(){ return IsEmpty() ? null : AtNRemove(Natural.ZERO);}

  default void RemoveLast(){if(!IsEmpty()) RemoveAt(Size().Decrement());};
  default Data LastNRemove(){ return IsEmpty() ? null : AtNRemove(Size().Decrement());}

}
