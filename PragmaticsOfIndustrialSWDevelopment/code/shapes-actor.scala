package shapes {
    import scala.actors._, Actor._
    import shapes._
    object ShapeDrawingActor extends Actor {  
        def act() {
            loop {
                receive {
                    case (sender: Actor, s:Shape) => {
                        s.draw()
                        sender ! "drawn."
                    }
                    case (sender: Actor, x)  => {
                        println("Error: " + x)
                        sender ! "Unknown message: " + x
                    }
                }
            } } } }
