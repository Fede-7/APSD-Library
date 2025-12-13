package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data> {

  default List<Data> SubList(Natural frsIdx, Natural scdIdx){
    return (List<Data>) SubChain(Natural.Of(ExcIfOutOfBound(frsIdx)), Natural.Of(ExcIfOutOfBound(scdIdx)));
  }

  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    this.InsertLast(dat);
    return true;
  }

}
