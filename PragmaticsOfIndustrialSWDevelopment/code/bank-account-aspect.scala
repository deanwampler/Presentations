// Schematic AspectJ with mixed Scala syntax:
public aspect PersisentCustomer {
  public pointcut setName(Customer c, String newName) :
    call(Customer.name_=(*)) && this(c) && args(newName);

  public pointcut getName(Customer c) :
    call(Customer.name()) && this(c);
  
  after(Customer c, String newName) returning : setName(c, newName) {
    // Use a singleton version of Persister
    PersisterSingleton.putToDatabase(c, "name", newName);
  }
  
  before(Customer c) : getName(c) {
    PersisterSingleton.getFromDatabase(c, "name") match {
      case Some(x) => c.name = x
      case None =>
    }
  }
}