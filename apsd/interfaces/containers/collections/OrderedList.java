package apsd.interfaces.containers.collections;

public interface OrderedList<Data extends Comparable<Data>> extends List<Data>, OrderedChain<Data>{}
