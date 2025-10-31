package apsd.interfaces.containers.base;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{

  Boolean Insert(Data data);

  Boolean InsertAll(TraversableContainer<Data> con){
    final Box<boolean> allInserted = new Box<>(true);
    if(con != null) con.TraversFoward -> {dat -> allInserted.Set(allInserted.Get() && Insert(dat)); };
    return allInserted.Get();
  }

  Boolean InsertSome(TraversableContainer<Data> con){
    final Box<boolean> someInserted = new Box<>(false);
    if(con != null) con.TraversFoward -> {dat -> someInserted.Set(someInserted.Get() || Insert(dat)); };
    return someInserted.Get();
  }

}
