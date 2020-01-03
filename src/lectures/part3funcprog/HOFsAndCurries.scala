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

  //toCurry -takes: "a function (call it f1) that takes 2 ints and returns an int" , eg f1=(a,b)=>a*b
  //        -returns: a function (call it f2) that -takes an int
  //                                               -returns a function (call it f3) that takes an int and returns an int
  //        -the returned function (f2) will take an int which is (probably) used when the function that it itself returns (f3) is invoked
  def toCurry(f: (Int, Int) => Int) :  Int=>(Int=>Int) = {
  //aka                             : (Int=>(Int=>Int)) =
  //returns "a function that -takes an int (x) and
  //                         -returns a function that takes an int and returns an int (f(x,y))"
    x=>y=>f(x, y)
    //aka
    //x=>(y=>f(x, y))
  }
  //example:
  val f1=(a:Int, b:Int) => a*b //f1 is the function to be curried; it takes 2 ints but we are going to curry it so that
                               //it can effectively be split into a function f2 that takes an int and the function that f2
                               //returns (f3) that also takes an int
  val f2= toCurry(f1)   //f2 is our curried function that takes an int and returns "a function that takes an int and returns an int"
                        //let us now call f2 with an int....
  val tripler = f2(3)   //f2(3) returns our new function tripler, which is is "a function that takes an int and returns an int"
  println(tripler(100)) //we pass the value 3 to tripler; it returns the result (an int)


  //fromCurry - takes function that -takes an int
  //                                -returns "a function that takes an int and returns an int"
  //          - returns a function that takes 2 ints and returns an int
  def fromCurry(f: Int=>(Int=>Int)) : (Int, Int)=>Int = {
    (x: Int, y: Int) => f(x)(y)
  }

  //f10 is our curried function - we want to uncurry it
  //it is a function that takes an int (x) and returns "a function that takes an int (y) and returns an int (using the algorithm x*y)"
  val f10 : (Int => (Int=>Int)) = x => y => x*y
  //aka i.e. a little more verbosely
  val f10a: (Int => (Int=>Int)) = (x:Int) => ((y:Int) => x*y)
  //f11 is going to be a function that takes 2 ints and returns an int
  val f11 = fromCurry(f10)
  //call it
  println(f11(3, 4))

  //compose is a function that takes 2 functions f(x) and g(y) and returns a function that computes f(g(y))
  def compose(f: Int=>Int, g: Int=>Int) : Int=>Int =
    x=>f(g(x))

  val f20 = compose (x=> { println(s"computing ${x}*15"); x*15}, y=> { println(s"computing ${y}*30"); y*30;} )
  println(f20(3))

  //andThen is a function that takes 2 functions f(x) and g(y) and returns a function that computes g(f(x))
  def andThen(f: Int=>Int, g: Int=>Int) : Int=>Int =
    x=>g(f(x))

  val f30 = andThen(x=> { println(s"computing ${x}*15"); x*15}, y=> { println(s"computing ${y}*30"); y*30;} )
  println(f30(3))

  //genericised:
  def composeGeneric[A,B,C](f: A=>B, g: C=>A) : C=>B =
    x=>f(g(x))

  //genericised:
  def andThenGeneric[A,B,C](f: A=>B, g: B=>C) : A=>C =
    x=>g(f(x))

  val f40 = composeGeneric ((x:Int)=> { println(s"cG computing ${x}*15"); x*15}, (y:Int)=> { println(s"computing ${y}*30"); y*30;} )
  println(f40(3))

  val f50 = andThenGeneric ((x:Int)=> { println(s"aTG computing ${x}*15"); x*15}, (y:Int)=> { println(s"computing ${y}*30"); y*30;} )
  println(f50(3))



}
