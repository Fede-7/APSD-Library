package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> {

  protected long size = 0L;
  
  //TODO: costruttore
  // DynLinearVectorBase

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() { return Capacity();}

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() { Realloc(Natural.ZERO);  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newsize) {
    //TODO: cosa cambia dal super??
    long size = ExcIfOutOfBound(newsize);
    
    // Data[] newarr;
    // if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    // newarr = (Data[]) new Object[(int) size];
    // long minsize = Math.min(arr.length, (int) size);
    // System.arraycopy(arr, 0, newarr, 0, (int) minsize);
    // arr = newarr; 
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public void Expand(Natural factor) {
      // TODO Auto-generated method stub
      
  }

  @Override
  public void Reduce(Natural factor) {
      // TODO Auto-generated method stub
      
  }


  /* ************************************************************************ */
  /* Override specific member functions from VectorBase<Data> across LinVectBase<data>               */
  /* ************************************************************************ */

  @Override
  protected void ArrayAlloc(Natural newsize) {
    // TODO Auto-generated method stub
    super.ArrayAlloc(newsize);
  }
}
