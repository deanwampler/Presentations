val customer = Customer("Dean")

val ca = CheckingAccount(customer, Money(1000.0))
println("Checking: initial account: " + ca)
ca.deposit(Money(1000.0))
ca.withdraw(Money(500.0))
println("Checking: final account: " + ca)

val sa = SavingsAccount(customer, Money(2000.0), .02)
println("Savings: initial account: " + sa)
sa.withdraw(Money(1200.0))
sa.deposit(Money(100.0))
sa.interestRate = 0.3
println("Checking: final account: " + sa)

// Checking: initial account: CheckingAccount(Customer(Dean),Money(1000.0))
// Checking: final account: CheckingAccount(Customer(Dean),Money(1500.0))
// Savings: initial account: SavingsAccount(Customer(Dean),Money(2000.0),0.02)
// Checking: final account: SavingsAccount(Customer(Dean),Money(900.0),0.3)