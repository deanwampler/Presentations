content = File.read(ARGV[0])

content = content.gsub(/<div class="slide">\s+<h1 class="slide0"/m, %{<div class="title slide">\n<h1})

puts content