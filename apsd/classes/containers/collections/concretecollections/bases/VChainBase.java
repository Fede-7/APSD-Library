package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<Data> implements Chain<Data> {

  protected final DynVector<Data> vec;

  public VChainBase(){ vec = new DynCircularVector<>();}

  protected VChainBase(DynVector<Data> vec){ this.vec = vec;}

  public VChainBase(TraversableContainer<Data> con){ vec = new DynCircularVector<>(con);}

  abstract protected VChainBase<Data> NewChain(DynVector<Data> vec);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() { return vec.Size();}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() { vec.Clear();}

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    Natural idx = vec.Search(dat);
    if (idx == null) return false;
    vec.AtNRemove(idx);
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  public ForwardIterator<Data> FIterator() { return vec.FIterator();}

  @Override
  public BackwardIterator<Data> BIterator() { return vec.BIterator();}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural pos) { return vec.GetAt(pos);  }

  @Override
  public Sequence<Data> SubSequence(Natural start, Natural end) { return NewChain((DynVector<Data>) vec.SubSequence(start, end));}

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  public Data AtNRemove(Natural pos) {
    if (pos == null) throw new NullPointerException("Position cannot be null");
    if (vec.IsEmpty()) throw new IndexOutOfBoundsException("out of bound");
    long idx = pos.ToLong();
    if (idx < 0 || idx >= vec.Size().ToLong()) throw new IndexOutOfBoundsException("out of bound");
    return vec.AtNRemove(pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {
    long del = 0;
    if (fun != null) {
      MutableForwardIterator<Data> wrt = vec.FIterator();
      for (; wrt.IsValid(); wrt.Next()) {
        Data currentData = wrt.GetCurrent();
        if (!fun.Apply(currentData)) {
          del++;
          wrt.SetCurrent(null);
        }
      }
      if (del > 0) {
        wrt.Reset();
        MutableForwardIterator<Data> rdr = vec.FIterator();
        for (; rdr.IsValid(); rdr.Next()) {
          Data dat = rdr.GetCurrent();
          if (dat != null) {
            rdr.SetCurrent(null);
            wrt.SetCurrent(dat);
            wrt.Next();
          }
        }
        vec.Reduce(Natural.Of(del));
      }
    }
    return (del > 0);
  }

}
