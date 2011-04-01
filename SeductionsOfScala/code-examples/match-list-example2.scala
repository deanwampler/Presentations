val list = 
  1 :: 2 :: 3 :: 4 :: 5 :: Nil

def prnt(l: List[_]): Unit = l match {
  case head :: tail => 
    print(head + ", ")
    prnt(tail)
  case Nil => // do nothing
}
 
prnt(list)