// Typical OO, with lots of mutability, also poor encapsulation, etc...
case class Money(value: BigDecimal) {
  def + (m: Money) = Money(value + m.value)
  def - (m: Money) = Money(value - m.value)
}

case class Customer(var name: String)

trait Account {
  val customer: Customer
  var balance: Money
  def deposit(amount: Money) = balance += amount
  def withdraw(amount: Money) = balance -= amount
}

case class CheckingAccount(customer: Customer, var balance: Money) extends Account 
case class SavingsAccount(customer: Customer, var balance: Money, 
  var interestRate: Double) extends Account 
