package main.scala.com.h2.entities

import main.scala.com.h2.service.{AccountService, CustomerService, ProductService, StatisticsService}

class Bank(val name: String, val city: String, val country: String, val email: Email)
  extends CustomerService
    with ProductService
    with AccountService
    with StatisticsService {

  println(s"$name Established 2018")

  override def toString: String = s"[$name]" +
    s" - $numCustomers customers" +
    s" - $numDepositProducts deposits products" +
    s" - $numDepositAccounts deposits accounts" +
    s" - $numLendingProducts lending products" +
    s" - $numLendingAccounts lending accounts"
}
