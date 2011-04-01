package shapes {
    import scala.actors._, Actor._
    import shapes._
    object ShapeDrawingActor extends Actor {  
        def act() {
            loop {
                receive {
                    case s:Shape => 
                        s.draw(); sender ! "drawn."
                    
                    case "exit"  =>
                        println("exiting...")
                        sender ! "good bye!"; exit
                    
                    case x  => 
                        println("Error: " + x)
                        sender ! "Unknown message: " + x
                    
                }
            } } } }
