// What does this do??
val list = List("1", "4", "10", "2", "6", "2", "3", "5", "7", "7",
                "10", "12", "13", "9", "12", "11", "8", "2", "5")
list.map(_.toInt)
    .toSet
    .toList
    .sortWith(_ < _)
    .foreach(println(_))
