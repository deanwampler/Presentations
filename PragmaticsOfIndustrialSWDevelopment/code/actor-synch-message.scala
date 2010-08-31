class Manager {
  ...  
  def receive = {
    ...
    case Stop  => managedActors foreach (_ ! Stop)
    case Start => managedActors foreach (_ ! Start)
    case Ping  => managedActors map { _ =>
      (_ !! Ping).getOrElse(error(...))
    }
  }
}
