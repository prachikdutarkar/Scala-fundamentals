package main.scala.com.h2.entities

import java.util.UUID

sealed trait TransactionType

case object In extends TransactionType

case object Out extends TransactionType

case class Transaction(customer: Customer, amount: Dollars,
                       transactionType: TransactionType, accountCategory: AccountCategory)

sealed trait AccountCategory

case object DepositsA extends AccountCategory

case object LendingA extends AccountCategory

abstract class Account {
  val id: UUID = UUID.randomUUID()
  val customer: Customer
  val product: Product
  val category: AccountCategory
  var transactions: Seq[Transaction] = Seq.empty

  def getBalance: Dollars
}

class DepositsAccount(val customer: Customer,
                      val product: Deposits,
                      private var balance: Dollars) extends Account {
  override val category: AccountCategory = DepositsA

  def deposit(dollars: Dollars): Unit = {
    require(dollars > Dollars(0), "amount deposited should be grater than zero.")
    balance += dollars
    transactions = transactions :+ Transaction(customer, dollars, In, category)
    println(s"Depositing $dollars to " + toString)
  }

  def withdraw(dollars: Dollars): Unit = {
    require(dollars > Dollars(0) && balance > dollars,
      "amount should be grater than zero and requested amount should be less than current balance.")
    balance -= dollars
    transactions = transactions :+ Transaction(customer, dollars, Out, category)
    println(s"Withdrawing $dollars from " + toString)
  }

  override def getBalance: Dollars = balance

  override def toString: String = s"$id -> $customer with $product has remaining balance of $balance"
}

class LendingAccount(val customer: Customer,
                     val product: Lending,
                     private var balance: Dollars) extends Account {
  override val category: AccountCategory = LendingA

  def payBill(dollars: Dollars): Unit = {
    require(dollars > Dollars(0), "The payment must be made for amount deposited should be grater than zero.")
    balance += dollars
    transactions = transactions :+ Transaction(customer, dollars, In, category)
    println(s"Paying bill of $dollars against " + toString)
  }

  def withdraw(dollars: Dollars): Unit = {
    require(dollars > Dollars(0), "The withdrawal amount must be grater than zero.")
    println(s"Debited $dollars from " + toString)
    balance -= dollars
    transactions = transactions :+ Transaction(customer, dollars, Out, category)
  }

  override def getBalance: Dollars = balance

  override def toString: String = s"$id -> $customer with $product has remaining balance of $balance"
}