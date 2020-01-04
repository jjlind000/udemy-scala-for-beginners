package lectures.part3funcprog

object Arrays extends App{
  val arr = Array(1,2,3)
  val arr2 = Array.ofDim[Int](3)
  println(arr)  //[I@64c64813
  println(arr2) //[I@64c64813

  //note the default values - 0 for Int, null for reference types eg String etc
  arr2.foreach(i=> print ("" + i + "|")) //0|0|0|
  println
  arr.foreach(print) //123
  println
  arr(2) = 999 //syntactic sugar for numbers.update(2,0) - similar to apply
  arr.foreach(print) //12999
  println
  println(arr.mkString(",")) //1,2,999

  //Convert Array -> Sequence using implicit conversion
  val numSeq: Seq[Int] = arr //implicit conversion
  println(numSeq) //ArraySeq(1,2,999) - a wrapped array


}
