object HelloWorld {
  /*
   * Scala preferred comment.
   * Second line.
   */
  def main(args: Array[String]): Unit = {
    val msg = "Hello World!"
    println(msg)
    val long: Long = 123L
    val int: Int = long.asInstanceOf[Int]
    println(int)
  }
}
