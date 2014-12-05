import com.twitter.scalding._

class InvertedIndex(args: Args)
  extends Job(args) {

  val texts = Tsv("texts.tsv", ('id, 'text))

  val wordToIds = texts
   .flatMap(('id, 'text) -> ('word, 'id2)) {
    fields: (Long, String) =>
     val (id2, text) =
      fields.text.split("\\s+").map {
       word => (word, id2)
      }
   }

  val invertedIndex =
   wordToTweets.groupBy('word) {
    _.toList[Long]('id2 -> 'ids)
   }  
  invertedIndex.write(Tsv("output.tsv"))
}