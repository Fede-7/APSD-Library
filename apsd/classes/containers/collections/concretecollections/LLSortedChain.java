package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data> {

  public LLSortedChain(){super();}

  public LLSortedChain(LLSortedChain<Data> chn){super(chn);}

  public LLSortedChain(TraversableContainer<Data> con){
    super();
    con.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }

  protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail){super(size, head, tail);}
  
  @Override
  protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) { return new LLSortedChain<>(size, head, tail);}

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  protected LLNode<Data> PredFind(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> pred = null;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      Data val = ref.Get().Get();
      if (val.compareTo(dat) < 0) pred = ref.Get();
      else break;
    }
    return pred;
  }

  protected LLNode<Data> PredPredFind(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> predPred = null, pred = null;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      Data val = ref.Get().Get();
      if (val.compareTo(dat) < 0) { predPred = pred; pred = ref.Get(); }
      else break;
    }
    return predPred;
  }

  protected LLNode<Data> SuccFind(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      if (ref.Get().Get().compareTo(dat) > 0) return ref.Get();
    }
    return null;
  }

  protected LLNode<Data> PredSuccFind(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> predSucc = null;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      if (ref.Get().Get().compareTo(dat) > 0) return predSucc;
      predSucc = ref.Get();
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat){
    if(dat == null) return false;
    LLNode<Data> pred = PredFind(dat);
    Box<LLNode<Data>> box = (pred == null) ? headref : pred.GetNext();
    LLNode<Data> newNode = new LLNode<>(dat, box.Get());
    box.Set(newNode);
    if (tailref.Get() == pred) tailref.Set(newNode);
    size.Increment();
    return true;  
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat){
    if(dat == null || headref.IsNull()) return false;
    LLNode<Data> pred = PredFind(dat);
    Box<LLNode<Data>> box = (pred == null) ? headref : pred.GetNext();
    if (box.IsNull() || !box.Get().Get().equals(dat)) return false;
    LLNode<Data> node = box.Get();
    box.Set(node.GetNext().Get());
    if (tailref.Get() == node) tailref.Set(pred);
    size.Decrement();
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

  @Override
  public Natural Search(Data dat) {
    if (dat == null || IsEmpty()) return null;
    long idx = 0L;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      if (ref.Get().Get().equals(dat)) return Natural.Of(idx);
      idx++;
    }
    return null;
  }

  @Override
  public Natural SearchPredecessor(Data dat) {
    if (dat == null || IsEmpty()) return null;
    long idx = 0L;
    Natural pred = null;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      if (ref.Get().Get().compareTo(dat) < 0) pred = Natural.Of(idx);
      else break;
      idx++;
    }
    return pred;
  }

  @Override
  public Natural SearchSuccessor(Data dat) {
    if (dat == null || IsEmpty()) return null;
    long idx = 0L;
    for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
      if (ref.Get().Get().compareTo(dat) > 0) return Natural.Of(idx);
      idx++;
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  @Override
  public Data Predecessor(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> pred = PredFind(dat);
    return (pred == null) ? null : pred.Get();
  }

  @Override
  public Data Successor(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> succ = SuccFind(dat);
    return (succ == null) ? null : succ.Get();
  }

  @Override
  public void RemovePredecessor(Data dat) {
    LLNode<Data> pred = PredFind(dat);
    LLNode<Data> predPred = PredPredFind(dat);
    if (pred == null) return;
    Box<LLNode<Data>> box = (predPred == null) ? headref : predPred.GetNext();
    box.Set(pred.GetNext().Get());
    if (tailref.Get() == pred) tailref.Set(predPred);
    size.Decrement();
  }

  @Override
  public void RemoveSuccessor(Data dat) {
    if (dat == null || headref.IsNull()) return;
    LLNode<Data> succ = SuccFind(dat);
    if (succ == null) return;
    LLNode<Data> predSucc = PredSuccFind(dat);
    Box<LLNode<Data>> box = (predSucc == null) ? headref : predSucc.GetNext();
    box.Set(succ.GetNext().Get());
    if (tailref.Get() == succ) tailref.Set(predSucc);
    size.Decrement();
  }

  @Override
  public Data PredecessorNRemove(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> pred = PredFind(dat);
    if (pred == null) return null;
    Data val = pred.Get();
    RemovePredecessor(dat);
    return val;
  }

  @Override
  public Data SuccessorNRemove(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> succ = SuccFind(dat);
    if (succ == null) return null;
    Data val = succ.Get();
    RemoveSuccessor(dat);
    return val;
  }
}
