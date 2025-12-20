package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> {

  public VSortedChain(){super();}

  public VSortedChain(VSortedChain<Data> chn){super(chn.vec);}

  public VSortedChain(TraversableContainer<Data> con){super(con);}

  protected VSortedChain(DynVector<Data> vec){super(vec);}

  @Override
  protected VChainBase<Data> NewChain(DynVector<Data> vec){ return new VSortedChain<Data>(vec);}

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data data) {
    if (data == null) return false;
    Natural idx = SearchSuccessor(data);
    if (idx == null) idx = Size();
    vec.InsertAt(data, idx);
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
    if(dat == null) return;
    Filter(elm -> !dat.equals(elm));
 }

}
