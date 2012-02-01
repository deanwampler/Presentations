class Age  attr_reader :age
 def initialize value
  raise … unless positive(value)
  @age = value
 end
 def + amount
  raise … unless positive(amount)
  Age.new (@age + amount)
 end
 private
 def positive(x); x >= 0.0; end
end