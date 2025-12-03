package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete (static linear) vector implementation. */
public class Vector<Data> extends LinearVectorBase<Data>{

  public Vector() {
    super();
    ArrayAlloc(Natural.ONE);
  }

  public Vector(Natural inisize) {
    super();
    ArrayAlloc(inisize);
  }

  protected Vector(Data[] arr) {
    super();
    this.arr = arr;
  }
  
  public Vector(TraversableContainer<Data> con) {
    super();
    ArrayAlloc(new Natural(con.Size()));
    MutableForwardIterator<Data> it = FIterator();
    int i = 0;
    while (!it.IsValid()) {
      arr[i++] = it.DataNNext();
    }
  }

  @Override
  protected VectorBase<Data> NewVector(Data[] arr) { return new Vector<Data>(arr); }

  @Override
  public MutableForwardIterator<Data> FMutIterator() { return FIterator(); }

  @Override
  public MutableBackwardIterator<Data> BMutIterator() { return BIterator(); }

}
