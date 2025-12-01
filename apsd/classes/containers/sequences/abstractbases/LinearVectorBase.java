package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  public LinearVectorBase() { super();}

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newsize) { 
    long size = ExcIfOutOfBound(newsize);
    Data[] newarr;
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    newarr = (Data[]) new Object[(int) size];
    long minsize = Math.min(arr.length, (int) size);

    //TODO: Verificare il funzionamento di arraycopy - i don't trust you @Copilot
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
     Iter.Next(ExcIfOutOfBound(pos));
     return Iter.GetCurrent();
  }

  @Override
  public void SetAt(Data elem, Natural pos) {
    if(pos == null) throw new NullPointerException("Natural cannot be a null value");
    MutableForwardIterator<Data> Iter = FIterator();
    Iter.Next(ExcIfOutOfBound(pos));
    Iter.SetCurrent(elem);
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  //TODO: Check this method wtf is going on here

}
