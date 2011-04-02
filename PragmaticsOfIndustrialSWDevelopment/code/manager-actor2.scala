class Manager {
  private var managedActors: List[ActorRef] = Nil
  
  def receive = { ... }
  }
}

...
import se.scalablesolutions.akka.actor.Actor_
// Creating an actor (one way...) and starting
val manager = Actor.actorOf[Manager]
manager.start