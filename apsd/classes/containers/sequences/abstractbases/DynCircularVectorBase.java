package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> {

  protected long size = 0L;

  protected DynCircularVectorBase() {
    ArrayAlloc(Natural.ZERO);
  }

  protected DynCircularVectorBase(Natural initialCapacity) {
    ArrayAlloc(initialCapacity == null ? Natural.ZERO : initialCapacity);
  }

  protected DynCircularVectorBase(TraversableContainer<Data> con) {
    if (con == null || con.IsEmpty()) {
      ArrayAlloc(Natural.ZERO);
      return;
    }
    ArrayAlloc(con.Size());
    final long[] idx = {0};
    con.TraverseForward(dat -> {
      arr[(int) idx[0]++] = dat;
      return false;
    });
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() {return Capacity();}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() { Realloc(Natural.ZERO);}

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

    for (int i = 0; i < toCopy; i++) {
      newarr[i] = arr[(int) ((start + i) % oldCap)];
    }

    arr = newarr;
    start = 0L;
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
  }
}
