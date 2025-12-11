package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on (dynamic circular) vector. */
public class VList<Data>  extends VChainBase<Data> implements List<Data>{

  public VList(){super();}

  public VList(TraversableContainer<Data> con){super(con);}

  protected VList(DynVector<Data> vec){super(vec);}

  public VChainBase<Data> NewChain(DynVector<Data> vec){ return new VList<>(vec);}

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  @Override
  public MutableForwardIterator<Data> FIterator() { return vec.FMutIterator();}
  
  @Override
  public MutableBackwardIterator<Data> BIterator() { return vec.BMutIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data elem, Natural pos) { vec.SetAt(elem, pos);}


  //TODO: here he want mutablesequenz - aro cazz o miett?
  @Override
  public VChainBase<Data> SubSequence(Natural start, Natural end) {
    if(!IsInBound(start) || !IsInBound(end)) return null;
    return NewChain((DynVector<Data>) vec.SubSequence(start, end));
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  public void InsertAt(Data elem, Natural pos) { vec.InsertAt(elem, pos);}

}
