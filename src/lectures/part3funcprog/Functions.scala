package lectures.part3funcprog

/**
 * Created by User on 31/12/2019
 */
object Functions extends App {
  trait MyFunction[A,B] {
    def apply(a : A) : B
  }
  val fDoubler = new MyFunction[Int, Int] { override def apply(i : Int) : Int = i*2 }
  println(fDoubler(4))

  val fTripler = new Function1[Int, Int] { override def apply(i:Int):Int = i*3 }
  val fTripler2 = new ((Int) => Int) { override def apply(i:Int):Int = i*3 }
  val fTripler3 : ((Int) => Int) = (i:Int) => i*3
  println(fTripler(4))
  val fAdder = new Function2[Int, Int, Int] { override def apply(i:Int,j:Int):Int = i+j }
  val fAdder2: ((Int, Int)=>Int) = new Function2[Int, Int, Int] { override def apply(i:Int,j:Int):Int = i+j }
  val fAdder3: (Int, Int)=>Int = (i: Int, j: Int) => i + j

  println(fAdder(4,5))

  val concat : (String, String)=>String = (s1: String, s2:String) =>s1.concat(s2)
  println(concat("abc", "def"))

  //a function that takes an int and returns a function-that-takes-an-int-and-returns-an-int
  /*type: */ val fToF = new Function1[Int, Function1[Int, Int]] {
                            /*definition:*/ override def apply(v1: Int): Function1[Int, Int]
                          //define the returned function as multiplying the argument to the function that created it
                          //with the argument that is passed to it
                                  = new Function1[Int, Int] {
                                        override def apply(v2: Int): Int = v1*v2
                                  }
    }
  val multBy100 = fToF(100)
  println(multBy100(9))
  //or
  println(fToF(100)(7))


}
