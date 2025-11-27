package apsd.interfaces.containers.collections;

public interface OrderedSet<Data extends Comparable<Data>> extends Set<Data> {

  default Data Min() {return (this.IsEmpty()) ? null : FIterator().GetCurrent();}

  default void RemoveMin(){if(!this.IsEmpty()) Remove(Min());}

  default Data MinNRemove(){
    if(this.IsEmpty())return null;

    Data min = this.Min();
    RemoveMin();
    return min;
  }

  default Data Max(){return (this.IsEmpty()) ? null : BIterator().GetCurrent();}

  default void RemoveMax(){if(!this.IsEmpty()) Remove(Max());}

  default Data MaxNRemove(){
    if(this.IsEmpty()) return null;

    Data max = Max();
    RemoveMax();
    return max;
  }

  default Data Predecessor(Data dat){
    if(this.IsEmpty() && dat != null)return null;

    return this.FoldForward((num, acc) -> (num.compareTo(dat) < 0) ? num : acc, null);
  }

  default void RemovePredecessor(Data dat){if(!IsEmpty())this.Remove(Predecessor(dat));}

  default Data PredecessorNRemove(Data dat){
    if (this.IsEmpty()) return null;

    Data pred = Predecessor(dat);
    this.RemovePredecessor(dat);
    return pred;
  }

  default Data Successor(Data dat){
    if(this.IsEmpty() && dat != null)return null;

    return this.FoldBackward((num, acc) -> (num.compareTo(dat) > 0) ? num : acc, null);
  }

  default void RemoveSuccessor(Data dat){if(!IsEmpty())this.Remove(Successor(dat));}

  default Data SuccessorNRemove(Data dat){
    if(this.IsEmpty())return null;

    Data succ = Successor(dat);
    this.RemoveSuccessor(dat);
    return succ;
  }

}
