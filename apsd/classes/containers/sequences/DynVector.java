package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data> {

  public DynVector(){ super();}

  public DynVector(Natural inisize){ super(inisize);}

  public DynVector(TraversableContainer<Data> con){ super(con);}

  public DynVector(Data[] arr){ super(arr);}

  public DynVector<Data> NewVector(Data[] arr){ return new DynVector<>();}

  @Override
  public MutableForwardIterator<Data> FMutIterator() { return FIterator(); }

  @Override
  public MutableBackwardIterator<Data> BMutIterator() { return BIterator(); }


}
