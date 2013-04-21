case class Event(evntStr: String) 
case class Query(event: Event) 
case class Result(result: String) 

trait Logging {
  def log(message: String)
}

trait UI { this: Service with Logging =>
  def userEvent(e:Event) = {
    log("UI: "+e)
    val result = handleEvent(e)
    display(result)
  }
  protected def display(result: Result): Unit
}

trait Service { this: Datastore with Logging =>
  def handleEvent(e:Event) = {
    log("Service: "+e)
    query(Query(e))
  }
}

trait Datastore { this: Logging =>
  def query(query: Query) = {
    log("DS: "+query)
    doQuery(query)
  }
  protected def doQuery(query: Query): Result
}


val app = new App with UI with Service with Datastore with Logging {
  def log(message: String) = println("Log: "+message)
  def display(result: Result) = println("Result: "+result)
  def doQuery(query: Query): Result = {
    println("Query: "+query)
    Result("Success!!")
  }
}

app.userEvent(Event("Do a search!"))
