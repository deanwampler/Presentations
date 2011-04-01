def mapper[T](l: List[T], f: (T) => Any) = {
  l.map(f(_))
} 

println(mapper("a" :: "b" :: Nil, 
 (x:String) => x.toUpperCase))

println(mapper(1 :: "b" :: Nil, 
 (x:Any) => format("%s:%s ", x, x)))
