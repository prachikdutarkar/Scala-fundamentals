package main.scala.com.h2.entities

import java.util.UUID

sealed trait ProductCategory

case object DepositsP extends ProductCategory

case object LendingP extends ProductCategory

abstract class Product {
  val name: String
  val id: UUID = UUID.randomUUID()
  val category: ProductCategory

  override def toString: String = "product=" + name
}

//Deposits products
abstract class Deposits extends Product {
  override val category: ProductCategory = DepositsP
  val interestRatePerYear: Double
  val minimumBalancePerMonth: Dollars
}

abstract class Checking extends Deposits

abstract class Savings extends Deposits {
  val transactionsAllowedPerMonth: Int
}

//Checkings products
class CoreChecking(bal: Dollars, rate: Double) extends Checking {
  override val interestRatePerYear: Double = rate
  override val minimumBalancePerMonth: Dollars = bal
  override val name: String = "Core Checking"
}

class StudentChecking(bal: Dollars, rate: Double) extends Checking {
  override val interestRatePerYear: Double = rate
  override val minimumBalancePerMonth: Dollars = bal
  override val name: String = "Student Checking"
}

//Savings products
class RewardsSavings(bal: Dollars, rate: Double, trans: Int) extends Savings {
  println("Created Rewards Savings Product")
  override val interestRatePerYear: Double = rate
  override val minimumBalancePerMonth: Dollars = bal
  override val transactionsAllowedPerMonth: Int = trans
  override val name: String = "Rewards Savings"
}

//Lending products
abstract class Lending extends Product {
  override val category: ProductCategory = LendingP
  val annualFee: Dollars
  val apr: Double
  val rewardsPercent: Double
}

class CreditCard(fee: Dollars, rate: Double, pct: Double) extends Lending {
  override val annualFee: Dollars = fee
  override val apr: Double = rate
  override val rewardsPercent: Double = pct
  override val name: String = "Credit Card"
}
