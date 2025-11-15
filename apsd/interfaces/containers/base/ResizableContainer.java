package apsd.interfaces.containers.base;

import javax.print.attribute.standard.MediaSize.NA;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer {

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  void Expand(Natural factor);
  default void Expand() { Expand(Natural.ONE); }

  void Reduce(Natural factor);
  default void Reduce() { Reduce(Natural.ONE); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size() { return Capacity(); }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  default void Grow() { if((long) (THRESHOLD_FACTOR * GROW_FACTOR * Size().ToLong()) >= Capacity().ToLong()) ReallocableContainer.super.Grow();}

  @Override
  default void Grow(Natural factor) { if((long) (THRESHOLD_FACTOR * GROW_FACTOR * Size().ToLong()) >= Capacity().ToLong()) ReallocableContainer.super.Grow(factor); }

  @Override
  default void Shrink() { if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong()) ReallocableContainer.super.Shrink(); }

  @Override
  default void Shrink(Natural factor) { if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong()) ReallocableContainer.super.Shrink(factor); }
}
