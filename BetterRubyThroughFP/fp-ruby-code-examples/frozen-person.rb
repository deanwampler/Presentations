class Person
  attr_accessor :first_name, :last_name, :age, :addresses # etc.
  
  def initialize first_name, last_name, age, addresses
    @first_name = first_name
    @last_name  = last_name
    @age        = age
    @addresses  = addresses.clone
  end
  
  def freeze
    super
    @addresses.freeze
  end

  def to_s
    "#{@first_name} #{@last_name}, age #{@age}, at #{@addresses.inspect}"
  end
end

dean = Person.new "Dean", "Wampler", 29, ["1st St.", "2nd St.", "3rd St."]
p dean.first_name          # => "Dean"
dean.first_name = "Bubba"
p dean.first_name          # => "Bubba"
dean.freeze
begin
dean.first_name = "Joe"    # => frozen-person.rb:16:in `first_name=': can't modify frozen object (TypeError) 
                           #    from frozen-person.rb:16
rescue
end
begin
dean.addresses[0] = "Infinity Loop"    # => frozen-person.rb:16:in `first_name=': can't modify frozen object (TypeError) 
                           #    from frozen-person.rb:16
rescue
  p "Addresses not changed: #{dean.addresses}"
end
p "dean: #{dean}"
# But:
dean = Person.new "Joe", "de Plumber", 40, ["Wisteria Lane"]
p dean.first_name          # => "Joe"


