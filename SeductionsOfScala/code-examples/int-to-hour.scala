class Hour (val howMany:Int){
  def hour(when: Int) = when + howMany
}

object Hour {
 implicit def int2Hour(i:Int) = 
   new Hour(i)
 def fromNow = now
 def now = 11 // should call a time service...
}

import Hour._

println(1 hour fromNow)