package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data> {

  protected final List<Data> lst;

  public WStack() { this.lst = new VList<>(); }

  public WStack(List<Data> lst) {
    if (lst == null) throw new NullPointerException("List cannot be null!");
    this.lst = lst;
  }

  public WStack(TraversableContainer<Data> con) {
    if (con == null) throw new NullPointerException("TraversableContainer cannot be null!");
    this.lst = new VList<>(con);
  }

  public WStack(List<Data> lst, TraversableContainer<Data> con) {
    if (lst == null) throw new NullPointerException("List cannot be null!");
    if (con == null) throw new NullPointerException("TraversableContainer cannot be null!");
    this.lst = lst;
    con.TraverseForward(dat -> { this.lst.Insert(dat); return false; });
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size() { return lst.Size(); }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() { lst.Clear(); }

  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */

  @Override
  public Data Top() { return lst.IsEmpty() ? null : lst.GetFirst(); }

  @Override
  public void Pop() {
    if (lst.IsEmpty()) return;
    lst.RemoveFirst();
  }

  @Override
  public void Push(Data dat) {
    if (dat == null) throw new IllegalArgumentException("Data cannot be null");
    lst.InsertFirst(dat);
  }

  @Override
  public Data TopNPop() {
    if (lst.IsEmpty()) return null;
    final Data top = Top();
    Pop();
    return top;
  }

  @Override
  public void SwapTop(Data dat) {
    if (lst.IsEmpty()) return;
    Pop();
    Push(dat);
  }

  @Override
  public Data TopNSwap(Data dat) {
    if (lst.IsEmpty()) return null;
    final Data top = Top();
    SwapTop(dat);
    return top;
  }
}
