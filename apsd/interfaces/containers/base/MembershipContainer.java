package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla verifica di appartenenza. */
public interface MembershipContainer<Data> extends Container { 

  // Exists
  boolean Exists(Data dat){
    final Box<Boolean> exists = new Box<>(false);
    this.TraverseForward(data -> { exists.Set(exists.Get() || data.equals(dat)); });
    return exists.Get();
  }
}
