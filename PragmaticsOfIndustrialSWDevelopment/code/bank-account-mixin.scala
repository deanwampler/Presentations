trait Persister {
  def getFromDataBase[A](propertyName: String): Option[A] =  { /* nasty */ }
  def putToDatabase[A](propertyName: String, newValue: A): Option[A] =  { /* nasty */ }
}

trait Named { 
  def name: String
  def name_=(n:String):Unit
}

trait PersistentName extends Named with Persister {
  def name = {
    getFromDataBase("name") match {  // avoid stale data
      case Some(x) => super.name = x   // call parent's setter
      case None =>
    }
    super.name  // call parent's getter
  }
  def name_=(newName: String) = {
    super.name = newName
    putToDatabase("name", newName)
  }
}

case class Customer(var name: String) extends Named

val dean = new Customer("Dean") with PersistentName