package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<Data> {

  public DynCircularVector(){super();}

  public DynCircularVector(Natural inisize){super(inisize);}

  public DynCircularVector(TraversableContainer<Data> con){super(con);}

  protected DynCircularVector(Data[] arr){super(arr);}

  public DynCircularVector<Data> NewVector(Data[] arr){return new DynCircularVector<>(arr);}

  @Override
  public MutableForwardIterator<Data> FIterator() { return super.FIterator(); }

  @Override
  public MutableBackwardIterator<Data> BIterator() { return super.BIterator(); }

}
