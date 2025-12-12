package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;
import java.util.Objects;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> { // Must implement Set; Chn must extend Chain

  protected Chn chn;

  protected WSetBase() { ChainAlloc(); }

  protected WSetBase(Chn chn) {
    if (chn == null) throw new NullPointerException("Chain cannot be null!");
    this.chn = chn;
  }

  protected WSetBase(TraversableContainer<Data> con) {
    if (con == null) throw new NullPointerException("TraversableContainer cannot be null!");
    ChainAlloc();
    con.TraverseForward(dat -> { this.Insert(dat); return false; });
  }

  protected WSetBase(Chn chn, TraversableContainer<Data> con) {
    if (chn == null) throw new NullPointerException("Chain cannot be null!");
    if (con == null) throw new NullPointerException("TraversableContainer cannot be null!");
    this.chn = chn;
    con.TraverseForward(dat -> { this.Insert(dat); return false; });
  }

  // WSetBase

  protected abstract void ChainAlloc(); // ChainAlloc

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() { return chn.Size(); }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() { chn.Clear(); }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {
    if (dat == null) return false;
    if (Exists(dat)) return false;
    return chn.Insert(dat);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) { return chn.Remove(dat); }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  public ForwardIterator<Data> FIterator() { return chn.FIterator(); }

  @Override
  public BackwardIterator<Data> BIterator() { return chn.BIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) { return chn.Filter(fun); }

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  @Override
  public boolean Exists(Data dat) {
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      if (Objects.equals(it.GetCurrent(), dat)) return true;
      it.Next();
    }
    return false;
  }

}
