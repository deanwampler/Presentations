class Person
  attr_reader :addresses
  
  def initialize *addresses
    @addresses = *addresses
    @addresses.clone.freeze
  end
end

def validate address
  true # some operation...
end

dean = Person.new "1 Memory Lane", "1 Hope Drive", "1 Infinite Loop"
dean.addresses.each {|a| validate(a) or raise "..."}
dean.addresses.each {|a| puts a}
dean.addresses.sort {|a1,a2| a1 <=> a2}.each {|a| puts a}
dean.addresses.sort! {|a1,a2| a1 <=> a2}.each {|a| puts a}

