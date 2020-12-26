package main.scala.com.h2.entities

object Dollars {
  val Zero = new Dollars(0)

  def apply(amount: Int): Dollars = new Dollars(amount)
}

class Dollars(val amount: Int) extends AnyVal with Ordered[Dollars] {
  def +(dollars: Dollars): Dollars = new Dollars(amount + dollars.amount)

  def -(dollars: Dollars): Dollars = new Dollars(amount - dollars.amount)

  override def toString: String = "$" + amount

  override def compare(that: Dollars): Int = amount - that.amount
}

