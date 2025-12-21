package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> {

  protected long size = 0L;
  
  public DynLinearVectorBase() { super(); }

  public DynLinearVectorBase(Natural initialCapacity) { super(initialCapacity);}

  public DynLinearVectorBase(Data[] arr) { 
    super(arr);
    this.size = arr.length;
  }

  public DynLinearVectorBase(TraversableContainer<Data> con) {
    super(con);
    this.size = arr.length;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() { return Natural.Of(size);}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    size = 0L;
    ArrayAlloc(Natural.ZERO);
  }


  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newCapacity){
    if(newCapacity == null) return;
    super.Realloc(newCapacity);
    if(size > newCapacity.ToLong()){ size = newCapacity.ToLong();}
  }

  @Override
  public Natural Capacity() { return Natural.Of(arr.length); }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public void Expand(Natural factor) {
    if (factor == null) return;
    long n = factor.ToLong();
    if (n <= 0) return;
    long newSize = size + n;
    if (newSize > Integer.MAX_VALUE) throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
    if (Capacity().ToLong() < newSize) Realloc(Natural.Of(newSize));
    for (long i = size; i < newSize; i++) arr[(int) i] = null;
    size = newSize;
  }

  @Override
  public void Reduce(Natural factor){
    if (factor == null) return;
    long n = factor.ToLong();
    if (n <= 0) return;
    if (n > size) throw new IllegalArgumentException("Cannot reduce by more than current size!");
    long newSize = size - n;
    for (long i = newSize; i < size; i++) arr[(int) i] = null;
    size = newSize;
    Shrink();
  }



  /* ************************************************************************ */
  /* Override specific member functions from VectorBase<Data> across LinVectBase<data>               */
  /* ************************************************************************ */

  @SuppressWarnings("unchecked")
  @Override
  protected void ArrayAlloc(Natural newsize) {
    long cap = (newsize == null) ? 0L : newsize.ToLong();
    if (cap > Integer.MAX_VALUE)
      throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
    arr = (Data[]) new Object[(int) cap];
    if (size > cap) size = cap;
  }
}
