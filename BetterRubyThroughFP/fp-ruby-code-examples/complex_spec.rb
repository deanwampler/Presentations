require 'rubygems'
require 'spec'
require 'Complex'

module Math

  describe "Complex" do
    before :all do
      @samples = (-3..3).inject([]) do |list, i|
        list += (-3..3).inject([]) do |list, j|
          list << Complex.new(1.1 * i, 1.1 * j)
        end
      end
    end

    it "should support addition where a new value is returned that sums the input real and imaginary values" do
      @samples.each do |c1|
        @samples.each do |c2|
          c12 = c1 + c2
          c12.real.should == c1.real + c2.real
          c12.imag.should == c1.imag + c2.imag
        end
      end
    end
    it "should support subtraction where a new value is returned that subtracts the input real and imaginary values" do
      @samples.each do |c1|
        @samples.each do |c2|
          c12 = c1 - c2
          c12.real.should == c1.real - c2.real
          c12.imag.should == c1.imag - c2.imag
        end
      end
    end
  end
end
