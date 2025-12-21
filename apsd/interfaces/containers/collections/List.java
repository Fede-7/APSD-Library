package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.base.IterableContainer;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data> {

  default List<Data> SubList(Natural frsIdx, Natural scdIdx){
    if(frsIdx.compareTo(scdIdx) > 0) throw new IllegalArgumentException("first index cannot be bigger than second");
    return (List<Data>) SubChain(Natural.Of(ExcIfOutOfBound(frsIdx)), Natural.Of(ExcIfOutOfBound(scdIdx)));
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  default boolean IsEqual(IterableContainer<Data> con) {
    if(con == null || !this.Size().equals(con.Size())) {return false;}
    final ForwardIterator<Data> it1 = this.FIterator();
    final ForwardIterator<Data> it2 = con.FIterator();

    while(it1.IsValid()) {
      Data d1 = it1.DataNNext();
      Data d2 = it2.DataNNext();

      if (d1 == null ? d2 != null : !d1.equals(d2)) { return false;}
    }
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    if (dat == null) return false;
    this.InsertFirst(dat);
    return true;
  }

}
