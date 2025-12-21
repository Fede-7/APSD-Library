package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data>{

  default void ShiftLeft(Natural pos) {
   ShiftLeft(pos, Natural.ONE);
  }

  default void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    if (num == null) throw new NullPointerException("Number cannot be null");
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
    if (num == null) throw new NullPointerException("Number cannot be null");
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;

    if (len > 0) {
      long iniwrt = size - 1;
      long wrt = iniwrt;
      for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }

      for (long i = idx; i < idx + len; i++) {
        SetAt(null, Natural.Of(i));
      }
    }
  }

  default void ShiftFirstRight(){if(!IsEmpty()) ShiftRight(Natural.ZERO);}

  default void ShiftLastRight(){if(!IsEmpty())ShiftRight(Size().Decrement());}
    
  default Vector<Data> SubVector(Natural start, Natural end) {
    if (!IsInBound(start) || !IsInBound(end) || start.compareTo(end) > 0) return null;
    return (Vector<Data>) SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size(){return Capacity();}
}
