class List
 attr_reader :head, :tail
 
 def initialize(head, tail)
  @head = head
  @tail = tail
 end
 @@count=0
 def List.make(*elements)
   elements = elements[0] if elements[0].kind_of?(Array)
   if elements.empty?
     EMPTY 
   else
     List.new(elements[0], List.make(elements[1..-1]))
   end
 end
 
 def empty?
   return false
 end
 
 def to_s
   "(#{head},#{tail})"
 end
 alias :intern :to_s
 
 def prepend(head2)
   List.new(head2, self)
 end
 
 def filter(&f)
   f.call(head) ? List.new(head, tail.filter(&f)) : tail.filter(&f)
 end
 
 def map(&f)
   List.new(f.call(head), tail.map(&f))
 end
 
 def foldl(accumulator, &f)
   tail.foldl(f.call(accumulator, head), &f)
 end
 def foldr(accumulator, &f)
   f.call(head, tail.foldr(accumulator, &f))
 end
 
 EMPTY = List.new(nil,nil)
 def EMPTY.head; raise "EMPTY list has no head!!"; end
 def EMPTY.tail; raise "EMPTY list has no tail!!"; end
 def EMPTY.empty?; true; end
 def EMPTY.to_s; "()"; end
 def EMPTY.filter; self; end
 def EMPTY.map; self; end
 def EMPTY.foldl(accumulator); accumulator; end
 def EMPTY.foldr(accumulator); accumulator; end
end

l1 = List.new(1, List.new(2, List::EMPTY))
puts l1
l2 = l1.prepend(0)
puts l2
#List::EMPTY.head
#lempty = List.new(nil,nil)
#lempty.tail.tail

list = List.make(1, 2, 3, 4)
puts "list:        #{list}"
lf = list.filter {|x| x%2==1}
puts "filter:      #{lf}"
lm = list.map {|x| x*x}
puts "map:         #{lm}"

lfls = list.foldl("0") {|s,x| "(#{s}+#{x})"}
lflp = list.foldl(0) {|sum,x| sum+x}
puts "foldl (sum): #{lflp} == #{lfls}"

lfrs = list.foldr("0") {|x,s| "(#{x}+#{s})"}
lfrp = list.foldr(0) {|x,sum| x+sum}
puts "foldr (sum): #{lfrp} == #{lfrs}"

lflm = list.foldl(0) {|sub,x| sub-x}
puts "foldl (sub): #{lflm} == #{lfls.gsub(/\+/,'-')}"

lfrm = list.foldr(0) {|x,sub| x-sub}
puts "foldr (sub): #{lfrm} == #{lfrs.gsub(/\+/,'-')}"

list = List.make(
  "Dean@GMail.COM", 
  "bill@gmail.com", 
  "BOB@YAHOO.COM", 
  "tom@Spammer.com", 
  "pete@yahoo.com")
grouped = list.map {|x| 
  x.downcase
}.filter {|x|
  x !~ /spammer.com$/
}.foldl({}) {|groups, x|
  name, address = x.split('@')
  l = groups[address] || List::EMPTY
  groups[address] = List.new(name, l)
  groups
}
grouped.each {|k,v| puts "#{k}: #{v}"}