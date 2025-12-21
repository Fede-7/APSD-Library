package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> {

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  public LLChainBase(){
    size.Assign(0);
    headref.Set(null);
    tailref.Set(null);
  }

  public LLChainBase(TraversableContainer<Data> con) {
    final Box<Boolean> first = new Box<>(true);
    con.TraverseForward(dat -> {
      if (dat == null) return false;
      LLNode<Data> node = new LLNode<>(dat);
      if (first.Get()) {
        headref.Set(node);
        first.Set(false);
      } else { tailref.Get().SetNext(node); }
      tailref.Set(node);
      size.Increment();
      return false;
    });
  }

  public LLChainBase(long size, LLNode<Data> headref, LLNode<Data> tailref){
    this.size.Assign(size);
    this.headref.Set(headref);
    this.tailref.Set(tailref);
  }

  abstract protected LLChainBase<Data> NewChain(long size, LLNode<Data> headref, LLNode<Data> tailref);

  protected class ListFRefIterator implements ForwardIterator<Box<LLNode<Data>>> {
    protected Box<LLNode<Data>> cur;

    public ListFRefIterator() { cur = headref;}

    public ListFRefIterator(ListFRefIterator itr) { cur = itr.cur;}

    @Override
    public Box<LLNode<Data>> DataNNext() {
      if (!IsValid()) {
        throw new IllegalStateException("Iterator is not valid.");
      }
      Box<LLNode<Data>> oldCur = cur;
      cur = cur.Get().GetNext();
      return oldCur;
    }

    @Override
    public boolean IsValid() {return !cur.IsNull(); }

    @Override
    public void Reset() { cur = headref; }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid())
        throw new IllegalStateException("Iterator is not valid.");
      return cur;
    }

    @Override
    public void Next() {
      if (!IsValid())
        throw new IllegalStateException("Iterator is not valid.");
      cur = cur.Get().GetNext();
    }
  }

  protected class ListBRefIterator implements BackwardIterator<Box<LLNode<Data>>> {
    protected long cur = -1L;
    protected Vector<Box<LLNode<Data>>> arr = null;

    public ListBRefIterator() { Reset(); }

    public ListBRefIterator(ListBRefIterator itr) {
      cur = itr.cur;
      if (itr.arr != null) {
        arr = new Vector<>(itr.arr);
      }
    }

    @Override
    public boolean IsValid() { return (cur >= 0L && cur < size.ToLong()); }

    @Override
    public void Reset() {
      if (Size().IsZero()) {
        arr = null;
        cur = -1L;
      } else {
        arr = new Vector<>(Size());
        long index = 0L;
        for (Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()) {
          arr.SetAt(ref, Natural.Of(index));
          index++;
        }
        cur = size.ToLong() - 1L;
      }
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator is not valid.");
      return arr.GetAt(Natural.Of(cur));
    }

    @Override
    public Box<LLNode<Data>> DataNPrev() {
      if (!IsValid()) throw new IllegalStateException("Iterator is not valid.");
      Box<LLNode<Data>> result = arr.GetAt(Natural.Of(cur));
      cur--;
      return result;
    }

    @Override
    public void Prev() {
      if (!IsValid())
        throw new IllegalStateException("Iterator is not valid.");
      cur--;
    }
  }
  
  
  protected ForwardIterator<Box<LLNode<Data>>> FRefIterator() { return new ListFRefIterator(); }
  protected BackwardIterator<Box<LLNode<Data>>> BRefIterator() { return new ListBRefIterator(); }

  /* ************************************************************************ */
  /* Specific member functions from LLChainBase                               */
  /* ************************************************************************ */

  protected class ListFIterator implements MutableForwardIterator<Data> {
    protected final ForwardIterator<Box<LLNode<Data>>> itr;

    public ListFIterator() { itr = FRefIterator();}

    public ListFIterator(ListFIterator itr) { this.itr = itr.itr;  }

    @Override
    public Data DataNNext() {
      if (!IsValid()) {
        throw new IllegalStateException("Iterator is not valid.");
      }
      Box<LLNode<Data>> oldCur = itr.GetCurrent();
      itr.Next();
      return oldCur.Get().Get();
    }

    @Override
    public boolean IsValid() { return itr.IsValid();  }

    @Override
    public void Reset() { itr.Reset(); }

    @Override
    public Data GetCurrent() {
      if (!IsValid())
        throw new IllegalStateException("Iterator is not valid.");
      return itr.GetCurrent().Get().Get();
    }

    @Override
    public void Next() { itr.Next(); }

    @Override
    public void SetCurrent(Data data) {
       itr.GetCurrent().Get().Set(data);
    }
    
  }

  protected class ListBIterator implements MutableBackwardIterator<Data> {
    protected final BackwardIterator<Box<LLNode<Data>>> itr;

    public ListBIterator() {  itr = BRefIterator(); }

    public ListBIterator(ListBIterator itr) { this.itr = itr.itr; }

    @Override
    public Data DataNPrev() {
      if (!IsValid()) { throw new IllegalStateException("Iterator is not valid.");  }
      Box<LLNode<Data>> oldCur = itr.GetCurrent();
      itr.Prev();
      return oldCur.Get().Get();
    }

    @Override
    public boolean IsValid() {  return itr.IsValid(); }

    @Override
    public void Reset() { itr.Reset(); }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator is not valid.");
      return itr.GetCurrent().Get().Get();
    }

    @Override
    public void Prev() { itr.Prev(); }

    @Override
    public void SetCurrent(Data data) {
       itr.GetCurrent().Get().Set(data);
    }
  }

  @Override
  public ForwardIterator<Data> FIterator() { return new ListFIterator(); }
  @Override
  public BackwardIterator<Data> BIterator() { return new ListBIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() {return Natural.Of(size);}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    size.Assign(MutableNatural.ZERO);
    headref.Set(null);
    tailref.Set(null);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    if (dat == null) return false;
    final Box<LLNode<Data>> prd = new Box<>();
    return FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (node.Get().equals(dat)) {
        cur.Set(node.GetNext().Get());
        if (tailref.Get() == node) { tailref.Set(prd.Get()); }
        size.Decrement();
        return true;
      }
      prd.Set(node);
      return false;
    });
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  public Data GetFirst() {
    if (IsEmpty()) throw new IndexOutOfBoundsException("out of bound");
    return headref.Get().Get();
  }

  @Override
  public Data GetLast() {
    if (IsEmpty()) throw new IndexOutOfBoundsException("out of bound");
    return tailref.Get().Get();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Sequence<Data> SubSequence(Natural from, Natural to) {
    if (from == null || to == null) return null;

    long f = from.ToLong(), t = to.ToLong();
    if (f > t || t >= size.ToLong()) return null;

    final Box<LLNode<Data>> head = new Box<>();
    final Box<LLNode<Data>> tail = new Box<>();
    final Box<Long> idx = new Box<>(0L);

    TraverseForward(dat -> {
      long i = idx.Get();
      if (i > t) return true;

      if (i >= f) {
        LLNode<Data> linkNode = new LLNode<>(dat);
        if (head.Get() == null) head.Set(linkNode);
        else tail.Get().SetNext(linkNode);
        tail.Set(linkNode);
      }

      idx.Set(i + 1);
      return false;
    });

    return NewChain(t - f + 1, head.Get(), tail.Get());
  }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  public Data AtNRemove(Natural pos) {
    if (pos == null) throw new NullPointerException("Position cannot be null");
    if (IsEmpty()) throw new IndexOutOfBoundsException("out of bound");
    long idx = pos.ToLong();
    if (idx < 0 || idx >= size.ToLong()) throw new IndexOutOfBoundsException("out of bound");

    LLNode<Data> removed;
    if (idx == 0) {
      removed = headref.Get();
      headref.Set(removed.GetNext().Get());
      if (tailref.Get() == removed) tailref.Set(null);
    } else {
      LLNode<Data> prev = headref.Get();
      for (long i = 0; i < idx - 1; i++) prev = prev.GetNext().Get();
      removed = prev.GetNext().Get();
      prev.SetNext(removed.GetNext().Get());
      if (tailref.Get() == removed) tailref.Set(prev);
    }
    size.Decrement();
    return removed.Get();
  }

  @Override
  public void RemoveFirst() { if (!IsEmpty()) AtNRemove(Natural.ZERO); }

  @Override
  public void RemoveLast() { if (!IsEmpty()) AtNRemove(Size().Decrement()); }

  @Override
  public Data FirstNRemove() { return AtNRemove(Natural.ZERO); }

  @Override
  public Data LastNRemove() { return IsEmpty() ? null : AtNRemove(Size().Decrement()); }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {
    if (fun == null) return false;

    boolean status = false;
    Box<LLNode<Data>> prev = new Box<>();
    Box<LLNode<Data>> cur = headref;

    while (!cur.IsNull()) {
      LLNode<Data> node = cur.Get();

      if (!fun.Apply(node.Get())) {
        if (prev.IsNull()) headref.Set(node.GetNext().Get());
        else prev.Get().SetNext(node.GetNext().Get());

        if (tailref.Get() == node) tailref.Set(prev.Get());
        
        cur.Set(node.GetNext().Get());
        size.Decrement();
        status = true;
      } else {
        prev.Set(node);
        cur = node.GetNext();
      }
    }
    return status;
  }

}
