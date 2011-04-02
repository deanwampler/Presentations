trait Named { 
  def name: String
  def name_=(n:String):Unit
}
case class Customer(var name: String) extends Named

class InstrumentedCustomer(customer: Customer) 
    extends Named with Persister {
  def name = {
    getFromDataBase("name") match {  // avoid stale data
      case Some(x) => customer.name = x   // call customer.name_=()
      case None =>
    }
    customer.name  // call customer.name method
  }
  def name_=(newName: String) = {
    customer.name = newName
    putToDatabase("name", newName)
  }
}