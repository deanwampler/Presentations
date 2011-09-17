class List<T> {
 final T       _head;
 final List<T> _tail;

 T       head() {return _head;}
 List<T> tail() {return _tail;}

 boolean isEmpty() { return false; }

 List (T head, List<T> tail) {
  _head = head;
  _tail = tail;
 }

 public static final List<? extends Object> EMPTY = 
  new List<Object>() {
    public Object   	head() { throw new EmptyListHasNoHead(); } 
    public List<Object> tail() { throw new EmptyListHasNoTail(); } 
    public boolean	 isEmpty() { return true; }
    @Override
    public String toString() { return "()"; } 
  };  
}