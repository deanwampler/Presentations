require 'rubygems'
require 'spec'
require 'Money'

def value money
  money.send(:value)
end

describe "Money" do
  before :all do
    @samples = (-3..3).inject([]) do |list, i|
      list << Money.new(1.1 * i)
    end
  end

  it "should support addition" do
    @samples.each do |m1|
      @samples.each do |m2|
        m12 = m1 + m2
        value(m12).should == value(m1) + value(m2)
      end
    end
  end
  it "should support subtraction" do
    @samples.each do |m1|
      @samples.each do |m2|
        m12 = m1 - m2
        value(m12).should == value(m1) - value(m2)
      end
    end
  end
end      

describe "Dollar" do
  before :all do
    @samples = (-3..3).inject([]) do |list, i|
      list << Dollar.new(1.1111 * i)
    end
  end
  
  it "should print in the form $MMMMM.NN" do
    @samples.each do |m|
      v = value m
      int = v.to_i
      fraction = format("%.2d", ((v - int) * 100).to_i.abs)
      m.to_s.should == "$#{int}.#{fraction}"
    end
  end
end
     
  
