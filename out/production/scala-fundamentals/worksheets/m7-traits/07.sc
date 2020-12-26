abstract class NumberQ {
  def get(): Int

  def put(i: Int): Unit
}

class Q extends NumberQ {
  private var numbers: List[Int] = List.empty

  override def get() = {
    val toReturn = numbers.head
    numbers = numbers.tail
    toReturn
  }

  override def put(i: Int): Unit = {
    numbers = numbers :+ i
  }
}

trait Add1 extends NumberQ {
  abstract override def put(i: Int): Unit = super.put(i + 1)
}

trait Multiply2 extends NumberQ {
  abstract override def put(i: Int): Unit = super.put(i * 2)
}

trait Power2 extends NumberQ {
  abstract override def put(i: Int): Unit = super.put(math.pow(i, 2).toInt)
}

class Add1Q extends Q with Add1
val aQ = new Add1Q
aQ.put(12)
aQ.put(21)
aQ.get()
aQ.get()

class AddMul2Pow2 extends Q with Add1 with Multiply2 with Power2
val amQ = new AddMul2Pow2
amQ.put(10)
amQ.put {
  20
}
amQ.get()
amQ.get()

class Mul2Pow2Add1 extends Q with Multiply2 with Power2 with Add1
val mpaQ = new Mul2Pow2Add1
mpaQ.put(12)
mpaQ.put(21)
mpaQ.get()
mpaQ.get()
