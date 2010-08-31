package management
import se.scalablesolutions.akka.actor._

sealed trait ManagementMessage

case class  Register(actor: ActorRef)
case class  Unregister(actor: ActorRef)
case object Stop
case object Start
