def a_first? s
  s[0] == ?a
end

def filter array
  array.select {|s| yield s}
end

array = ["aa", "ab", "bb", "ba", "bc", "ac"]
# The following won’t "compile":
# array2 = filter array, a_first?
# array2 = filter array, &a_first?

array2 = filter(array) {|s| a_first? s}
p array2      # => ["aa", "ab", "ac"]

array3 = filter array, &method(:a_first?)
p array3      # => ["aa", "ab", "ac"]

def filter2 array, test
  array.select &test 
end

# Both of the following 2 defs work
pred = self.method(:a_first?).to_proc
pred = Proc.new {|s| a_first? s}
array4 = filter2 array, pred

p array4      # => ["aa", "ab", "ac"]

