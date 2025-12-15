package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Exists(Data dat) {return (dat != null && Search(dat) != null);}
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data elem) {
    if (elem == null) return null;

    long start = 0;
    long finish = Size().ToLong() - 1;

    while (start <= finish) {

      long medIdx = start + (finish - start) / 2;

      Natural NatMedIdx = Natural.Of(medIdx);
      Data medElem = GetAt(NatMedIdx);
      int cmp = elem.compareTo(medElem);

      if (cmp == 0) {
        return NatMedIdx;
      } else if (cmp < 0) {
        finish = medIdx - 1;
      } else {
        start = medIdx + 1;
      }
    }
    return null;
  }

}
