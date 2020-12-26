package main.scala.com.h2.service

import java.util.UUID

import main.scala.com.h2.entities.{DepositsAccount, Dollars, LendingAccount}

trait AccountService extends AccountDb
  with CustomerService with
  ProductService {
  def openDepositAccount(customerId: UUID, productId: UUID, amount: Dollars): UUID = {
    require(getCustomer(customerId).nonEmpty, s"no customer found with id=$customerId")

    val maybeProduct = getDepositProduct(productId)
    require(maybeProduct.nonEmpty, s"no deposits product with id=$productId")

    val account = new DepositsAccount(getCustomer(customerId).get, getDepositProduct(productId).get, amount)
    saveDepositAccount(account)
    account.id
  }

  def openLendingAccount(customerId: UUID, productId: UUID, balance: Dollars = Dollars(0)): UUID = {
    require(getCustomer(customerId).nonEmpty, s"no customer found with id=$customerId")

    val maybeProduct = getLendingProduct(productId)
    require(maybeProduct.nonEmpty, s"no lending product with id=$productId")

    val account = new LendingAccount(getCustomer(customerId).get, getLendingProduct(productId).get, balance)
    saveLendingAccount(account)
    account.id
  }

  def deposit(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getDepositAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid deposits account Id must be provided")
    maybeAccount.get deposit dollars
  }

  def withdraw(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getDepositAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid deposits account Id must be provided")
    maybeAccount.get withdraw dollars
  }

  def requestCurrency(accountID: UUID, currency: Currency): Unit = {
    withdraw(accountID, currency.costInDollars)
    //some work to register request to send money to customer's nearest branch
    println(s"The ${currency.amount} ${currency.code} wil be posted to your nearest branch in 2 working days.")
  }

  def useCreditCard(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getLendingAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid lending account Id must be provided")
    maybeAccount.get withdraw dollars
  }

  def payCreditCardBill(accountID: UUID, dollars: Dollars): Unit = {
    val maybeAccount = getLendingAccount(accountID)
    require(maybeAccount.nonEmpty, "A valid lending account Id must be provided")
    maybeAccount.get payBill dollars
  }
}
