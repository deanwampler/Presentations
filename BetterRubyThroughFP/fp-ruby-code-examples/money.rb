class Money
  def initialize v
    @value = v
  end

  def + other   
    # TODO: Handle rounding properly...
    Money.new(value + other.value)
  end

  def - other
    # TODO: Handle rounding properly...
    Money.new(value - other.value)
  end

  def * factor
    # TODO: Handle rounding properly...
    Money.new(value * factor)
  end

  include Comparable
  
  def <=> other
    # TODO: Handle rounding properly...
    value <=> other.value
  end
  
  protected                  
  attr_reader :value   # bad idea?
end  

class Dollar < Money
  def initialize v
    super v
  end
  
  def to_s
    format("$%.2f", value)
  end
end