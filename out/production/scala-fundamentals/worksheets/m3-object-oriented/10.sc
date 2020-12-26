abstract class Employee {
  val first: String
  val last: String
}

abstract class DepartmentEmployee extends Employee {
  private val secret = "Big Secret!"
  val department: String
  val departmentCode: String
  val numberOfStocks: Int

  override def toString: String =
    "[" + first + "," + last + "," + department + "," + numberOfStocks + "]"
}

class RnDEmployee(f: String, l: String) extends DepartmentEmployee {
  val first = f
  val last = l
  val department = "Research and Development"
  val departmentCode = "R&D"
  val numberOfStocks = 100
}

class MarketingEmployee(f: String, l: String) extends DepartmentEmployee {
  val first = f
  val last = l
  val department = "Marketing"
  val departmentCode = "MKT"
  val numberOfStocks = 85
}

val rndEmployee = new RnDEmployee("Amy", "Walter")
val marketingEmployee = new MarketingEmployee("Bob", "Builder")

/*Composition Example*/
abstract class Copany {
  val name: String
  val numberOfEmployees: Int
  val employees: Set[Employee]
}

class PluralSight(n: String, es: Set[Employee]) extends Copany {
  override val name = n
  override val numberOfEmployees = es.size
  override val employees = es
}

val pluralSight = new PluralSight("PluralSight Inc", Set(rndEmployee, marketingEmployee))
pluralSight.numberOfEmployees



