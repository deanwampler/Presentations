File.new("file-example.rb").each_with_index do |line, n|
  printf "%3d: %s", (n+1), line
end
