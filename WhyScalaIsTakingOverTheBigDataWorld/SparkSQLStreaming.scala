// Adapted from Typesafe's Spark Workshop exercises.
// Copyright (c) 2014, Typesafe. All Rights Reserved.
//
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.sql.SQLContext

case class Flight(
  number: Int, carrier: String, origin: String, destination: String)
object Flight {
  def parse(recordString: String): Option[Flight] = {
    val recordRE = """\[(\d+),(\w+),(\w+),(\w+)\]""".r
    recordString match {
      case recordRE(id, carrier, origin, dest) =>
        Some(Flight(id.toInt, carrier, origin, dest))
      case _ =>
        println("Invalid Flight record: "+recordString)
        None
    }
  }
}

object FrequentFlights {
  def main(args: Array[String]): Unit = {
    val master = array
    val sourceServerPort = array(1)
    run(master, sourceServerPort)
  }

  def run(master: String, serverPort: String): Unit = {
    val SparkContext = new SparkContext(master, "FrequentFlights")
    // Capture 60 second batches of events.
    val streamingContext = new StreamingContext(SparkContext, Seconds(60))
    val sqlContext = new SQLContext(SparkContext)
    import sqlContext._

    val (server,port) = {
      val array = serverPort.split(":")
      (array(0), array(1).toInt)
    }
    val dstream = streamingContext.socketTextStream(server, port)
    val flights = for {
      line <- dstream
      flight <- Flight.parse(line)
    } yield flight

    flights.foreachRDD { (rdd, time) =>
      rdd.registerTempTable("flights")
      sql(s"""
        SELECT $time, carrier, origin, destination, COUNT(*)
        FROM flights
        GROUP BY carrier, origin, destination
        ORDER BY c4 DESC
        LIMIT 20""").foreach(println)
    }
    streamingContext.start()
    streamingContext.awaitTermination(2*1000)
  }
}
