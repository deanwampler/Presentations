for i in [1, 2, 3, 4, 5]
  print "#{i}, "
end
print "\n"

hash = {:a => 'a', :b => 'b', :c => 'c'}
hash.each do |k, v|
  puts "#{k} => #{v}"
end

def print_array array, i = 0
  return if i == array.length
  print "#{array[i]}, "
  print_array array, i+1
end

print_array [1, 2, 3, 4, 5]
print "\n"
