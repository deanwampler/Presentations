trait KeyValuePersister[K,V] {
  def getFromDataBase(key: K): Option[V] = { /* nasty */ }
  def putToDatabase(key: K, newValue: V): Option[V] = { /* nasty */ }
}

trait PersistentMap[K,V] extends scala.collection.mutable.Map[K,V] with KeyValuePersister[K,V] {
  override def get(key: K) = {
    val newValue = getFromDataBase(key)  // avoid stale data
    newValue match {
      case Some(x) => super.put(key, x)
      case None =>
    }
    newValue
  }
  override def put(key: K, newValue: V) = {
    super.put(key, newValue)
    putToDatabase(key, newValue)
  }
}

val dean = new scala.collection.mutable.HashMap[String,Any] with PersistentMap[String,Any]
dean.put("name", "Dean")