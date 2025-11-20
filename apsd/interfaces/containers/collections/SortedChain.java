package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<Data>> extends OrderedChain<Data>, SortedSequence<Data> {

  default Natural SearchPredecessor(Data dat){
  if (dat == null || this.IsEmpty()) return null;

    long str = 0;
    long fns = Size().ToLong() - 1;
    Natural pred = null;

    while (str <= fns) {
        long medIdx = str + (fns - str) / 2;
        Data medElem = GetAt(Natural.Of(medIdx));

        if(medElem.compareTo(dat) < 0){
          pred = Natural.Of(medIdx);
          str = medIdx + 1;
        }else fns = medIdx - 1;
    }  
    return pred;  
  }

  default Natural SearchSuccessor(Data dat){
    if (dat == null || this.IsEmpty()) return null;

    long str = 0;
    long fns = Size().ToLong() - 1;
    Natural succ = null;

    while (str <= fns) {
        long medIdx = str + (fns - str) / 2;
        Data medElem = GetAt(Natural.Of(medIdx));

        if(medElem.compareTo(dat) > 0){
          succ = Natural.Of(medIdx);
          str = medIdx - 1;
        }else fns = medIdx + 1;
    }  
    return succ; 
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat) {
    if (dat == null || this.IsEmpty()) return null;

    long str = 0;
    long fns = Size().ToLong() - 1;

    while (str <= fns) {
        long medIdx = str + (fns - str) / 2;
        Data medElem = GetAt(Natural.Of(medIdx));

        //NOTE: Integer.Signum => serve per gestire i risultati di compareTo {solo -1, 0, 1}
        switch (Integer.signum(dat.compareTo(medElem))) {
            case 0  -> { return Natural.Of(medIdx); }
            case -1 -> fns = medIdx - 1;
            case 1  -> str = medIdx + 1;
          }
        }

        return null;
      }

  /* ************************************************************************ */
  /* Override ?? specific member functions from Set                              */
  /* ************************************************************************ */

  default void Intersection(SortedChain<Data> chn) {
    Natural i = Natural.ZERO, j = Natural.ZERO;
    while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
      int cmp = GetAt(i).compareTo(chn.GetAt(j));
      if (cmp < 0) {
        RemoveAt(i);
      } else {
        j = j.Increment();
        if (cmp == 0) { i = i.Increment(); }
      }
    }
    while (i.compareTo(Size()) < 0) {
      RemoveAt(i);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  @Override
  default Data Min() {return (!IsEmpty()) ? GetFirst() : null;}

  @Override
  default Data Max() {return (!IsEmpty()) ? GetLast() : null;}

}
