package learn.akka.avionics
import learn.akka.misc.EventSource._
import akka.actor.{Props, Actor, ActorLogging}
object Plane {
  // Returns the control surface to the Actor that asks for them
  case object GiveMeControl
}

// We want the Plane to own the Altimeter and we're going to do that
// by passing in a specific factory we can use to build the Altimeter
class Plane extends Actor with ActorLogging {
  import Altimeter._
  import Plane._
  val altimeter = context.actorOf(Props[Altimeter])
  val controls = context.actorOf(Props(new ControlSurfaces(altimeter)))
  def receive = {
    case AltitudeUpdate(altitude) =>
      log.info(s"Altitude is now: $altitude")
    case GiveMeControl =>
      log.info("Plane giving control.")
      sender ! controls
  } 

  override def preStart() {
    altimeter ! RegisterListener(self)
  } 
}