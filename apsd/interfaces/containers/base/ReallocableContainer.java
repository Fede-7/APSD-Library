package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer extends ClearableContainer, Reallocable {

  double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
  double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

  Natural capacity();

  default void Grow() {
    Realloc(Natural.valueOf((long) (capacity().toLong() * GROW_FACTOR)));
  }

  default void Grow(Natural factor) {
    Realloc(Natural.valueOf((long) (capacity().toLong() * factor.toLong())));
  }

  default void Shrink() {
    Realloc(Natural.valueOf((long) (capacity().toLong() / SHRINK_FACTOR)));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  default Natural size() { return capacity(); }
  
  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  default void clear() { Realloc(Natural.ZERO);  }

}
