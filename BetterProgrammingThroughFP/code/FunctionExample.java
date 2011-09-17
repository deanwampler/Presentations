public class FunctionExample {
  public interface Function1Void<A> {
    void call(A arg);
  }
  
  public static void usex(String x, Function1Void<String> f) {
    f.call(x);
  }
  
  public static void main(String[] args) {
    Function1Void<String> f = new Function1Void<String>() {
      public void call(String arg) {
        System.out.printf("Hello, %s!\n", arg);
      }
    };
    
    usex("Dean", f);
  }
}