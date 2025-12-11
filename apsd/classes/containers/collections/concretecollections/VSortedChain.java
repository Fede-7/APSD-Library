package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<Data>> extends VChainBase<Data> implements SortedChain<Data> {

  public VSortedChain(){super();}

  public VSortedChain(VSortedChain<Data> chn){super(chn.vec);}

  public VSortedChain(TraversableContainer<Data> con){super(con);}

  protected VSortedChain(DynVector<Data> vec){super(vec);}

  public VSortedChain<Data> NewChain(VSortedChain<Data> chn){ return new VSortedChain<Data>(chn);}

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data data) {
    if (data == null || this.vec == null) return false;
    Natural idx = SearchSuccessor(data);
    idx = ( idx == null) ? Size().Decrement() : idx;
    ShiftRight(idx);
    InsertAt(data, idx);
    return true;  
  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */
/*
*1. if dat = null:   Search::dat return null, than insert check and return false
*2. if dat == Data:  Search::dat return null if its isnt presente, the idx if it's present.
*/
  @Override
  public boolean InsertIfAbsent(Data dat) { return (Search(dat) == null) ? Insert(dat) : false;}

  @Override
  public void RemoveOccurrences(Data dat) {
    if(dat == null || this.vec == null) return;
    Filter(elm -> dat.equals(elm));
 }

  //TODO: metodi aggiunti per l'errore della classe
  @Override
  public Natural Capacity() { return vec.Capacity(); }

  @Override
  public void Expand(Natural size) { vec.Expand(size); }

  @Override
  public void Reduce(Natural size) { vec.Reduce(size); }

  @Override
  public void Realloc(Natural size) { vec.Realloc(size); }

  @Override
  protected VSortedChain<Data> NewChain(DynVector<Data> v) { return new VSortedChain<>(v); }

  @Override
  public MutableForwardIterator<Data> FMutIterator() { return vec.FMutIterator(); }

  @Override
  public MutableBackwardIterator<Data> BMutIterator() { return vec.BMutIterator(); }

}
