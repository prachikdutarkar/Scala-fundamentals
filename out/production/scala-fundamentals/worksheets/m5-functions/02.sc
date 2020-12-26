class Calculator {
  def sumOfSquares(a: Int, b: Int): Int = {
    def square(n: Int): Int = math.pow(n, 2).intValue()
    square(a) + square(b)
  }

  def multiplyDouble(a: Int, b: Int): Int = {
    def double(n: Int): Int = {
      println(s"Parent received $a and $b")
      2 * n
    }
    double(a) + double(b)
  }

  def divisionOfCubes(a: Int, b: Int): Int = {
    def cube(n: Int): Int = math.pow(n, 3).intValue()
    cube(a) / cube(b)
  }
}

val calc = new Calculator

calc.multiplyDouble(8,9)
calc.divisionOfCubes(55,38)
calc.sumOfSquares(6,3)


