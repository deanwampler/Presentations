import se.scalablesolutions.akka.stm.local._

val ten = 10
val ref = Ref(1) // initialize with 1 
atomic {  
  ref set 10     // set new value
}                // (actually returns old value)
ref.get          // -> 10