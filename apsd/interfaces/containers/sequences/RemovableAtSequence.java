package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data> {

  // default void RemoveAt(Natural pos){
  //   long[] idx = new long[1];
  //   idx[0] = ExcIfOutOfBound(pos);
  //   this.TraverseForward(dat -> {
  //     if(idx[0] == 0){
  //       dat = null;
  //       return true;
  //     }
  //     idx[0]--;
  //     return false;
  //   });
  // }

  // Data AtNRemove(Natural pos);
  
  void RemoveAt(Natural pos);

  default Data AtNRemove(Natural pos) {
    if (!IsEmpty() && IsInBound(pos)) {
      GetAt(pos);
      RemoveAt(pos);
    }
    return null;
  }

  default void RemoveFirst(){if(!IsEmpty()) RemoveAt(Natural.ZERO);};
  default Data FirstNRemove(){return AtNRemove(Natural.ZERO);};

  default void RemoveLast(){if(!IsEmpty()) RemoveAt(Size().Decrement());};
  default Data LastNRemove(){return AtNRemove(Size().Decrement());};

}
