import se.scalablesolutions.akka.stm.local._

val ref = Ref(0)        // initialize with 0
def counter = atomic {  // demarcate "atomic" block
  ref alter (_ + 1)     // change value using a function
}
 
counter  // -> 1
counter  // -> 2
ref.get  // -> 2 (retrieve the value explicitly)