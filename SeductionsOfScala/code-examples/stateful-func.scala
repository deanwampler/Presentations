class Counter[A](val inc: Int) 
   extends  Function1[A,A] {
 var count = 0
 def apply(a:A) = {
   count += inc  
   a   // return the same object
 }
} 

val f = new Counter[String](2)
val list = "a" :: "b" :: Nil
val list2 = list map {s => f(s)}
println(f.count) // 4
println(list2)   // List("a", "b")
