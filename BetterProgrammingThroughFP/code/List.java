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
}