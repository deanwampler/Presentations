@ThreadSafe
public class Sequence {
  @GuardedBy("this") private int value;
  
  /** Returns a unique value. */
  public synchronized int getNext() {
    return value++;
  }
}