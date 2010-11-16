trait Persister {
  def getFromDataBase[A](key: String): Option[A] =  { /* nasty */ }
  // Returns optional previous value (like Scala's mutable map.put())
  def putToDatabase[A](key: String, value: A): Option[A] =  { /* nasty */ }
}
...
case class Customer(initialName: String) extends Persister{
  protected var _name = initialName
  def name = {
    _name = getFromDataBase("name")  // avoid stale data
    _name
  }
  def name_=(newName: String) = {
    _name = newName
    putToDatabase("name", _name)
  }
}