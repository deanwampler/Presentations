package learn.akka.avionics
import learn.akka.misc.EventSource
import akka.actor.{Props, Actor, ActorSystem, ActorLogging}

// The duration package object extends Ints with some timing functionality
// DeanW: pre 2.10, scala.concurrent.util.duration._
import scala.concurrent.duration._

// The Scheduler needs an execution context - we'll just use the global one
import scala.concurrent.ExecutionContext.Implicits.global

object Altimeter {
  // Sent to the Altimeter to inform it about a rate-of-climb changes
  case class RateChange(amount: Float)

  case class AltitudeUpdate(altitude: Double)
}

class Altimeter extends Actor with ActorLogging with EventSource {
  import Altimeter._
  // The maximum ceiling of our plane in 'feet'
  val ceiling = 43000
  // The maximum rate of climb for our plane in 'feet per minute'
  val maxRateOfClimb = 5000
  // The varying rate of climb depending on the movement of the stick
  var rateOfClimb: Float = 0
  // Our current altitude
  var altitude: Double = 0
  // As time passes, we need to change the altitude based on the time passed.
  // The lastTick allows us to figure out how much time has passed.
  var lastTick = System.currentTimeMillis
  // We need to periodically update our altitude.  This scheduled message send
  // will tell us when to do that.
  val ticker = context.system.scheduler.schedule(100.millis, 100.millis, self, Tick)
  // An internal message we send to ourselves to tell us to update our altitude.
  case object Tick 

  def receive = eventSourceReceive orElse altimeterReceive

  def altimeterReceive: Receive =  {
    // Our rate of climb has changed
    case RateChange(amount) =>
      // Keep the value of rateOfClimb within [-1, 1]
      rateOfClimb = amount.min(1.0f).max(-1.0f) * maxRateOfClimb
      log.info(s"Altimeter changed rate of climb to $rateOfClimb.")
    // Calculate a new altitude
    case Tick =>
      val tick = System.currentTimeMillis
      altitude = altitude + ((tick - lastTick) / 60000.0) * rateOfClimb
      lastTick = tick
      sendEvent(AltitudeUpdate(altitude))
  }
  // Kill our ticker when we stop
  override def postStop(): Unit = ticker.cancel
}