# Recursive definition of the Fibonacci sequence.
# f(n) = 0 for n == 0
#      = 1 for n == 1
#      = f(n-1) + f(n-2) for n > 1
require 'rubygems'
require 'spec'

describe "Fibonacci sequence" do
  def fib n
    case n
    when 0..1 then n
    else fib(n-1) + fib(n-2)
    end
  end

  it "should return the correct result for an input integer >= 0" do
    expected = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    (0...expected.length).each do |i|
      fib(i).should == expected[i]
    end
  end
end