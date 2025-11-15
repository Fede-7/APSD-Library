package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container {

  boolean Remove(Data dat);

  default boolean RemoveAll(TraversableContainer<Data> con){
    final Box<Boolean> remAll = new Box<>(true);
    if (con != null) con.TraverseForward(dat -> {remAll.Set(remAll.Get() && Remove(dat)); return false;});
    return remAll.Get();
  }

  default boolean RemoveSome(TraversableContainer<Data> con){
    final Box<Boolean> remSome = new Box<>(false);
    if(con != null) con.TraverseForward(dat -> {remSome.Set(remSome.Get() || Remove(dat)); return false;});

    return remSome.Get();
  }

}
