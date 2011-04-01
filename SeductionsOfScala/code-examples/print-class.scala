def printClass(l: List[AnyRef]) = {
  l.foreach(x => println(x.getClass))
} 

printClass("a" :: "b" :: Nil)
printClass(1 :: 2 :: Nil)
//val intList = 1 :: 2 :: Nil
//printClass(intList)