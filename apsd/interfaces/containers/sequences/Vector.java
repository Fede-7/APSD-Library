package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data>{

  default void ShiftLeft(Natural pos) {
   ShiftLeft(pos, Natural.ONE);
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
    ShiftRight(pos, Natural.ONE);
  }

  default void ShiftRight(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long len = num.ToLong();
    len = (len <= idx + 1) ? len : idx + 1;

    if (len > 0) {
      long iniwrt = idx;
      long wrt = iniwrt;

      for (long rdr = idx - len; rdr >= 0; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }

      for (; wrt >= 0; wrt--) {
        SetAt(null, Natural.Of(wrt));
      }

    }
  }

  default void ShiftFirstRight(){if(!IsEmpty()) SetFirst(null);;}

  default void ShiftLastRight(){if(!IsEmpty())ShiftRight(Size().Decrement());;}

  default Vector<Data> SubVector(Natural start, Natural finish){/*TODO: non lo so*/}

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size(){return Capacity();}

}
