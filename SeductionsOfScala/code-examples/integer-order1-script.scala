// What does this do??
val list = List("1", "4", "10", "2", "6", "2", "3", "5", "7", "7",
                "10", "12", "13", "9", "12", "11", "8", "2", "5")
args.map(s => s.toInt)
    .toSet
    .toList
    .sortWith((x,y) => x < y)
    .foreach(x => println(x))
