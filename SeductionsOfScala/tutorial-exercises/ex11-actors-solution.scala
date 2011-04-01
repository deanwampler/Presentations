// Exercise 11: Actors
// Starting with the Actors example, modify it to have the driver actor send
// a shape, then wait for a reply, after which it sends the next shape, etc.
// Put in a sleep call or other delay to slow it down.
// Also, change the shape hierarchy; add at least one more shape type.
// Add more “control” messages (in addition to "exit").
// You can run this file with "scala ex11-actors-solution.scala"

// In this solution, I've add a Triangle shape and modify the driver actor
// to loop, sending shapes from a list of shapes, along with other commands.
// These messages are sent one at a time, after which the driver waits for a
// response from the drawing actor before sending the next message.
// I've also added a new "control" message. It configures the format used for
// the response message sent back from the drawing actor.
// Also, I deleted the package declarations so we can put everything in one script file.

case class Point(x: Double, y: Double)  

abstract case class Shape() { def draw() = println(this) }  
case class Circle(center: Point, radius: Double) extends Shape  
case class Rectangle(lowerLeft: Point, h: Double, w: Double) extends Shape
case class Triangle(one: Point, two: Point, three: Point) extends Shape

import scala.actors._, Actor._

object ShapeDrawingActor extends Actor {  
  // Use a mutable variable for the string format used to create responses.
  var responseFormat = "response: \"%s\", original message: \"%s\""
  
  def makeResponse(response: String, originalMessage: Any) = 
    String.format(responseFormat, response, originalMessage.toString)
  
  def act() {
    loop {
      react {
        case s:Shape => 
          s.draw()
          sender ! makeResponse("drawn!", s)
        
        case "exit"  =>
          println("exiting...")
          sender ! makeResponse("good bye!", "exit")
          //exit
        
        case ("new-response-format", format:String)  =>
          responseFormat = format
          sender ! makeResponse("Using new response format.", ("new-response-format", format))

        case x  => 
          println("Error: " + x)
          sender ! makeResponse("Unknown message!", x)
      }
    }
  } 
}

// Need to modify the "driver" to be truly asynchronous, so we make an Actor
// object.

object DriverActor extends Actor {  
  // List of messages we'll send.
  val messages = 
    Circle(Point(0.0,0.0), 1.0) ::
    Rectangle(Point(0.0,0.0), 2, 5) ::
    Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,1.0)) ::
    ("new-response-format", "resp: \"%s\", msg: \"%s\"") ::
    Circle(Point(1.0,1.0), 2.0) ::
    Rectangle(Point(3.0,3.0), 4, 6) ::
    Triangle(Point(2.0,0.0), Point(4.0,0.0), Point(3.0,1.0)) ::
    3.14159 ::
    "exit" :: Nil

  // Use "index" to keep track of which message goes next. Can you come up with
  // an alternative implementation that doesn't require a mutable variable? One
  // general approach is to replace the driver actor with a new actor after each
  // message received. Another approach is to change "act()" to not loop, but
  // use tail recursion, where it passes the next message to send with each 
  // recursive invocation.
  
  var index = 0
  def sendNextMessage = {
    if (index < messages.length) {
      ShapeDrawingActor.send(messages(index), this)
      index += 1
    }
  }

  // Use a regular expression with one "capture group" for the final exit reply.
  val goodByeRE = """^.*good bye![^:]*:\s*(.*)$""".r  
  def act() {
    loop {
      react {
        case goodByeRE(reply) =>   // Match on the final exit reply regex?
          println("<-- driver actor finished: reply = " + reply) 
          exit
        case msg => 
          println("<-- " + msg)
          sendNextMessage
      }
    }
  }
}

ShapeDrawingActor.start()
DriverActor.start()

// Send the first message...
DriverActor.sendNextMessage

// Expected output:
// => Circle(Point(0.0,0.0),1.0)
// => <-- response: "drawn!", original message: "Circle(Point(0.0,0.0),1.0)"
// => Rectangle(Point(0.0,0.0),2.0,5.0)
// => <-- response: "drawn!", original message: "Rectangle(Point(0.0,0.0),2.0,5.0)"
// => Triangle(Point(0.0,0.0),Point(2.0,0.0),Point(1.0,1.0))
// => <-- response: "drawn!", original message: "Triangle(Point(0.0,0.0),Point(2.0,0.0),Point(1.0,1.0))"
// => <-- resp: "Using new response format.", msg: "(new-response-format,resp: "%s", msg: "%s")"
// => Circle(Point(1.0,1.0),2.0)
// => <-- resp: "drawn!", msg: "Circle(Point(1.0,1.0),2.0)"
// => Rectangle(Point(3.0,3.0),4.0,6.0)
// => <-- resp: "drawn!", msg: "Rectangle(Point(3.0,3.0),4.0,6.0)"
// => Triangle(Point(2.0,0.0),Point(4.0,0.0),Point(3.0,1.0))
// => <-- resp: "drawn!", msg: "Triangle(Point(2.0,0.0),Point(4.0,0.0),Point(3.0,1.0))"
// => Error: 3.14159
// => <-- resp: "Unknown message!", msg: "3.14159"
// => exiting...

