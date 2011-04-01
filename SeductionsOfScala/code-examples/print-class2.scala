val l = "a" :: "b" :: Nil
l.foreach(x => 
      println(x.getClass))

// Try these variants:

//l.foreach(println(_.getClass))
//l.foreach((x:AnyRef) => println(x.getClass))

l.map(_.getClass).foreach(println)

// Which works?
//l map  _.getClass  foreach println
l map (_.getClass) foreach println