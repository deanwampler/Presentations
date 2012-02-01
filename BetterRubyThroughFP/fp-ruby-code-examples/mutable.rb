require 'rubygems'
require 'spec'

describe "Mutable data hazards" do
  class MutableName
    attr_accessor :name
    def initialize name
      @name = name
    end
  end

  it "will change an object through an alias" do
    m1 = MutableName.new "m1"
    m2 = m1
    m2.name = "m2"
    m1.name.should == "m2"
  end
end