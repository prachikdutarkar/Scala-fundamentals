package main.scala.com.h2.service

import java.util.UUID

import main.scala.com.h2.entities.{CoreChecking, CreditCard, Dollars, RewardsSavings, StudentChecking}

trait ProductService extends ProductDb {
  def addNewDepositedProduct(name: String, minBalance: Int, ratePerYear: Double,
                             transactionsAllowedPerMonth: Int = 2): UUID = {
    val product = name match {
      case "CoreChecking" => new CoreChecking(Dollars(minBalance), ratePerYear)
      case "StudentChecking" => new StudentChecking(Dollars(minBalance), ratePerYear)
      case "RewardsSavings" => new RewardsSavings(Dollars(minBalance), ratePerYear, transactionsAllowedPerMonth)
    }

    saveDepositProduct(product)
    product.id
  }

  def addNewLendingProduct(annualFee: Dollars, apr: Double, rewardsPercent: Double): UUID = {
    val product = new CreditCard(annualFee, apr, rewardsPercent)
    saveLendingProduct(product)
    product.id
  }
}
