package learn.akka.misc

import akka.actor.{Actor, ActorRef}
object EventSource {
  // Messages used by listeners to register and unregister themselves
  case class RegisterListener(listener: ActorRef)
  case class UnregisterListener(listener: ActorRef)
}

trait EventSource { this: Actor =>
  import EventSource._
  // We're going to use a Vector but many structures would be adequate
  var listeners = Vector.empty[ActorRef]
  // Sends the event to all of our listeners
  def sendEvent[T](event: T): Unit = listeners foreach { _ ! event }
  // We create a specific partial function to handle the messages for
  // our event listener.  Anything that mixes in our trait will need to
  // compose this receiver
  def eventSourceReceive: Receive = {
    case RegisterListener(listener) =>
      listeners = listeners :+ listener
    case UnregisterListener(listener) =>
      listeners = listeners filter { _ != listener }
  }
}