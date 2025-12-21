package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{

  boolean Insert(Data data);

  default boolean InsertAll(TraversableContainer<Data> con){
    final Box<Boolean> allInserted = new Box<>(true);
    if(con != null) con.TraverseForward(dat -> { allInserted.Set(Insert(dat) && allInserted.Get()); return false; });
    return allInserted.Get();
  }

  default boolean InsertSome(TraversableContainer<Data> con){
    final Box<Boolean> someInserted = new Box<>(false);
    if(con != null) con.TraverseForward(dat -> { someInserted.Set(Insert(dat) || someInserted.Get()); return false; });
    return someInserted.Get();
  }

}
