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

  //TODO: find Vector base info?
  public VectorBase(Natural inisize){
    if (inisize == null) throw new NullPointerException("Natural cannot be a null value");
    ArrayAlloc(inisize);
  }

  protected void NewVector(Data[] arr){this.arr = arr;}

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

    //TODO: IsValid vuole Size or Capacity?
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

  @Override
  public MutableForwardIterator<Data> FIterator() { return new VectorFIterator();}

  @Override
  public MutableBackwardIterator<Data> BIterator() {
      // TODO Auto-generated method stub
      return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
      // TODO: implement SubSequence
      throw new UnsupportedOperationException("SubSequence not yet implemented");
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  //TODO: ?? non lo so manco io
}
