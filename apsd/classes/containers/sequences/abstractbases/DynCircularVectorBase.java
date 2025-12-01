package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> { // Must extend CircularVectorBase and implement DynVector

  protected long size = 0L;

  //TODO: costruttore
  // DynCircularVectorBase

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

  @Override
  public void Realloc(Natural newsize) {
      // TODO Auto-generated method stub
      super.Realloc(newsize);
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
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural pos, Natural num) {
    // TODO Auto-generated method stub
    super.ShiftLeft(pos, num);
  }

  @Override
  public void ShiftRight(Natural pos, Natural num) {
    // TODO Auto-generated method stub
    super.ShiftRight(pos, num);
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
