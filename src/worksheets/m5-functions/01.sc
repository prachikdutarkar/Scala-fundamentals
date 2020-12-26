class Calculator {
  def double(n: Int): Int = 2 * n

  def square(n: Int): Int = math.pow(n, 2).intValue()

  def cube(n: Int): Int = math.pow(n, 3).intValue()

  def sumOfSquares(a: Int, b: Int): Int = square(a) + square(b)

  def multiplyDouble(a: Int, b: Int): Int = double(a) + double(b)

  def divisionOfCubes(a: Int, b: Int): Int = cube(a) / cube(b)
}

val calc = new Calculator

calc.cube(6)
calc.multiplyDouble(8,9)
calc.square(18)
calc.divisionOfCubes(55,38)
