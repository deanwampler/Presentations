# Adapted from MenTaLguY's InfoQ interview: http://www.infoq.com/articles/actors-rubinius-interview
require 'rubygems'
require 'case'

Foo = Case::Struct.new :a, :b


def example(arg)
  case arg
  when Foo[:blarg, Object] # matches any Foo with :a == :blarg
    p "matched a :blarg"
  when Foo[10, 20] # matches only a Foo with .a == 10 and .b == 20
    p "matched 10 & 20"
  when Foo # matches any Foo
    p "matched another Foo: #{arg}"
  when Case[1, 2, Object] # matches a three-element array with initial elements 1, 2:
    p "matched [1, 2, Object], where the Object is #{arg[2].inspect}"
  when Case::Any[String, Array] # matches either a String or Array (Note: after previous case!)
    p "matched a String or Array: #{arg.inspect}"
  when Case::All[Integer, Case.guard { |n| n > 10 }]  # matches any Integer > 10:
    p "matched an integer > 10"
  else
    p "matched none of the above! \"#{arg}\""
  end
end

example(Foo.new(:blarg, :blech))
example(Foo.new(10, 20))
example(Foo.new(:aaa, :bbb))
example([1, 2, "boo!"])
example([3, 4, "boo!"])
example("Hello")
example([:c, :d, :e, :f])
example(11)
example(10)
