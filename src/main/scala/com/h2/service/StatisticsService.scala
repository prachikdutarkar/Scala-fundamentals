package main.scala.com.h2.service

import main.scala.com.h2.entities.{Account, AccountCategory, DepositsA, Dollars, LendingA}

trait StatisticsService {
  def getTotalMoneyDeposited(accounts: Seq[Account]): Dollars = {
    accounts.foldLeft(Dollars.Zero)((total, account) => if (account.category == DepositsA) total + account.getBalance else total)
  }

  def getTotalMoneyBorrowedByCustomers(accounts: Seq[Account]): Dollars = {
    accounts map { a => if (a.category == LendingA) a.getBalance else Dollars.Zero } reduce (_ + _)
  }

  def getNumTransactionsByAccount(accounts: Seq[Account]): Map[String, Int] = {
    val tuples: Seq[(AccountCategory, Int)] = accounts.map { a => a.category -> a.transactions.length }
    val categoryToTuples: Map[AccountCategory, Seq[(AccountCategory, Int)]] = tuples.groupBy(_._1)
    categoryToTuples map {
      case (accountCategory: AccountCategory, rest) => accountCategory.toString -> rest.map(_._2).sum
    }
  }
}
