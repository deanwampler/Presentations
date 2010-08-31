package management
import se.scalablesolutions.akka.actor._

class Manager {
  private var managedActors: List[ActorRef] = Nil
  
  def receive = {
    case Register(actor: ActorRef) => 
      managedActors ::= actor
    case Unregister(actor: ActorRef) => 
      managedActors -= actor
    case Stop  => managedActors foreach (_ ! Stop)
    case Start => managedActors foreach (_ ! Start)
  }
}
