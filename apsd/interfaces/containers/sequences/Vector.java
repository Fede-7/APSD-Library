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

  default void ShiftFirstRight(){if(!IsEmpty()) SetFirst(null);;}

  default void ShiftLastRight(){if(!IsEmpty())ShiftRight(Size().Decrement());;}

  @SuppressWarnings("unchecked")
  default Vector<Data> SubVector(Natural start, Natural finish){
    /* @Copilot TODO: Non so come restituire un vettore */
    Vector<Data> subVector;
    try {
      subVector = (Vector<Data>) this.getClass().getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    long idx = 0;
    long str = ExcIfOutOfBound(start);
    long fns = ExcIfOutOfBound(finish);

    for (; str <= fns; str++, idx++) {
      subVector.SetAt(GetAt(Natural.Of(str)), Natural.Of(idx));
    }
    return subVector;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size(){return Capacity();}
}
