require 'rubygems'
require 'case'
require 'shapes'

def example(arg)
  case arg
  when Shape
    p arg.draw
  when "exit"
    p "exiting..."
  end
end

[Circle.new, Rectangle.new, "exit"].each do |t|
  example(t)
end
