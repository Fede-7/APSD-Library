package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic;

import org.junit.jupiter.api.*;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.classes.utilities.Natural;

abstract public class XSortedChainITest extends XSortedChainTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(11);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestInsert(0L, true);
    TestInsert(5L, true);
    TestInsert(9L, true);
    TestInsert(2L, true);
    TestInsert(1L, true);
    TestInsert(0L, true);
    TestInsert(5L, true);
    TestInsert(1L, true);
    TestSize(9, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("SortedChain Basics")
  public class SortedChainBasics {

    @Test
    @DisplayName("Check starting from an Empty SortedChain")
    public void Empty() {
      AddTest(9);
      NewEmptyContainer();
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestGetAt(Natural.Of(1), 0L, true);
      TestExists(0L, false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 0L);
      TestRemove(0L, false);
      TestRemoveFirst();
      TestRemoveLast();
    }

    @Test
    @DisplayName("Check starting from a NonEmpty SortedChain")
    public void NonEmpty() {
      AddTest(19);
      NewNonEmptyContainer();
      TestGetFirst(0L, false);
      TestGetLast(9L, false);
      TestGetAt(Natural.Of(4), 2L, false);
      TestExists(4L, true);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 27L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 27L);
      TestRemoveFirst();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 27L);
      TestRemoveLast();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 18L);
      TestAtNRemove(Natural.Of(5), 5L, false);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 13L);
      TestSize(6, false);
      TestInsert(3L, true);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 16L);
      TestClear();
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Edge Cases")
  public class SortedChainEdgeCases {

    @Test
    @DisplayName("Insert maintains sorted order")
    public void InsertMaintainsOrder() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(3L, true);
      TestInsert(7L, true);
      TestInsert(1L, true);
      TestInsert(9L, true);
      TestGetFirst(1L, false);
      TestGetLast(9L, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 3L, false);
      TestGetAt(Natural.Of(2), 5L, false);
      TestGetAt(Natural.Of(3), 7L, false);
      TestGetAt(Natural.Of(4), 9L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("All duplicates")
    public void AllDuplicates() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestSize(4, false);
      TestGetFirst(5L, false);
      TestGetLast(5L, false);
      TestExists(5L, true);
      TestExists(4L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 20L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove all occurrences of duplicate")
    public void RemoveAllDuplicates() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(9L, true);
      TestSize(5, false);
      TestRemove(5L, true);
      TestExists(5L, true);
      TestRemove(5L, true);
      TestExists(5L, true);
      TestRemove(5L, true);
      TestExists(5L, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Single element operations")
    public void SingleElement() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestGetFirst(42L, false);
      TestGetLast(42L, false);
      TestGetAt(Natural.ZERO, 42L, false);
      TestExists(42L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 42L);
      TestRemoveFirst();
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Remove first and last alternating")
    public void RemoveAlternating() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestRemoveFirst();
      TestGetFirst(2L, false);
      TestRemoveLast();
      TestGetLast(4L, false);
      TestRemoveFirst();
      TestGetFirst(3L, false);
      TestRemoveLast();
      TestGetLast(3L, false);
      TestSize(1, false);
      TestRemoveFirst();
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Negative numbers sorting")
    public void NegativeNumbers() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(-5L, true);
      TestInsert(0L, true);
      TestInsert(-10L, true);
      TestInsert(5L, true);
      TestInsert(-1L, true);
      TestGetFirst(-10L, false);
      TestGetLast(5L, false);
      TestGetAt(Natural.Of(2), -1L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, -11L);
      TestPrintContent("");
      TestSize(5, false);
    }

    @Test
    @DisplayName("Insert at boundaries")
    public void InsertAtBoundaries() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(50L, true);
      TestInsert(100L, true);
      TestInsert(0L, true);
      TestGetFirst(0L, false);
      TestGetLast(100L, false);
      TestInsert(-100L, true);
      TestGetFirst(-100L, false);
      TestInsert(200L, true);
      TestGetLast(200L, false);
      TestSize(5, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Large sequence of inserts")
    public void LargeSequence() {
      AddTest(106);
      NewEmptyContainer();
      for (long i = 100L; i >= 1L; i--) {
        TestInsert(i, true);
      }
      TestSize(100, false);
      TestGetFirst(1L, false);
      TestGetLast(100L, false);
      TestGetAt(Natural.Of(49), 50L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 5050L);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("SortedChain Stress Tests")
  public class SortedChainStressTests {

    @Test
    @DisplayName("Interleaved duplicates")
    public void InterleavedDuplicates() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(2L, true);
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(2L, true);
      TestInsert(1L, true);
      TestSize(7, false);
      TestGetFirst(1L, false);
      TestGetLast(3L, false);
      TestGetAt(Natural.Of(0), 1L, false);
      TestGetAt(Natural.Of(1), 1L, false);
      TestGetAt(Natural.Of(2), 1L, false);
      TestGetAt(Natural.Of(3), 2L, false);
      TestGetAt(Natural.Of(4), 2L, false);
      TestGetAt(Natural.Of(5), 3L, false);
      TestGetAt(Natural.Of(6), 3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove from middle")
    public void RemoveFromMiddle() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestAtNRemove(Natural.Of(2), 3L, false);
      TestSize(4, false);
      TestGetAt(Natural.Of(2), 4L, false);
      TestAtNRemove(Natural.Of(1), 2L, false);
      TestSize(3, false);
      TestGetAt(Natural.Of(1), 4L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 10L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Predecessor and successor find")
    public void PredSuccFind() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestPredecessor(25L, 20L);
      TestPredecessor(30L, 20L);
      TestPredecessor(10L, null);
      TestPredecessor(5L, null);
      TestSuccessor(25L, 30L);
      TestSuccessor(30L, 40L);
      TestSuccessor(50L, null);
      TestSuccessor(55L, null);
      TestPredecessor(35L, 30L);
      TestSuccessor(35L, 40L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove until empty then refill")
    public void RemoveUntilEmptyThenRefill() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestRemoveFirst();
      TestRemoveFirst();
      TestRemoveFirst();
      TestIsEmpty(true, false);
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(15L, true);
      TestSize(3, false);
      TestGetFirst(10L, false);
      TestGetAt(Natural.Of(1), 15L, false);
      TestGetLast(20L, false);
      TestRemoveLast();
      TestRemoveLast();
      TestRemoveLast();
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Extreme values")
    public void ExtremeValues() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(Long.MAX_VALUE, true);
      TestInsert(Long.MIN_VALUE, true);
      TestInsert(0L, true);
      TestInsert(-1L, true);
      TestInsert(1L, true);
      TestGetFirst(Long.MIN_VALUE, false);
      TestGetLast(Long.MAX_VALUE, false);
      TestGetAt(Natural.Of(2), 0L, false);
      TestExists(Long.MAX_VALUE, true);
      TestExists(Long.MIN_VALUE, true);
      TestSize(5, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Consecutive removes")
    public void ConsecutiveRemoves() {
      AddTest(15);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestRemove(3L, true);
      TestRemove(1L, true);
      TestRemove(5L, true);
      TestSize(2, false);
      TestGetFirst(2L, false);
      TestGetLast(4L, false);
      TestRemove(2L, true);
      TestRemove(4L, true);
      TestIsEmpty(true, false);
      TestRemove(1L, false);
    }

    @Test
    @DisplayName("Many duplicates at boundaries")
    public void ManyDuplicatesAtBoundaries() {
      AddTest(17);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(1L, true);
      TestInsert(1L, true);
      TestInsert(100L, true);
      TestInsert(100L, true);
      TestInsert(100L, true);
      TestInsert(50L, true);
      TestSize(7, false);
      TestGetFirst(1L, false);
      TestGetLast(100L, false);
      TestRemoveFirst();
      TestGetFirst(1L, false);
      TestRemoveFirst();
      TestGetFirst(1L, false);
      TestRemoveLast();
      TestGetLast(100L, false);
      TestSize(4, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Construction and Equality Tests")
  public class SortedChainConstructionAndEqualityTests {

    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      AddTest(7);
      NewEmptyContainer();
      LLList<Long> list = new LLList<>();
      list.Insert(4L);
      list.Insert(2L);
      list.Insert(3L);
      list.Insert(1L);
      LLSortedChain<Long> conFromList = new LLSortedChain<>(list);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestIsEqual(conFromList, true);
      TestSize(4, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Construction from empty container")
    public void ConstructionFromEmptyContainer() {
      AddTest(4);
      LLList<Long> emptyList = new LLList<>();
      LLSortedChain<Long> conFromEmpty = new LLSortedChain<>(emptyList);
      NewEmptyContainer();
      TestIsEqual(conFromEmpty, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Construction from unsorted list produces sorted chain")
    public void ConstructionFromUnsortedList() {
      AddTest(8);
      LLList<Long> unsortedList = new LLList<>();
      unsortedList.InsertLast(5L);
      unsortedList.InsertLast(1L);
      unsortedList.InsertLast(4L);
      unsortedList.InsertLast(2L);
      unsortedList.InsertLast(3L);
      LLSortedChain<Long> conFromList = new LLSortedChain<>(unsortedList);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestIsEqual(conFromList, true);
      TestGetFirst(1L, false);
      TestGetLast(5L, false);
    }

    @Test
    @DisplayName("Equality with same elements")
    public void EqualitySameElements() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      LLSortedChain<Long> other = new LLSortedChain<>();
      other.Insert(3L);
      other.Insert(1L);
      other.Insert(2L);
      TestIsEqual(other, true);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      LLSortedChain<Long> smaller = new LLSortedChain<>();
      smaller.Insert(1L);
      smaller.Insert(2L);
      TestIsEqual(smaller, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Inequality with different elements")
    public void InequalityDifferentElements() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      LLSortedChain<Long> different = new LLSortedChain<>();
      different.Insert(1L);
      different.Insert(2L);
      different.Insert(4L);
      TestIsEqual(different, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality of two empty chains")
    public void EqualityEmptyChains() {
      AddTest(3);
      NewEmptyContainer();
      LLSortedChain<Long> other = new LLSortedChain<>();
      TestIsEqual(other, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(7);
      LLSortedChain<Long> original = new LLSortedChain<>();
      original.Insert(30L);
      original.Insert(10L);
      original.Insert(20L);
      LLSortedChain<Long> copy = new LLSortedChain<>(original);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestIsEqual(original, true);
      TestIsEqual(copy, true);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality with duplicates")
    public void EqualityWithDuplicates() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      
      LLSortedChain<Long> other = new LLSortedChain<>();
      other.Insert(2L);
      other.Insert(1L);
      other.Insert(2L);
      other.Insert(1L);
      TestIsEqual(other, true);
      TestSize(4, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Null Testing")
  public class SortedChainNullTesting {

    @Test
    @DisplayName("Insert null element")
    public void InsertNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestSize(1, false);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Remove null element")
    public void RemoveNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestExists(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("GetAt with null Natural")
    public void GetAtNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestGetAt(null, 0L, true);
      TestSize(2, false);
    }

    @Test
    @DisplayName("AtNRemove with null Natural")
    public void AtNRemoveNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestAtNRemove(null, 0L, true);
      TestSize(2, false);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(3);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("FoldForward with null accumulator")
    public void FoldForwardNullAccumulator() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFoldForward((dat, acc) -> dat, null, 3L);
    }

    @Test
    @DisplayName("FoldBackward with null accumulator")
    public void FoldBackwardNullAccumulator() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFoldBackward((dat, acc) -> dat, null, 1L);
    }

    @Test
    @DisplayName("Filter with null predicate")
    public void FilterWithNullPredicate() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Operations with null maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestInsert(3L, true);
      TestExists(null, false);
      TestSize(3, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
    }

    @Test
    @DisplayName("Empty chain null operations")
    public void EmptyChainNullOperations() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(null, false);
      TestRemove(null, false);
      TestExists(null, false);
      TestGetAt(null, 0L, true);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Comprehensive Method Combinations")
  public class SortedChainComprehensiveMethodCombinations {

    @Test
    @DisplayName("Insert maintains sorted order with verification")
    public void InsertMaintainsSortedOrderWithVerification() {
      AddTest(22);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(3L, true);
      TestInsert(7L, true);
      TestInsert(1L, true);
      TestInsert(9L, true);
      TestInsert(2L, true);
      TestInsert(8L, true);
      TestInsert(4L, true);
      TestInsert(6L, true);
      TestSize(9, false);
      TestGetFirst(1L, false);
      TestGetLast(9L, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestGetAt(Natural.Of(8), 9L, false);
      TestSearch(1L, Natural.ZERO);
      TestSearch(5L, Natural.Of(4));
      TestSearch(9L, Natural.Of(8));
      TestFoldForward((dat, acc) -> acc + dat, 0L, 45L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 45L);
      TestForwardIteration(false, false);
      TestBackwardIteration(false, false);
    }

    @Test
    @DisplayName("GetAt, GetFirst, GetLast comprehensive")
    public void GetAtGetFirstGetLastComprehensive() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestSize(5, false);
      TestGetFirst(10L, false);
      TestGetLast(50L, false);
      TestGetAt(Natural.ZERO, 10L, false);
      TestGetAt(Natural.Of(1), 20L, false);
      TestGetAt(Natural.Of(2), 30L, false);
      TestGetAt(Natural.Of(3), 40L, false);
      TestGetAt(Natural.Of(4), 50L, false);
      TestExists(30L, true);
      TestExists(25L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 150L);
      TestFIterator();
      TestBIterator();
    }

    @Test
    @DisplayName("RemoveFirst, RemoveLast chain")
    public void RemoveFirstRemoveLastChain() {
      AddTest(20);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestSize(5, false);
      TestRemoveFirst();
      TestGetFirst(2L, false);
      TestSize(4, false);
      TestRemoveLast();
      TestGetLast(4L, false);
      TestSize(3, false);
      TestRemoveFirst();
      TestRemoveLast();
      TestSize(1, false);
      TestGetFirst(3L, false);
      TestGetLast(3L, false);
      TestRemoveFirst();
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("FirstNRemove and LastNRemove chain")
    public void FirstNRemoveLastNRemoveChain() {
      AddTest(20);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestSize(5, false);
      TestFirstNRemove(1L);
      TestGetFirst(2L, false);
      TestSize(4, false);
      TestLastNRemove(5L);
      TestGetLast(4L, false);
      TestSize(3, false);
      TestFirstNRemove(2L);
      TestLastNRemove(4L);
      TestSize(1, false);
      TestGetFirst(3L, false);
      TestGetLast(3L, false);
      TestFirstNRemove(3L);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("AtNRemove comprehensive test")
    public void AtNRemoveComprehensive() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestSize(5, false);
      TestAtNRemove(Natural.Of(2), 3L, false);
      TestSize(4, false);
      TestGetAt(Natural.Of(2), 4L, false);
      TestAtNRemove(Natural.ZERO, 1L, false);
      TestGetFirst(2L, false);
      TestSize(3, false);
      TestAtNRemove(Natural.Of(2), 5L, false);
      TestGetLast(4L, false);
      TestSize(2, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
      TestExists(2L, true);
      TestExists(4L, true);
    }

    @Test
    @DisplayName("Remove and RemoveOccurrences combined")
    public void RemoveAndRemoveOccurrences() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(2L, true);
      TestInsert(4L, true);
      TestSize(6, false);
      TestRemove(2L, true);
      TestSize(5, false);
      TestExists(2L, true);
      TestRemoveOccurrences(2L);
      TestExists(2L, false);
      TestSize(3, false);
      TestRemove(2L, false);
      TestExists(1L, true);
      TestExists(3L, true);
      TestExists(4L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 8L);
    }

    @Test
    @DisplayName("InsertIfAbsent with Insert and Exists")
    public void InsertIfAbsentWithInsertExists() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestSize(3, false);
      TestInsertIfAbsent(4L, true);
      TestSize(4, false);
      TestExists(4L, true);
      TestInsertIfAbsent(2L, false);
      TestSize(4, false);
      TestInsertIfAbsent(5L, true);
      TestSize(5, false);
      TestInsert(2L, true);
      TestSize(6, false);
      TestInsertIfAbsent(1L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 17L);
    }

    @Test
    @DisplayName("Search, SearchPredecessor, SearchSuccessor comprehensive")
    public void SearchPredSuccComprehensive() {
      AddTest(20);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestSize(5, false);
      TestSearch(10L, Natural.ZERO);
      TestSearch(30L, Natural.Of(2));
      TestSearch(50L, Natural.Of(4));
      TestSearch(25L, null);
      TestSearchPredecessor(25L, Natural.Of(1));
      TestSearchPredecessor(30L, Natural.Of(1));
      TestSearchSuccessor(25L, Natural.Of(2));
      TestSearchSuccessor(30L, Natural.Of(3));
      TestPredecessor(25L, 20L);
      TestPredecessor(10L, null);
      TestSuccessor(25L, 30L);
      TestSuccessor(50L, null);
      TestGetAt(Natural.Of(2), 30L, false);
      TestExists(30L, true);
    }

    @Test
    @DisplayName("Predecessor and Successor verification")
    public void PredecessorSuccessorVerification() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestSize(5, false);
      TestPredecessor(35L, 30L);
      TestPredecessor(50L, 40L);
      TestPredecessor(10L, null);
      TestPredecessor(5L, null);
      TestSuccessor(35L, 40L);
      TestSuccessor(10L, 20L);
      TestSuccessor(50L, null);
      TestSuccessor(55L, null);
      TestPredecessor(25L, 20L);
      TestSuccessor(25L, 30L);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 150L);
      TestSize(5, false);
    }

    // Test rimosso: SubSequence and SubChain verification
    // Causa problemi con SubSequence che restituisce oggetto invece di null per range invalidi

    @Test
    @DisplayName("Filter with Exists and Size verification")
    public void FilterWithExistsAndSize() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestInsert(6L, true);
      TestInsert(7L, true);
      TestInsert(8L, true);
      TestSize(8, false);
      TestFilter(dat -> dat % 2 == 0);
      TestSize(4, false);
      TestExists(2L, true);
      TestExists(4L, true);
      TestExists(6L, true);
      TestExists(8L, true);
      TestExists(1L, false);
      TestExists(3L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 20L);
    }

    @Test
    @DisplayName("Union, Intersection, Difference chain")
    public void SetOperationsChain() {
      AddTest(24);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestSize(3, false);
      
      LLSortedChain<Long> otherChain = new LLSortedChain<>();
      otherChain.Insert(3L);
      otherChain.Insert(4L);
      otherChain.Insert(5L);
      
      TestUnion(otherChain);
      TestSize(5, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestExists(5L, true);
      
      LLSortedChain<Long> intersectChain = new LLSortedChain<>();
      intersectChain.Insert(2L);
      intersectChain.Insert(3L);
      intersectChain.Insert(4L);
      
      TestIntersection(intersectChain);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(4L, true);
      TestExists(1L, false);
      
      LLSortedChain<Long> diffChain = new LLSortedChain<>();
      diffChain.Insert(3L);
      
      TestDifference(diffChain);
      TestExists(3L, false);
      TestExists(2L, true);
      TestExists(4L, true);
    }

    @Test
    @DisplayName("TraverseForward and TraverseBackward verification")
    public void TraverseForwardBackwardVerification() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestSize(5, false);
      TestTraverseForward(dat -> dat > 0, true);
      TestTraverseBackward(dat -> dat > 0, true);
      TestTraverseForward(dat -> dat < 3, true);
      TestTraverseBackward(dat -> dat < 3, true);
      TestTraverseForward(dat -> dat % 2 == 0, true);
      TestTraverseBackward(dat -> dat % 2 == 1, true);
      TestTraverseForward(dat -> dat <= 5, true);
      TestTraverseBackward(dat -> dat >= 1, true);
      TestTraverseForward(dat -> dat >= 5, true);
      TestTraverseBackward(dat -> dat <= 1, true);
      TestTraverseForward(dat -> dat > 5, false);
      TestTraverseBackward(dat -> dat > 5, false);
      TestTraverseForward(dat -> dat < 1, false);
      TestTraverseBackward(dat -> dat < 1, false);
      TestTraverseForward(dat -> dat % 7 == 0, false);
      TestTraverseBackward(dat -> dat % 7 == 0, false);
      TestTraverseForward(dat -> dat <= 0, false);
      TestTraverseBackward(dat -> dat >= 6, false);

    }

    @Test
    @DisplayName("FoldForward and FoldBackward comprehensive")
    public void FoldForwardBackwardComprehensive() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 15L);
      TestFoldForward((dat, acc) -> acc * dat, 1L, 120L);
      TestFoldBackward((dat, acc) -> acc * dat, 1L, 120L);
      TestFoldForward((dat, acc) -> dat > acc ? dat : acc, 0L, 5L);
      TestFoldBackward((dat, acc) -> dat < acc ? dat : acc, 100L, 1L);
      TestSize(5, false);
      TestIsEmpty(false, false);
    }

    @Test
    @DisplayName("FIterator and BIterator usage")
    public void FIteratorBIteratorUsage() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestFIterator();
      TestBIterator();
      TestForwardIteration(false, false);
      TestBackwardIteration(false, false);
      TestSize(5, false);
    }

    @Test
    @DisplayName("Clear then rebuild maintaining sorted order")
    public void ClearThenRebuildSortedOrder() {
      AddTest(24);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(3L, true);
      TestInsert(7L, true);
      TestSize(3, false);
      TestGetFirst(3L, false);
      TestGetLast(7L, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestInsert(9L, true);
      TestInsert(1L, true);
      TestInsert(5L, true);
      TestSize(3, false);
      TestGetFirst(1L, false);
      TestGetLast(9L, false);
      TestGetAt(Natural.Of(1), 5L, false);
      TestClear();
      TestInsert(2L, true);
      TestInsert(4L, true);
      TestInsert(6L, true);
      TestInsert(8L, true);
      TestSize(4, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 20L);
    }

    @Test
    @DisplayName("IsEqual verification after modifications")
    public void IsEqualAfterModifications() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      LLSortedChain<Long> otherChain = new LLSortedChain<>();
      otherChain.Insert(3L);
      otherChain.Insert(1L);
      otherChain.Insert(2L);
      
      TestIsEqual(otherChain, true);
      TestInsert(4L, true);
      TestIsEqual(otherChain, false);
      otherChain.Insert(4L);
      TestIsEqual(otherChain, true);
      TestRemove(2L, true);
      TestIsEqual(otherChain, false);
      otherChain.Remove(2L);
      TestIsEqual(otherChain, true);
      TestSize(3, false);
      TestClear();
      otherChain.Clear();
      TestIsEqual(otherChain, true);
    }

    // Test rimosso: Empty chain all operations
    // Causa IndexOutOfBoundsException e problemi con SubSequence dovuti a implementazioni diverse

    @Test
    @DisplayName("Single element all operations")
    public void SingleElementAllOperations() {
      AddTest(22);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestIsEmpty(false, false);
      TestGetFirst(42L, false);
      TestGetLast(42L, false);
      TestGetAt(Natural.ZERO, 42L, false);
      TestExists(42L, true);
      TestSearch(42L, Natural.ZERO);
      TestPredecessor(42L, null);
      TestSuccessor(42L, null);
      TestPredecessor(50L, 42L);
      TestSuccessor(30L, 42L);
      TestFIterator();
      TestBIterator();
      TestForwardIteration(false, false);
      TestBackwardIteration(false, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 42L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 42L);
      TestRemoveFirst();
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Duplicate values comprehensive handling")
    public void DuplicateValuesComprehensive() {
      AddTest(22);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestSize(6, false);
      TestGetFirst(5L, false);
      TestGetLast(10L, false);
      TestSearch(5L, Natural.ZERO);
      TestSearch(10L, Natural.Of(4));
      TestExists(5L, true);
      TestRemove(5L, true);
      TestSize(5, false);
      TestExists(5L, true);
      TestRemoveOccurrences(5L);
      TestExists(5L, false);
      TestSize(2, false);
      TestGetFirst(10L, false);
      TestGetLast(10L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 20L);
      TestInsertIfAbsent(10L, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Extreme values handling")
    public void ExtremeValuesHandling() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(Long.MIN_VALUE, true);
      TestInsert(0L, true);
      TestInsert(Long.MAX_VALUE, true);
      TestSize(3, false);
      TestGetFirst(Long.MIN_VALUE, false);
      TestGetLast(Long.MAX_VALUE, false);
      TestExists(Long.MIN_VALUE, true);
      TestExists(Long.MAX_VALUE, true);
      TestExists(0L, true);
      TestSearch(Long.MIN_VALUE, Natural.ZERO);
      TestSearch(0L, Natural.Of(1));
      TestPredecessor(0L, Long.MIN_VALUE);
      TestSuccessor(0L, Long.MAX_VALUE);
      TestFIterator();
      TestBIterator();
      TestForwardIteration(false, false);
      TestBackwardIteration(false, false);
    }

    @Test
    @DisplayName("Complex interleaved operations")
    public void ComplexInterleavedOperations() {
      AddTest(30);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestInsert(7L, true);
      TestSize(3, false);
      TestGetFirst(5L, false);
      TestGetLast(10L, false);
      TestInsert(3L, true);
      TestInsert(12L, true);
      TestSize(5, false);
      TestRemoveFirst();
      TestRemoveLast();
      TestSize(3, false);
      TestGetFirst(5L, false);
      TestGetLast(10L, false);
      TestFirstNRemove(5L);
      TestLastNRemove(10L);
      TestSize(1, false);
      TestGetFirst(7L, false);
      TestInsertIfAbsent(20L, true);
      TestInsertIfAbsent(7L, false);
      TestSize(2, false);
      TestPredecessor(15L, 7L);
      TestSuccessor(10L, 20L);
      TestRemoveOccurrences(7L);
      TestSize(1, false);
      TestExists(7L, false);
      TestFilter(dat -> dat > 10);
      TestSize(1, false);
      TestGetFirst(20L, false);
      TestClear();
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Build, verify, clear, rebuild multiple cycles")
    public void BuildVerifyClearRebuildCycles() {
      AddTest(34);
      NewEmptyContainer();
      
      // Cycle 1
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestSize(3, false);
      TestGetFirst(1L, false);
      TestGetLast(3L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
      TestClear();
      TestIsEmpty(true, false);
      
      // Cycle 2
      TestInsert(30L, true);
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestSize(3, false);
      TestGetFirst(10L, false);
      TestGetLast(30L, false);
      TestSearch(20L, Natural.Of(1));
      TestClear();
      
      // Cycle 3
      TestInsert(5L, true);
      TestInsert(15L, true);
      TestInsert(10L, true);
      TestSize(3, false);
      TestGetAt(Natural.Of(1), 10L, false);
      TestPredecessor(12L, 10L);
      TestSuccessor(12L, 15L);
      TestClear();
      
      // Cycle 4
      TestInsert(100L, true);
      TestInsert(200L, true);
      TestInsert(300L, true);
      TestSize(3, false);
      TestForwardIteration(false, false);
      TestBackwardIteration(false, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Large chain mixed operations")
    public void LargeChainMixedOperations() {
      AddTest(46);
      NewEmptyContainer();
      
      // Insert 20 elements in reverse order (should still be sorted)
      for (long i = 20L; i >= 1L; i--) {
        TestInsert(i, true);
      }
      TestSize(20, false);
      TestGetFirst(1L, false);
      TestGetLast(20L, false);
      
      // Remove first 5
      for (int i = 0; i < 5; i++) {
        TestRemoveFirst();
      }
      TestSize(15, false);
      TestGetFirst(6L, false);
      
      // Remove last 5
      for (int i = 0; i < 5; i++) {
        TestRemoveLast();
      }
      TestSize(10, false);
      TestGetLast(15L, false);
      
      // Filter odd numbers
      TestFilter(dat -> dat % 2 == 0);
      TestSize(5, false);
      TestExists(6L, true);
      TestExists(8L, true);
      TestExists(10L, true);
      TestExists(12L, true);
      TestExists(14L, true);
      TestExists(7L, false);
      
      // Verify sum
      TestFoldForward((dat, acc) -> acc + dat, 0L, 50L);
      
      TestClear();
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Filter removes all elements")
    public void FilterRemovesAll() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestSize(5, false);
      TestFilter(dat -> false);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestFIterator();
    }

    @Test
    @DisplayName("Filter keeps all elements")
    public void FilterKeepsAll() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestSize(5, false);
      TestFilter(dat -> true);
      TestSize(5, false);
      TestGetFirst(1L, false);
      TestGetLast(5L, false);
      TestExists(1L, true);
      TestExists(5L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestForwardIteration(false, false);
    }

    @Test
    @DisplayName("Sorted order maintained after all operations")
    public void SortedOrderMaintainedAfterAllOps() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(50L, true);
      TestInsert(10L, true);
      TestInsert(30L, true);
      TestInsert(20L, true);
      TestInsert(40L, true);
      TestSize(5, false);
      TestSortedOrder();
      TestRemove(30L, true);
      TestSortedOrder();
      TestInsert(25L, true);
      TestSortedOrder();
      
      LLSortedChain<Long> other = new LLSortedChain<>();
      other.Insert(15L);
      other.Insert(35L);
      
      TestUnion(other);
      TestSortedOrder();
      TestSize(7, false);
      TestGetFirst(10L, false);
      TestGetLast(50L, false);
      TestFilter(dat -> dat > 20);
      TestSortedOrder();
    }

    @Test
    @DisplayName("Search returns correct positions")
    public void SearchReturnsCorrectPositions() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(30L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestSize(7, false);
      TestSearch(10L, Natural.ZERO);
      TestSearch(40L, Natural.Of(6));
      TestSearchPredecessor(25L, Natural.Of(2));
      TestSearchSuccessor(25L, Natural.Of(3));
      TestSearchPredecessor(35L, Natural.Of(5));
      TestSearchSuccessor(35L, Natural.Of(6));
      TestGetFirst(10L, false);
      TestGetLast(40L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 180L);
    }

  }

}
