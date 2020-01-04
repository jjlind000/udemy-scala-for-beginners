package lectures.part3funcprog

object Lists extends App {

  val list = List(1,2,3)
  //prepend, append:
  val list2 = 42 :: list
  println(list2) //List(42, 1, 2, 3)
  val list3 = 43 +: list
  println(list3) //List(43, 1, 2, 3)
  val list4 = list :+ 44
  println(list4) //List(1, 2, 3, 44)

  //fill: fill is a curried function that takes 2 parameters: a number and a value
  println(List.fill(5)("abc")) //List(abc, abc, abc, abc, abc)

  //mkString
  println(list.mkString("*")) //1*2*3


}
