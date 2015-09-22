// import org.apache.spark.SparkContext
// import org.apache.spark.SparkContext._
// import org.apache.spark.sql.SQLContext

// val sparkContext = new SparkContext(master, “...”)
// val sqlContext = new SQLContext(sparkContext)

val flights = sqlContext.read.parquet("output/flights")
val planes = sqlContext.read.parquet("output/planes")
flights.registerTempTable("flights")
planes.registerTempTable("planes")
flights.cache(); planes.cache()

val planes_for_flights1 = sqlContext.sql("""
  SELECT * FROM flights f
  JOIN planes p ON f.tailNum = p.tailNum LIMIT 100""")

planes_for_flights1.show

val planes_for_flights2 = flights.
  join(planes, flights("tailNum") === planes("tailNum")).
  limit(100)

planes_for_flights2.show
