class Employee {
  var first: String = ""
  var last: String = ""

  override def toString: String = first + " " + last
}

val bobMartin = new Employee
bobMartin.first = "Bob"
bobMartin.last = "Martin"

bobMartin

bobMartin.first = "Ruby"
bobMartin.last = "Jackson"

bobMartin
