package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> {

  protected long size = 0L;
  
  public DynLinearVectorBase() { super(); }

  public DynLinearVectorBase(Natural initialCapacity) { super(initialCapacity);}

  public DynLinearVectorBase(Data[] arr) { super(arr);}

  public DynLinearVectorBase(TraversableContainer<Data> con) {super(con);}

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() { return Capacity();}

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

  @SuppressWarnings("unchecked")
  @Override
  public void Realloc(Natural newCapacity) {
    if (newCapacity == null) return;

    long newCap = newCapacity.ToLong();
    if (newCap > Integer.MAX_VALUE)
      throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");

    Data[] newarr = (Data[]) new Object[(int) newCap];
    long oldCap = Capacity().ToLong();
    long toCopy = (oldCap < newCap) ? oldCap : newCap;

    for (int i = 0; i < toCopy; i++) newarr[i] = arr[i];

    arr = newarr;

    if (size > newCap) size = newCap;
  }
  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public void Expand(Natural factor) {
    long f = (factor == null) ? 0L : factor.ToLong();
    f = (f <= 1L) ? 2L : f;
    long cur = Capacity().ToLong();
    long newCap = (cur == 0) ? f : cur * f;
    Realloc(Natural.Of(newCap));
  }

  @Override
  public void Reduce(Natural factor) {
    long f = (factor == null) ? 0L : factor.ToLong();
    if (f <= 1L) return;
    long cur = Capacity().ToLong();
    long newCap = cur / f;
    Realloc(Natural.Of(newCap));
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
