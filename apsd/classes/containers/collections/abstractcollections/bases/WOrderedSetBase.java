package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.OrderedSet;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<Data>, Chn extends SortedChain<Data>>
    extends WSetBase<Data, Chn>
    implements OrderedSet<Data> { // Must extend WSetBase and implement OrderedSet; Chn must extend SortedChain

  protected WOrderedSetBase() { super(); }

  protected WOrderedSetBase(Chn chn) { super(chn); }

  protected WOrderedSetBase(TraversableContainer<Data> con) { super(con); }

  protected WOrderedSetBase(Chn chn, TraversableContainer<Data> con) { super(chn, con); }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ... (inherited via WSetBase)

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ... (defaults in OrderedSet)

}
