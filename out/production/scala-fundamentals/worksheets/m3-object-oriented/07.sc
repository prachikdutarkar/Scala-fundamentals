object MyApplication{
  def main(args: Array[String]): Unit = {
    println("I am entry point to hte application")
    args.foreach(println)
  }
}

MyApplication.main(Array("Namste","World!"))

