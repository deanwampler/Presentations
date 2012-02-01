dean = {
  :first_name => "Buck", 
  :last_name  => "Trends", 
  :addresses  => [
    "1 Memory Lane", 
    "1 Hope Drive", 
    "1 Infinite Loop"]}

dean[:addresses].each do |addr|
 puts "Address: #{addr}"
end