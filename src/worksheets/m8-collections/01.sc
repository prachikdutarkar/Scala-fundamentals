val priceCoffeeByDay: Map[String, Double] = Map(
  "Sunday"-> 2.45,
  "Monday"-> 2.45,
  "Tuesday"-> 2.45,
  "Wednesday"-> 2.45,
  "Thursday"-> 2.45,
  "Friday"-> 2.45,
  "Saturday"-> 2.45
)

val totalSpentOnCoffeeUsingSum = priceCoffeeByDay.values.sum
val minSpentOnCoffeeUsingSum = priceCoffeeByDay.values.min

val totalSpentOnCoffeeUsingReduce =
  priceCoffeeByDay.values.reduce((a,b) => a + b)

val totalSpentOnCoffeeUsingReduceOther =
  priceCoffeeByDay.values.reduce(_ + _)

val minSpentOnCoffeeUsingReduce =
  priceCoffeeByDay.values.reduce(_ min  _)