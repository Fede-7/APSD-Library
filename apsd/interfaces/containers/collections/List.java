package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data> {

  default List<Data> SubList(Natural frsIdx, Natural scdIdx){
    if(frsIdx.compareTo(scdIdx) < 0) throw new IllegalArgumentException("first index cannot be bigger than second");
    return (List<Data>) SubChain(Natural.Of(ExcIfOutOfBound(frsIdx)), Natural.Of(ExcIfOutOfBound(scdIdx)));
  }

  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    this.InsertFirst(dat);
    return true;
  }

}
