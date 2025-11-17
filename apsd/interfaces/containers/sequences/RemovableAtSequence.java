package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data> {

  default void RemoveAt(Natural pos){
    final long idx = ExcIfOutOfBound(pos);
    this.TraverseForward(dat -> {
      if(idx == 0){
        dat = null;
        return true;
      }
      idx = ;
      return false;
    });
  }

  // AtNRemove

  // RemoveFirst
  // FirstNRemove

  // RemoveLast
  // LastNRemove

}
