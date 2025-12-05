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

//FIXME: LLNode è esteso opp import? pdf si, prof here no

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
    size.Assign(con.Size());
    final Box<Boolean> first = new Box<>(true);
    con.TraverseForward(dat -> {
      LLNode<Data> node = new LLNode<>(dat);
      if (first.Get()) {
        headref.Set(node);
        first.Set(false);
      } else {
        tailref.Get().SetNext(node);
      }
      tailref.Set(node);
      return false;
    });
  }

  public LLChainBase(long size, LLNode<Data> headref, LLNode<Data> tailref){
    this.size.Assign(size);
    this.headref.Set(headref);
    this.tailref.Set(tailref);
  }

  abstract protected LLChainBase<Data> NewChain(long size, LLNode<Data> headref, LLNode<Data> tailref);

  //lavora con Box<LLNode>,

  protected class ListFRefIterator implements ForwardIterator<Box<LLNode<Data>>> {
    protected Box<LLNode<Data>> cur;

    public ListFRefIterator() { this.cur = headref;}

    public ListFRefIterator(ListFRefIterator itr) { this.cur = itr.cur;}

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
      if (!IsValid())
        throw new IllegalStateException("Iterator is not valid.");
      return arr.GetAt(Natural.Of(cur));
    }

    @Override
    public Box<LLNode<Data>> DataNPrev() {
      if (!IsValid())
        throw new IllegalStateException("Iterator is not valid.");
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

  //estrae il dato e lo restituisce

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
      if (data == null)
        return;
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
      if (data == null) return;
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
  public Data GetFirst() {return IsEmpty() ? null : (Data) headref.Get();}

  @Override
  public Data GetLast() {return IsEmpty() ?  null : (Data) tailref.Get();}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Sequence<Data> SubSequence(Natural from, Natural to) {
    long f = from.ToLong(), t = to.ToLong();
    
    if( f > t || t >= size.ToLong()) return null;
    final Box<LLNode<Data>> head = new Box<>();
    final Box<LLNode<Data>> tail = new Box<>();
    final Box<Long> idx = new Box<>(0L);

    TraverseForward(dat -> {
      if (idx.Get() > t) return true;
      LLNode<Data> linkNode = new LLNode<>(dat);
      if(idx.Get() > f){
        tail.Get().SetNext(linkNode);
      }else if (idx.Get() == f){
        head.Set(linkNode);
      }
      tail.Set(linkNode);
      idx.Set(idx.Get() + 1);
      return false;
    });

    return NewChain(t-f+1, head.Get(), tail.Get());
  }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  public Data AtNRemove(Natural pos) {
      Data dat = GetAt(pos);
      Remove(dat);
      return dat;
  }

  @Override
  public void RemoveFirst() { Remove(GetFirst()); }

  @Override
  public void RemoveLast() { Remove(GetLast()); }

  @Override
  public Data FirstNRemove() {return AtNRemove(Natural.ZERO);}

  @Override
  public Data LastNRemove() { return AtNRemove(Natural.Of(Size()));}

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {
    if (fun == null) return false;
    
    boolean changed = false;
    Box<LLNode<Data>> prev = new Box<>();
    Box<LLNode<Data>> cur = headref;
    
    while (!cur.IsNull()) {
      LLNode<Data> node = cur.Get();
      
      if (!fun.Apply(node.Get())) {
        if (prev.IsNull()) {
          headref.Set(node.GetNext().Get());
        } else {
          prev.Get().SetNext(node.GetNext().Get());
        }

        // Aggiorno tail
        if (tailref.Get() == node) {
          tailref.Set(prev.Get());  
        }

        // Avanza senza aggiornare prev
        cur.Set(node.GetNext().Get());  
        size.Decrement();
        changed = true;
      } else {
        prev.Set(node);
        cur.Set(node.GetNext().Get());
      }
    }
    
    return changed;
  }

}
