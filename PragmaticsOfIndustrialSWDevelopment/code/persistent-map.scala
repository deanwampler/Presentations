trait KeyValuePersister[K,V] {
  def getFromDataBase(key: K): Option[V] = { /* nasty */ }
  def putToDatabase(key: K, value: V): Option[V] = { /* nasty */ }
}

import scala.collection.mutable._
trait PersistentMap[K,V] extends Map[K,V] with KeyValuePersister[K,V] {
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

val dean = new HashMap[String,Any] with PersistentMap[String,Any]
dean.put("name", "Dean")