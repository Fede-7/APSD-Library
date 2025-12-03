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
    
    long currentCap = Capacity().ToLong();
    long size = (currentCap < newCap) ? currentCap : newCap;
    
    for (int i = 0; i < size; i++) { newarr[i] = arr[i];}
    arr = newarr; 
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

   
  @Override
  public Data GetAt(Natural pos) {
    if(pos == null) throw new NullPointerException("Natural cannot be a null value");
    MutableForwardIterator<Data> Iter = FIterator(); 
    Iter.Next(pos.Decrement().ToLong() % Size().ToLong());
    return Iter.GetCurrent();
  }

  @Override
  public void SetAt(Data elem, Natural pos) {
    if(pos == null) throw new NullPointerException("Natural cannot be a null value");
    MutableForwardIterator<Data> Iter = FIterator();
    Iter.Next(pos.Decrement().ToLong() % Size().ToLong());
    Iter.SetCurrent(elem);
  }



  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    ExcIfOutOfBound(pos);
    long sz = Size().ToLong();
    if (pos == null || num == null || sz <= 1) return;
    
    long idx = pos.ToLong();
    long shifts = num.ToLong() % sz;
    
    for (long i = 0; i < shifts; i++) {
      long currentIdx = idx;
      Data temp = GetAt(Natural.Of(currentIdx));
      
      long nextIdx = (currentIdx + 1) % sz;
      while (nextIdx != idx) {
        SetAt(GetAt(Natural.Of(nextIdx)), Natural.Of(currentIdx));
        currentIdx = nextIdx;
        nextIdx = (nextIdx + 1) % sz;
      }
      
      long prevIdx = (idx - 1 + sz) % sz;
      SetAt(temp, Natural.Of(prevIdx));
    }
  }

  @Override
  public void ShiftRight(Natural pos, Natural num) { 
    if (pos == null || num == null) return;
    
    long sz = Size().ToLong();
    if (sz <= 1) return;
    
    ExcIfOutOfBound(pos);
    
    long idx = pos.ToLong();
    long shifts = num.ToLong() % sz;
    
    for (long shift = 0; shift < shifts; shift++) {
      long currentIdx = idx;
      Data temp = GetAt(Natural.Of(currentIdx));
      
      long prevIdx = (currentIdx - 1 + sz) % sz;
      while (prevIdx != idx) {
        SetAt(GetAt(Natural.Of(prevIdx)), Natural.Of(currentIdx));
        currentIdx = prevIdx;
        prevIdx = (prevIdx - 1 + sz) % sz;
      }
      
      long nextIdx = (idx + 1) % sz;
      SetAt(temp, Natural.Of(nextIdx));
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
