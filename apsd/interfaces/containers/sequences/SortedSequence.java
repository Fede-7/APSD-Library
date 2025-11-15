package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<Data>> extends Sequence<Data>, SortedIterableContainer<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Exists(Data dat) { return this.TraverseForward( elem -> elem.equals(dat)); }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...

}
