abstract class NumberQ {
  def get(): Int

  def put(i: Int): Unit
}

class O extends NumberQ {
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

val queue = new O
queue.put(10)
queue.put(22)
queue.put(25)
queue.get()
queue.get()
queue.get()
