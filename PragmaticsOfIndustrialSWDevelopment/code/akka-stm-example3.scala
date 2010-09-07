// Inside a Transactor for bank transfers:
def receive = {
  case Transfer(from, to, amount) =>
    atomic {
      if (from.get < amount) {
        log.info("not enough money - retrying")
        retry  // <= try again "later"
      }
      log.info("transferring")
      from alter (_ - amount)
      to alter (_ + amount)
    }
}
