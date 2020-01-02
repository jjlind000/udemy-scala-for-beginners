package lectures.part3funcprog

import scala.annotation.tailrec

/**
 * Created by User on 31/12/2019
 */
object HOFsAndCurries extends App {
  //function f
  //  takes
  //    an int
  //    a function that
  //      takes
  //        a string
  //        a function that takes an int and returns a boolean
  //      returns an int
  //  returns a function that takes an int and returns an int
//  val f : (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = //(i:Int) => i
//                                                                     (v1: Int) => (v2: Int) => v1*v2
  //method that applies a function f n times over value x
  //nTimes(f, n, x)
  @tailrec
  def apply_f_nTimes(f : Int => Int, n : Int, x: Int) : Int = {
    if (n<=0) x
    else apply_f_nTimes(f, n-1, f(x))
  }

  val plus1 = (i :Int ) => i+1
  println(apply_f_nTimes(plus1, 10, 10))

  //instead, define a method that returns "a function that applies a function f n times"
  def generate_apply_f_nTimes_Function(f: Int=>Int, n:Int) : Int => Int = {
    if(n<=0) (i: Int) => i
    else (i: Int) => generate_apply_f_nTimes_Function(f, n-1)(f(i))
  }
  //define a function that returns a function that applies an increment-by-one function 10 times
  val incrementByOne_10Times = generate_apply_f_nTimes_Function((i:Int)=>i+1, 10)
  //invoke the function on the value 10
  println(incrementByOne_10Times(10))

  //curried functions:
  val superAdder : Int => Int => Int  = (i: Int)    =>  (j:Int) => i + j
  //.............: <====== TYPE ====> = 1ST PARAM   =>  FUNCTION THAT IT RETURNS
  // superAdder takes an int and returns "a function that takes an int and returns an int"
  //aka because of right associativity:
  val superAdder2 : Int => (Int => Int) = (i: Int)  =>  (j:Int) => i + j
  //aka:
  val superAdder3 = (i: Int) =>  (j:Int) => i + j
  val add3 = superAdder3(3)
  println(add3(10))

  //curried formatter: for taking any format string and applying it to a double, returning the formatted string representation of the double:
  def curriedFormatter(fmt: String)(d:Double) : String = fmt.format(d)
  //creates a function that will apply a particular format string to a double
  val two_decimalplaces_formatter : (Double => String ) = curriedFormatter("%4.2f")
  val three_decimalplaces_formatter : (Double => String ) = curriedFormatter("%4.3f")
  println(two_decimalplaces_formatter(Math.PI)) //3.14
  println(three_decimalplaces_formatter(Math.PI)) //3.142

  def toCurry(f: (Int, Int) => Int) : (Int=>Int=>Int) = {
    x=>y=>f(x, y)
  }


}
