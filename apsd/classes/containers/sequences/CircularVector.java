package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete (static) circular vector implementation. */
public class CircularVector<Data> extends CircularVectorBase<Data> {

  public CircularVector(){ super(); }

  public CircularVector(Natural inisize){ super(inisize); }

  public CircularVector(TraversableContainer<Data> con){ super(con); }

  protected CircularVector(Data[] arr){ super(arr); }

  public CircularVector<Data> NewVector(Data[] arr){ return new CircularVector<>(arr); }

  @Override
  public MutableForwardIterator<Data> FIterator() { return super.FIterator(); }

  @Override
  public MutableBackwardIterator<Data> BIterator() { return super.BIterator(); }

}
