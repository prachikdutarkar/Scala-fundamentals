package main.scala.com.h2

import java.util.UUID

import main.scala.com.h2.entities.{Bank, DepositsP, Dollars, Email, LendingP, Product}

object BankOfScala {
  val random = new scala.util.Random()

  def main(args: Array[String]): Unit = {
    println("Opening Bank")
    val bank = new Bank("Bank Of Scala", "Auckland", "New Zealand", Email("bank", "scala.com"))
    val customerIds = getCustomers map { c => bank.createNewCustomer(c._1, c._2, c._3, c._4) }
    val depositProductIds = getDepositProducts map { p => bank.addNewDepositedProduct(p._1, p._2, p._3) }
    val lendingProductsIds = getLendingProducts map { l => bank.addNewLendingProduct(l._2, l._3, l._4) }

    /* logging */
    println(s"Bank: $bank")
    println(s"CustomerIds: $customerIds")
    println(s"Deposits Product Ids: $depositProductIds")
    println(s"Lending Product Ids: $lendingProductsIds")

    def openAccounts(customerId: UUID, product: Product): Dollars => UUID = product.category match {
      case DepositsP => bank openDepositAccount(customerId, product.id, _: Dollars)
      case LendingP => bank openLendingAccount(customerId, product.id, _: Dollars)
    }

    /**
     * Bank clerk opening the account.
     * There is no money deposited in the account yet, so the accounts are not active*/
    val depositsAccounts = for (c <- customerIds; p <- depositProductIds) yield openAccounts(c, bank.getDepositProduct(p).get)

    /* Depositing money into the accounts */
    val random = scala.util.Random
    val depositsAccountIds = depositsAccounts.map { account => account(Dollars(10000 + random.nextInt(10000))) }

    /* logging */
    println(s"Deposits Accounts: $depositsAccounts")
    println(s"Deposits Accounts Ids: $depositsAccountIds")

    /**
     * Open credit card accounts.
     * The bank process has not finished the credit check, so balance will be known later */
    val lendingAccounts = for (c <- customerIds; p <- lendingProductsIds) yield openAccounts(c, bank.getLendingProduct(p).get)

    val lendingAccountIds = lendingAccounts.map { account => account(Dollars(random.nextInt(550))) }

    /* logging */
    println(s"Lending Accounts: $lendingAccounts")
    println(s"Lending Accounts Ids: $lendingAccountIds")
    println(s"Bank: $bank")

    /*Performing Deposit Accounts transactions */
    val randomAmount = new scala.util.Random(100)
    depositsAccountIds foreach { accountId =>
      bank deposit(accountId, Dollars(1 + randomAmount.nextInt(200)))
      bank withdraw(accountId, Dollars(1 + randomAmount.nextInt(50)))
    }

    /*Performing Lending Accounts transactions */
    lendingAccountIds foreach { accountId =>
      bank useCreditCard(accountId, Dollars(1 + randomAmount.nextInt(500)))
      bank payCreditCardBill(accountId, Dollars(1 + randomAmount.nextInt(150)))
    }

    /** Request Currency **/
    println("-" * 150)
    println("Requesting Currency")
    println("-" * 150)
    bank.requestCurrency(depositsAccountIds.head, "120 USD")
    bank.requestCurrency(depositsAccountIds.tail.head, "100 INR")

    /** Statistics **/
    println("-" * 150)
    println("Statistics")
    println("-" * 150)

    val dAccounts = depositsAccountIds flatMap {
      bank.getDepositAccount
    }
    println(s"Total Money Deposited to Bank: ${bank.getTotalMoneyDeposited(dAccounts)}")

    val lAccounts = lendingAccountIds flatMap {
      bank.getLendingAccount
    }
    println(s"Total Money Borrowed by Customers: ${bank.getTotalMoneyBorrowedByCustomers(lAccounts)}")

    val allAccounts = dAccounts ++ lAccounts
    println(s"Number of Transactions By Account: ${bank.getNumTransactionsByAccount(allAccounts)}")
  }

  def getCustomers: Seq[(String, String, String, String)] = {
    Seq(
      ("Bob", "Martin", "bob@maritn.com", "1976/11/25"),
      ("Amy", "Jones", "amy.jones@gmail.com", "1983/11/25"),
      ("Taylor", "Jackson", "taylor1982@jackson.com", "1982/12/25")
    )
  }

  def getDepositProducts: Seq[(String, Int, Double)] = {
    Seq(
      ("CoreChecking", 1000, 0.025),
      ("StudentChecking", 0, 0.010),
      ("RewardsSavings", 100000, 0.50)
    )
  }

  def getLendingProducts: Seq[(String, Dollars, Double, Double)] = {
    Seq(
      ("CreditCard", Dollars(500), 14.23, 20.00)
    )
  }
}
