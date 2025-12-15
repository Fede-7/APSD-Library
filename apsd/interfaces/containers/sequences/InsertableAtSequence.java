package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<Data> extends Sequence<Data>{ 

  void InsertAt(Data elem, Natural pos);

  default void InsertFirst(Data elem){ if(elem != null) InsertAt(elem, Natural.ZERO); }

  default void InsertLast(Data elem){ 
    if (elem != null) {
      InsertAt(elem, Natural.Of(Size().ToLong()));
    }
  }

}