package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> {

  protected long size = 0L;

  public DynCircularVectorBase() {super();}

  public DynCircularVectorBase(Natural initialCapacity) {super(initialCapacity);}

  public DynCircularVectorBase(TraversableContainer<Data> con) {super(con);}

  public DynCircularVectorBase(Data[] arr) {super(arr);}
  
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
    size = 0L;
    start = 0L;
    Realloc(Natural.ZERO);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newCapacity){
    if(newCapacity == null) throw new NullPointerException("Size cannot be null!");
    super.Realloc(newCapacity);
    if(size > newCapacity.ToLong()){
      size = newCapacity.ToLong();
    }
  }


  @Override
  public Natural Capacity() { return Natural.Of(arr.length); }
  

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public void Expand(Natural factor) {
    if (factor == null) throw new NullPointerException("factor cannot be null!");
    long n = factor.ToLong();
    if (n <= 0) return;
    long newSize = size + n;
    if (newSize > Integer.MAX_VALUE) throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
    if (Capacity().ToLong() < newSize) Realloc(Natural.Of(newSize));
    long cap = Capacity().ToLong();
    for (long i = size; i < newSize; i++) arr[(int) ((start + i) % cap)] = null;
    size = newSize;
  }

  @Override
  public void Reduce(Natural factor) {
    if (factor == null) throw new NullPointerException("factor cannot be null!");
    long n = factor.ToLong();
    if (n <= 0) return;
    if (n > size) throw new IllegalArgumentException("Cannot reduce by more than current size!");
    long newSize = size - n;
    long cap = Capacity().ToLong();
    for (long i = newSize; i < size; i++) arr[(int) ((start + i) % cap)] = null;
    size = newSize;
    Shrink();
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */
  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    DynVector.super.ShiftLeft(pos, num);
  }

  @Override
  public void ShiftRight(Natural pos, Natural num) {
    DynVector.super.ShiftRight(pos, num);
  }

  /* ************************************************************************ */
  /* Override specific member functions from VectorBase<Data> across LinVectBase<data>               */
  /* ************************************************************************ */
  
  @SuppressWarnings("unchecked")
  @Override
  public void ArrayAlloc(Natural newsize) {
    long cap = (newsize == null) ? 0L : newsize.ToLong();
    if (cap > Integer.MAX_VALUE)
      throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
    arr = (Data[]) new Object[(int) cap];
    start = 0L;
    if (size > cap) size = cap;
  }
}
