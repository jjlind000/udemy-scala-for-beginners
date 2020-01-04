package lectures.part3funcprog

object Sequences extends App {

  val seq = Seq(4,2,3,4)
  println(seq) // seq is actually a List
  println(seq(3)) //4 - apply method is get(ndx)
  println(seq ++ Seq(7,6,5))
  println((seq ++ Seq(7,6,5)).sorted)
  //ranges
  val rngTo : Seq[Int] = 1 to 10
  println(rngTo) //Range 1 to 10
  println(rngTo.sorted.reverse) //Range 10 to 1 by -1
  println(rngTo.sorted.reverse.foreach(i=> print(i + ","))) //10,9,8,7,6,5,4,3,2,1,()
  (1 to 10).foreach(i=> print(i + ",")) //1,2,3,4,5,6,7,8,9,10,
  val rngUntil : Seq[Int] = 1 until 10
  println
  rngUntil.foreach(i=> print(i + ",")) //1,2,3,4,5,6,7,8,9,

}
