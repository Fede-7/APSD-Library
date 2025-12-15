package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.OrderedSet;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<? super Data>, Chn extends SortedChain<Data>> extends WSetBase<Data, Chn> implements OrderedSet<Data> {

  protected WOrderedSetBase() { super(); }

  protected WOrderedSetBase(Chn chn) { super(chn); }

  protected WOrderedSetBase(TraversableContainer<Data> con) { super(con); }

  protected WOrderedSetBase(Chn chn, TraversableContainer<Data> con) { super(chn, con); }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  public boolean IsEqual(IterableContainer<Data> con) {
    if (con == null || !this.Size().equals(con.Size())) return false;
    return !con.TraverseForward(elm -> !this.Exists(elm));
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  @Override
  public Data Min() {
    return this.IsEmpty() ? null : chn.Min();
  }

  @Override
  public Data Max() {
    return this.IsEmpty() ? null : chn.Max();
  }

  @Override
  public void RemoveMax() {
    if (!this.IsEmpty()) chn.RemoveMax();
  }

  @Override
  public void RemoveMin() {
    if (!this.IsEmpty()) chn.RemoveMin();
  }

  @Override
  public Data Predecessor(Data dat) {
    return chn.Predecessor(dat);
  }

  @Override
  public Data Successor(Data dat) {
    return chn.Successor(dat);
  }

  @Override
  public void RemovePredecessor(Data dat) {
    chn.RemovePredecessor(dat);
  }

  @Override
  public void RemoveSuccessor(Data dat) {
    chn.RemoveSuccessor(dat);
  }

  @Override
  public Data PredecessorNRemove(Data dat) {
    return chn.PredecessorNRemove(dat);
  }

  @Override
  public Data SuccessorNRemove(Data dat) {
    return chn.SuccessorNRemove(dat);
  }
}
