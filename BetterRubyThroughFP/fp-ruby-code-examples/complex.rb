module Math
  class Complex
    attrib_reader :real, :imag
    
    def initialize real, imag
      @real = real
      @imag = imag
    end
    
    def + other
      Complex.new(real + other.real, imag + other.imag)
    end
    
    def - other
      Complex.new(real - other.real, imag - other.imag)
    end
  end
end