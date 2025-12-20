package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> {

  public LinearVectorBase() { super();}

  public LinearVectorBase(Data[] arr) {super(arr);}

  public LinearVectorBase(Natural initialCapacity) {super(initialCapacity);}

  public LinearVectorBase(TraversableContainer<Data> con) {super(con);}


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
    if (pos == null) throw new NullPointerException("Natural cannot be a null value");
    long idx = ExcIfOutOfBound(pos);
    return arr[(int) idx];  
  }

  @Override
  public void SetAt(Data elem, Natural pos) {
    if (pos == null) throw new NullPointerException("Natural cannot be a null value");
    long idx = ExcIfOutOfBound(pos);
    arr[(int) idx] = elem;
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

}
