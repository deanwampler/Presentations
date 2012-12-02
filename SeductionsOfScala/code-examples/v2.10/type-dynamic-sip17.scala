// Dynamic Typing
// New to Scala 2.10 (SIP-17)
// https://docs.scala-lang.org/sips/pending/type-dynamic.html

// You can only use the dynamic feature if you start scala with
// the language feature enabled:
//   scala -language:dynamic ...
// OR, you import the following:
 
import scala.language.dynamics

trait Updateable {
  def update(index: Int, value: Int)
}

case object Dyno extends Dynamic {
  def applyDynamic(methodName: String)(args: Any*) = {
    val args2 = args.map(_.toString).reduceLeft[String] { case (result:String, x:String) => s"$result, $x" }
    println(s"Dyno.applyDynamic: $methodName called with args: $args2")
  }
  def applyDynamicNamed(methodName: String)(args: (String,Any)*) = {
    val args2 = args map { case (name, value) => "%s = %s".format(name, value) }
    println(s"Dyno.applyDynamicNamed: $methodName called with args: $args2")
  }
  def selectDynamic(fieldName: String): Updateable = fieldName match {
    case "array" =>  
      new Updateable { def update(index: Int, value: Int) = ary(index) = value }
    case _ => 
      println(s"Dyno.selectDynamic called for field $fieldName")    
      new Updateable { def update(index: Int, value: Int) = 
        println(s"--- ERROR --- $fieldName.update($index, $value) called!")
      }
  }
  def updateDynamic(fieldName: String)(value: Any) = {
    println(s"Dyno.updateDynamic: $fieldName called with arg: $value")    
  }

  var ary = Array(0,1,2,3,4,5,6,7,8,9)
}

val d = Dyno
d.method1
d.method1("value1")
d.method1(arg1 = "value1")
d.method1(arg  = "value1", "value2")
d.field1
d.field1 = "hello!"
d.array(3)
d.array(3) = 30
d.invalid(3) = 30
