# Yes
def filter array
  array.select {|s| yield s}
end

class Person
  # No
  attr_writer :first_name, :last_name, :age # etc.
  ...
end

# No
puts "Some string"
