package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete (static) circular vector implementation. */
public abstract class CircularVector<Data> extends CircularVectorBase<Data> { // Must extend CircularVectorBase

  public CircularVector(){
    super();
  }

  public CircularVector(Natural inisize){ super.ArrayAlloc(inisize); }

  public CircularVector(TraversableContainer<Data> con){ super(con); }

  protected CircularVector(Data[] arr){ super(arr); }

  public abstract CircularVector<Data> NewVector(Data[] arr);

}
