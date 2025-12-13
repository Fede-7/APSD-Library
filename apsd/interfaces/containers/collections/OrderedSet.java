package apsd.interfaces.containers.collections;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data> {

  default Data Min() {return (IsEmpty()) ? null : FIterator().GetCurrent();}

  default void RemoveMin(){if(!IsEmpty()) Remove(Min());}

  default Data MinNRemove(){
    if(IsEmpty())return null;

    Data min = Min();
    RemoveMin();
    return min;
  }

  default Data Max(){return (IsEmpty()) ? null : BIterator().GetCurrent();}

  default void RemoveMax(){if(!IsEmpty()) Remove(Max());}

  default Data MaxNRemove(){
    if(IsEmpty()) return null;

    Data max = Max();
    RemoveMax();
    return max;
  }

  default Data Predecessor(Data dat){
    if(IsEmpty() || dat == null) return null;

    return FoldForward((num, acc) -> (num != null && num.compareTo(dat) < 0) ? num : acc, null);
  }

  default void RemovePredecessor(Data dat){if(!IsEmpty())Remove(Predecessor(dat));}

  default Data PredecessorNRemove(Data dat){
    if (IsEmpty()) return null;

    Data pred = Predecessor(dat);
    RemovePredecessor(dat);
    return pred;
  }

  default Data Successor(Data dat){
    if(IsEmpty() || dat == null) return null;

    return FoldBackward((num, acc) -> (num != null && num.compareTo(dat) > 0) ? num : acc, null);
  }

  default void RemoveSuccessor(Data dat){if(!IsEmpty())Remove(Successor(dat));}

  default Data SuccessorNRemove(Data dat){
    if(IsEmpty() || dat == null) return null;

    Data succ = Successor(dat);
    RemoveSuccessor(dat);
    return succ;
  }

}
