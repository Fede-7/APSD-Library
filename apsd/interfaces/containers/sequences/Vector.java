package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data>{

  default void ShiftLeft(Natural pos) {
    long idx = ExcIfOutOfBound(pos);

    for (; idx < Size().ToLong() - 1; idx++) {
      SetAt(GetAt(Natural.Of(idx + 1)), Natural.Of(idx));
    }
    SetLast(null);
  }

  default void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len > 0) {
      long iniwrt = idx;
      long wrt = iniwrt;
      for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++) {
        SetAt(null, Natural.Of(wrt));
      }
    }
  }

  default void ShiftFirstLeft(){if(!IsEmpty()) ShiftLeft(Natural.ZERO);}

  default void ShiftLastLeft(){if(!IsEmpty()) SetAt(null, Size().Decrement()); }

  default void ShiftRight(Natural pos){
    long idx = ExcIfOutOfBound(pos);
    for (; idx > 1; idx--) {
      SetAt(GetAt(Natural.Of(idx - 1)), Natural.Of(idx));
    }
    SetFirst(null);
  }

  default void ShiftFirstRight(){if(!IsEmpty()) SetFirst(null);;}

  default void ShiftLastRight(){if(!IsEmpty())ShiftRight(Size().Decrement());;}

  default Vector<Data> SubVector(Natural start, Natural finish){S//TODO: non lo so}

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size(){return Capacity();}

}
