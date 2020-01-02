package lectures.part3funcprog

/**
 * Created by User on 31/12/2019
 */
object AnonymousFunctions extends App {
  val doubler = new Function1[Int, Int] { override def apply(i:Int) = i*2 }
  //as a lambda, effectively an instance of Function1:
  val doubler2 = (i:Int) => i*2
  val doubler3: Int=>Int = (i:Int) => i*2
  //multiple params must be bracketed:
  val multipleParams: (Int, String) => String = (i:Int, s:String) => "Result : " + i*2
  //no params:
  val noparams: ()=>String = ()=> "Hello world"
  //note you must call a lambda val/var with ():
  println(noparams)  //lectures.part3funcprog.AnonymousFunctions$$$Lambda$5/517938326@5474c6c
  println(noparams())//Hello world
  val bracesstyle: ()=>String = {
    () => "Hello world"
  }
  println(bracesstyle())

  //_ as shorthand for incoming arguments - note that the function signature - (Int,Int)=>Int below - must be specified:
  val foo : (Int, Int) => Int = _ * _

  //a function that takes an int and returns a function-that-takes-an-int-and-returns-an-int
  val fToF = new Function1[Int, Function1[Int, Int]] {
      override def apply(v1: Int): Function1[Int, Int]
        = new Function1[Int, Int] {
          override def apply(v2: Int): Int = v1*v2
        }
  }

  val fToF2 = (v1: Int) => new Function1[Int, Int] {
        override def apply(v2: Int): Int = v1*v2
      }
  val fToF3 = (v1: Int) => (v2: Int) => v1*v2

  println(fToF(100)(9))


}
