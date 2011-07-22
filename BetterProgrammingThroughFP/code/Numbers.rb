class List
 attr_reader :head, :tail
 def initialize head, tail
  @head = head
  @tail = tail
 end
 def to_s
   "(#{head},#{tail})"
 end
 alias :intern :to_s
 
 EMPTY = Object.new {}
 def EMPTY.head; raise "EMPTY list has no head!!"; end
 def EMPTY.tail; raise "EMPTY list has no tail!!"; end
 def EMPTY.to_s; "()"; end

end

module NaturalNumbers 
  ZERO = 0
  def nextn(previous) 
    previous + 1
  end
  def take(count) 
    doTake(List::EMPTY, count)  # use nil 
  end
  private 
  def doTake(accumulator, count) 
    count == ZERO ? 
      accumulator : 
      doTake(List.new(nextn(count - 1), accumulator), count - 1)
  end
end

include NaturalNumbers
puts "0: #{ZERO}"
puts "1: #{nextn(ZERO)}"
puts "2: #{nextn(nextn(ZERO))}"
puts "3: #{nextn(nextn(nextn(ZERO)))}"

puts NaturalNumbers.take(5)
