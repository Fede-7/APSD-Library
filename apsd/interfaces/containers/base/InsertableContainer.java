package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{

  boolean Insert(Data data);

  default boolean InsertAll(TraversableContainer<Data> con){
    final Box<Boolean> allInserted = new Box<>(true);
    if(con != null) con.TraverseForward(dat -> { allInserted.Set(allInserted.Get() && Insert(dat)); return false; });
    return allInserted.Get();
  }

  default boolean InsertSome(TraversableContainer<Data> con){
    final Box<Boolean> someInserted = new Box<>(false);
    if(con != null) con.TraverseForward(dat -> { someInserted.Set(someInserted.Get() || Insert(dat)); return false; });
    return someInserted.Get();
  }

}
