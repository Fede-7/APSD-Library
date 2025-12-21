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
  default Natural Search(Data dat) {
    if (dat == null) return null;

    long str = 0;
    long fns = Size().ToLong() - 1;
    Long result = null;

    while (str <= fns) {
      long medIdx = str + (fns - str) / 2;
      Data medElem = GetAt(Natural.Of(medIdx));

      int cmp = dat.compareTo(medElem);
      switch (cmp) {
        case 0 -> {
          result = medIdx;
          fns = medIdx - 1;
        }
        case -1 -> fns = medIdx - 1;
        case 1 -> str = medIdx + 1;
      }
    }
    return result == null ? null : Natural.Of(result);
  }

}
