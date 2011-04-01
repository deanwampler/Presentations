// Exercise 1: Create an Address class
// Create an Address class with mutable fields for the number, street, city, etc.
// Create instances, read and write the fields, etc.
// Next, create an immutable version.
// Create instances, read the fields, etc. 
// What happens if you attempt to write the fields?
// Bonus: add a "toString" method.
//
// For comparison, here is the Person class from the notes, with a toString method added.
// You can run this file with "scala ex1-address.scala"

class Person(
  var firstName: String,
  var lastName:  String,
  var age:       Int) {
    
  /**
   * Note that the "override" keyword is required any time you override a concrete
   * (as opposed to abstract) method.
   */
  override def toString = 
    "Person(" + firstName + ", " + lastName + ", " + age + ")"
}


// Finally, we create some person instances, read (and print) and write fields, etc.

val person = new Person("Dean", "Wampler", 29)
println(person)
println(person.firstName)
println(person.lastName)
println(person.age)

person.firstName = "Buck"
person.lastName  = "Trends"
person.age       = 30
println(person)
println(person.firstName)
println(person.lastName)
println(person.age)
