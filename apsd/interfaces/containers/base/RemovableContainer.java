package apsd.interfaces.containers.base;

import javax.xml.crypto.Data;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container {

  boolean Remove(Data data){

  }

  boolean RemoveAll(TraversableContainer<Data> con){
    final Box<Boolean> remAll = new Box<>(true);
    if (con != null) con.TraverseForward(dat -> remAll.Set(remAll.Get() && Remove(dat)));
    return remAll.Get();
  }

  boolean RemoveSome(TraversableContainer<Data> con){
    final Box<boolean> remSome = new Box<>(false);
    if(con != null) con.TraverseForward(dat -> remSome.Set(remSome.Get() || Remove(dat)));

    return remSome.Get();
  }

}
