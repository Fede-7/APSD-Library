package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> {

  protected long start = 0L;

  public CircularVectorBase() { super();}

  public CircularVectorBase(TraversableContainer<Data> con) { super(con); }

  public CircularVectorBase(Data[] arr) { super(arr); }

  public CircularVectorBase(Natural inisize) { super(inisize);}
  
  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  

  @SuppressWarnings("unchecked")
  @Override
  public void Realloc(Natural newCapacity) { 
    if (newCapacity == null) return;
    
    long newCap = newCapacity.ToLong();
    if (newCap > Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    
    Data[] newarr = (Data[]) new Object[(int) newCap]; 
    long currentSize = Size().ToLong();
    long copySize = (currentSize > newCap) ? newCap : currentSize;
    
    if (arr.length > 0) {
      for (long i = 0; i < copySize; i++) {
        newarr[(int) i] = arr[(int) ((start + i) % arr.length)];
      }
    }

    arr = newarr;
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

   
  @Override
  public Data GetAt(Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    return arr[(int) ((start + idx) % arr.length)];
  }

  @Override
  public void SetAt(Data elem, Natural pos) {
    long idx = ExcIfOutOfBound(pos);
    arr[(int) ((start + idx) % arr.length)] = elem;
  }



  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void ShiftRight(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    if (num == null) throw new NullPointerException("Number cannot be null");
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len <= 0) return;

    long wrt = size - 1;
    for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
      Natural natrdr = Natural.Of(rdr);
      SetAt(GetAt(natrdr), Natural.Of(wrt));
      SetAt(null, natrdr);
    }
    for (long i = idx; i < idx + len; i++) {
      SetAt(null, Natural.Of(i));
    }
  }

  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    if (num == null) throw new NullPointerException("Number cannot be null");
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len <= 0) return;

    long iniwrt = idx;
    long wrt = iniwrt;
    for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
      Natural natrdr = Natural.Of(rdr);
      SetAt(GetAt(natrdr), Natural.Of(wrt));
      SetAt(null, natrdr);
    }
    for (; wrt - iniwrt < len; wrt++) {
      SetAt(null, Natural.Of(wrt));
    }
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @SuppressWarnings("unchecked")
  @Override
  public void ArrayAlloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    arr = (Data[]) new Object[(int) size];
    start = 0L;
  }

}
