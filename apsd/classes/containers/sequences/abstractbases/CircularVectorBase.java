package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  protected long start = 0L;

  public CircularVectorBase() { super();}

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

   @Override
  public void Realloc(Natural newsize) { 
    if(newsize == null) throw new NullPointerException("Natural cannot be a null value");
    Data[] newarr;
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    newarr = (Data[]) new Object[(int) size];
    long minsize = Math.min(arr.length, (int) size);

    //TODO: pt2 i don't trust you @Copilot
    System.arraycopy(arr, 0, newarr, 0, (int) minsize);
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
    long idx = ExcIfOutOfBound(pos);
    long str = pos.ToLong();
    for(long i = 0; i < num.ToLong(); i++){
      do {
        long newPos = (idx--) % Size().ToLong();
        Data temp = GetAt(Natural.Of(newPos));
        SetAt(Natural.Of(newPos), GetAt(Natural.Of(idx)));
        idx--;
      } while (idx != str);
    };
  }

  @Override
  public void ShiftRight(Natural pos, Natural num) { 
    long idx = ExcIfOutOfBound(pos);
    long str = pos.ToLong();
    long newPos;
    for(long i = 0; i < num.ToLong(); i++){
      do {
        newPos = (idx++) % Size().ToLong();
        Data temp = GetAt(Natural.Of(newPos));
        SetAt(Natural.Of(newPos), GetAt(Natural.Of(idx)));
        idx++;
      }while(newPos == pos.ToLong());
    }
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @Override
  ArrayAlloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    arr = (Data[]) new Object[(int) size];
    start = 0L;
  }

}
