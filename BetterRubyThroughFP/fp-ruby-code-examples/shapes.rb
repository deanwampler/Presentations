class Shape
  # "abstract" draw method. 
  # Sends commands to graphics engine
end

class Rectangle < Shape
  def draw
    "Rectangle"
  end
end

class Circle < Shape
  def draw
    "Circle"
  end
end

# etc.
