trait Counter[A]
   extends  Function1[A,A] {
 val inc   = 1
 var count = 0
 abstract override def apply(a:A) = {
   count += inc
   super.apply(a)
 }
} 

val f = new Function1[String,String] with Counter[String] {
  override val inc = 2
  def apply(s: String) = s.toUpperCase
}
val list = "a" :: "b" :: Nil
val list2 = list map {s => f(s)}
println(f.count) // 4
println(list2)   // List("a" :: "b")
