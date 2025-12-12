package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data>{

  protected Data[] arr; 

  public VectorBase() { ArrayAlloc(Natural.ONE);}

  public VectorBase(Data[] arr) {
    if (arr == null) throw new NullPointerException("Array cannot be null!");
    this.arr = arr;
  }

  public VectorBase(Natural size) {
    if (size == null) throw new NullPointerException("Size cannot be null!");
    ArrayAlloc(size);
  }

  public VectorBase(TraversableContainer<Data> con) {
    if (con == null) throw new NullPointerException("TraversableContainer cannot be null!");
    ArrayAlloc(con.Size());
    final MutableNatural index = new MutableNatural();
    con.TraverseForward(dat -> {
      SetAt(dat, index.GetNIncrement());
      return false;
    });
  }

  protected abstract VectorBase<Data> NewVector(Data[] arr);

  @SuppressWarnings("unchecked")
  protected void ArrayAlloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    arr = (Data[]) new Object[(int) size];
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {ArrayAlloc(Natural.ZERO);}

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public Natural Capacity() {return Natural.Of(((long) arr.length));}

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  protected class VectorFIterator implements MutableForwardIterator<Data>{
    protected long idxCur = 0L;

    @Override
    public boolean IsValid() {return (0 <= idxCur && idxCur < Capacity().ToLong());}

    @Override
    public Data GetCurrent() { 
      if (!IsValid()) throw new IllegalStateException(" Iterator is not valid!");
      return arr[(int) idxCur];
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) throw new IllegalStateException(" Iterator is not valid!");
      arr[(int) idxCur] = dat;
    }

    @Override
    public Data DataNNext() {
      if (!IsValid()) throw new IllegalStateException(" Iterator is not valid!");
      Data dat = GetCurrent();
      idxCur++;
      return dat;
    }

    @Override
    public void Next() { DataNNext();}

    @Override
    public void Next(long steps) { for(; steps > 0; steps--){DataNNext();}}

    @Override
    public void Next(Natural steps) {Next(steps.ToLong());}

    @Override
    public void Reset() { idxCur = 0L;}

  }

  protected class VectorBIterator implements MutableBackwardIterator<Data>{
    protected long idxCur = Size().ToLong() - 1;

    @Override
    public boolean IsValid() {return (0 <= idxCur && idxCur < Size().ToLong());}

    @Override
    public Data GetCurrent() { 
      if (!IsValid()) throw new IllegalStateException(" Iterator is not valid!");
      return arr[(int) idxCur];
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) throw new IllegalStateException(" Iterator is not valid!");
      arr[(int) idxCur] = dat;
    }

    @Override
    public Data DataNPrev() {
      if (!IsValid()) throw new IllegalStateException(" Iterator is not valid!");
      Data dat = GetCurrent();
      idxCur--;
      return dat;
    }

    @Override
    public void Prev() { DataNPrev();}

    @Override
    public void Prev(long steps) { for(; steps > 0; steps--){DataNPrev();}}

    @Override
    public void Prev(Natural steps) {Prev(steps.ToLong());}

    @Override
    public void Reset() { idxCur = Size().ToLong() - 1;}

  }

  @Override
  public MutableForwardIterator<Data> FIterator() { return new VectorFIterator();}

  @Override
  public MutableBackwardIterator<Data> BIterator() { return new VectorBIterator();}

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  //TODO: forse manca qualcosa...

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */
  
  //FIXME: errore variabile
  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
    if (start == null || end == null) return null;
    if (!IsInBound(start) || !IsInBound(end) || start.compareTo(end) > 0) return null;

    long s = start.ToLong();
    long e = end.ToLong();
    long span = e - s + 1;
    if (span < 0 || span > Integer.MAX_VALUE) return null;

    int len = (int) span;

    @SuppressWarnings("unchecked")
    Data[] subArr = (Data[]) new Object[len];
    
    for (int i = 0; i < len; i++) {
      subArr[i] = arr[(int) (s + i)];
    }

    return (MutableSequence<Data>) NewVector(subArr);
  }
}
