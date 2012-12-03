// Exercise 1: Solution
// Create an Address class with mutable fields for the number, street, city, etc.
// Create instances, read and write the fields, etc.
// Next, create an immutable version.
// Create instances, read the fields, etc. 
// What happens if you attempt to write the fields?
// Bonus: add a "toString" method.

// You can run this file with "scala ex1-address-solution.scala"

// Note that we are making the questionable assumption that Ints and Strings 
// are good choices for the fields where they are used. 

class Address(
  var number:  Int,
  var street:  String,
  var city:    String,
  var state:   String,
  var zip:     Int) {
    
  /**
   * Note that the "override" keyword is required any time you override a concrete
   * (as opposed to abstract) method.
   */
  override def toString = 
    "Address(" + number + " " + street + ", " + city + ", " + state + " " + zip + ")"
}

// Finally, we create some person instances, read and write fields, etc.

val address1 = new Address(1, "Infinity Loop", "Cupertino", "CA", 99999)
println(address1)
println(address1.number)
println(address1.street)
println(address1.city)
println(address1.zip)

address1.number = 2
address1.street = "Me Too Way"
address1.city = "Redmond"
address1.state = "WA"
address1.zip = 98000
println(address1)
println(address1.number)
println(address1.street)
println(address1.city)
println(address1.zip)

// Immutable Address

class Address2(
  val number:  Int,
  val street:  String,
  val city:    String,
  val state:   String,
  val zip:     Int) {
    
  override def toString = 
    "Address2(" + number + " " + street + ", " + city + ", " + state + " " + zip + ")"
}

val address2 = new Address2(2, "Negative Infinity Way", "Los Gatos", "CA", 98888)
println(address2)
println(address2.number)
println(address2.street)
println(address2.city)
println(address2.zip)

// Error:
// address2.number = 2
// address2.street = "Me Too Way"
// address2.city = "Redmond"
// address2.state = "WA"
// address2.zip = 98000

// Sneak Preview:
// We can simplify things further by declaring the class as a "case class",
// which we'll discuss later.

// Immutable Address
// 1. The "val" (immutable) keywords are inferred. 
// 2. A suitable toString is generated for us, as long with
//    many other very useful methods, including hashCode,
//    equals, and others we'll see.
case class Address3(
  number:  Int,
  street:  String,
  city:    String,
  state:   String,
  zip:     Int)

// No "new" is required. We are effectively calling a "factory"
// method here:
val address3 = Address3(3, "Zero Street", "San Jose", "CA", 97777)
println(address3)
println(address3.number)
println(address3.street)
println(address3.city)
println(address3.zip)
