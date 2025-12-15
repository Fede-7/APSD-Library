package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> {

  default void Union(Set<Data> set){
    if(set == null) return;
    set.TraverseForward(dat -> {
      if(!this.Exists(dat)) this.Insert(dat);
      return false;
    });
  }

  default void Difference(Set<Data> set) {
    if (set == null || set.IsEmpty()) {return;}

    this.Filter(dat ->!set.Exists(dat));
  }
  default void Intersection(Set<Data> set) {
    if (set == null || set.IsEmpty()) {
      this.Clear();
      return;
    }
    this.Filter(set::Exists);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  default boolean IsEqual(IterableContainer<Data> con) {
    if (con == null || !this.Size().equals(con.Size())) return false;
    return !con.TraverseForward(elm -> !this.Exists(elm));
  };

}
