package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.containers.sequences.DynVector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Sequence;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data>{

  public LLList(){ super();}

  public LLList(TraversableContainer<Data> con){ super(con);}

  protected LLList(long size, LLNode<Data> head, LLNode<Data> tail){super(size, head, tail);}

  protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail){ return new LLList<>(size, head, tail);}

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */
  
  @Override
  public MutableForwardIterator<Data> FIterator() { return FIterator(); }
  
  @Override
  public MutableBackwardIterator<Data> BIterator() { return BIterator();}


  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data elem, Natural pos) {
    if (elem == null) throw new IllegalArgumentException("Element to set cannot be null.");
    if (!IsInBound(pos)) throw new IllegalArgumentException("Position " + pos + " is out of bounds.");
   
    MutableForwardIterator<Data> it = FIterator();
    it.Next(pos);
    it.SetCurrent(elem);
  }

  @Override
  public void SetFirst(Data dat) { SetAt(dat, Natural.ZERO);}

  @Override
  public void SetLast(Data elem) { SetAt(elem, Size().Decrement()); }

  @Override
  public MutableSequence<Data> SubSequence(Natural startindex, Natural endindex) {
    if (startindex.ToLong() < 0 || endindex.ToLong() > size.ToLong() || startindex.ToLong() > endindex.ToLong()) { throw new IndexOutOfBoundsException("Invalid start or end index"); }

    LLList<Data> subList = new LLList<Data>();
    LLNode<Data> cur = headref.Get();
    long index = 0;

    while (cur != null && index < endindex.ToLong()) {
      if (index >= startindex.ToLong()) { subList.InsertLast(cur.Get());}
      cur = cur.GetNext().Get();
      index++;
    }
    
    return subList;
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  public void InsertAt(Data dat, Natural pos) {
    long  idx = ExcIfOutOfBound(pos);

    if (idx == 0) {
      InsertFirst(dat);
    } else if (idx == size.ToLong()) {
      InsertLast(dat);
    } else {
      LLNode<Data> node = headref.Get();
      long i = 0;
      while (i < idx - 1) {
        node = node.GetNext().Get();
        i++;
      }
      if(node == null) throw new IllegalStateException("Internal list structure corrupted");
      
      LLNode<Data> newNode = new LLNode<Data>(dat);
      newNode.SetNext(node.GetNext().Get());
      node.SetNext(newNode);
      size.Increment();
    }
  }

  @Override
  public void InsertFirst(Data data) {
    LLNode<Data> newNode = new LLNode<>(data);
    newNode.SetNext(headref.Get());
    headref.Set(newNode);
    if (tailref.Get() == null) { tailref.Set(newNode);}
    size.Increment();
  }

  @Override
  public void InsertLast(Data data) {
    LLNode<Data> newNode = new LLNode<Data>(data);
    if (tailref.Get() == null) {
      headref.Set(newNode);
      tailref.Set(newNode);
    } else {
      tailref.Get().SetNext(newNode);
      tailref.Set(newNode);
    }
    size.Increment();
  }

}
