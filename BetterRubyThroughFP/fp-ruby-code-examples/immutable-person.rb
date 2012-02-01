class Person
  attr_reader :first_name, :last_name, :age # etc.
  
  def initialize first_name, last_name, age
    @first_name = first_name
    @last_name  = last_name
    @age        = age
  end
end

dean = Person.new "Dean", "Wampler", 29
p dean.first_name          # => "Dean"
dean.first_name = "Bubba"  # => ... undefined method `first_name=' ...

