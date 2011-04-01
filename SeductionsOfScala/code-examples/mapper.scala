def mapper(l: List[AnyRef], f: (AnyRef) => Any) = {
  l.map(f(_))
} 

println(mapper("a" :: "b" :: Nil, 
 (x:AnyRef) => x.getClass))

// println(mapper("a" :: "b" :: Nil, 
//  (x:String) => x.toUpperCase))

println(mapper("a" :: "b" :: Nil, 
 (x:Any) => x.isInstanceOf[Int]))

 println(mapper("a" :: "b" :: Nil, 
  (x:Any) => x.toString))
