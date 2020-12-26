package main.scala.com.rnd

import java.text.SimpleDateFormat

object DateSorting extends App {

  case class EmailContent(date: String, source: String) extends Ordered[EmailContent] {
    val format = new SimpleDateFormat("dd-MMM-yyyy")
    override def compare(that: EmailContent): Int =
      format.parse(this.date) compareTo (format.parse(that.date))
  }

  val collection = Vector(EmailContent("31-MAR-2020", "Google"), EmailContent("30-OCT-2020", "Wiki"), EmailContent("30-OCT-2020", "Source"),EmailContent("31-SEP-2020", "Apache"))
  collection
    .foreach(elem => println(s"before sort $elem"))

  println("******************************************")

  collection
    .sorted
    .reverse
    .foreach(elem => println(s"after sort $elem"))

}
