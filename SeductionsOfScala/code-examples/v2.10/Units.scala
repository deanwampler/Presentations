package units

class Meters(val length: Double) extends AnyVal {
  def +(m: Meters): Meters = new Meters(length + m.length)
  override def toString = s"$length meters"
}

class Feet(val length: Double) extends AnyVal {
  def +(f: Feet): Feet   = new Feet(length + f.length)
  override def toString = s"$length feet"
}
