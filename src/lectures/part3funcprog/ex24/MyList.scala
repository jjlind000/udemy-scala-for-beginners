package lectures.part3funcprog.ex24

import scala.annotation.tailrec

/**
 * Created by User on 0/12/2019
 */
/*
trait MyPredicate[-TT] {
  def test(t : TT) : Boolean
}
*/


//trait MyTransformer[T,U] {
//  def transform[T] : U
//}

//trait MyTransformer[-A,B] {
//  def transform (a : A) : B
//}
//trait MyTransformer2[-XX,YY] {
//  def transform (a : XX) : YY
//}

abstract class MyList[+A] {
//  def MyPredicateFunction[T,R]:R = (t: T) => null
//  val myPredicateFunc = MyPredicateFunction[Int, Int]

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  //NO: def add(element: A) : MyList[A]
  def add[B >: A] (element: B) : MyList[B]
  def printElements: String
  override def toString: String = String.format("[ %s ]", printElements)
  //def map[A,U](transformer: MyTransformer[MyList[A],MyList[U]]) : MyList[U] = transformer.apply
  //map is a method that takes "a MyTransformer object that transforms an A into a XXX" and returns a MyList[XXX]
  //def map[B](transformer: MyTransformer[A,B]) : MyList[B]
  //Use a Function1 instead
  def map[B](transformer: A=>B) : MyList[B]
  //def map2[G](transformer: MyTransformer2[A,G]) : MyList[G]
  //Use a Function1 instead
  def map2[G](transformer: A=>G) : MyList[G]
  //filter is a method that takes "a MyPredicate that does some kind of test on an element (implicitly an A) and returns a Boolean" and returns a MyList[A]
  //def filter(predicate: MyPredicate[A]) : MyList[A]
  //instead of MyPredicate use a Function1[A,Boolean] instead (1):
  def filter(predicate: A=>Boolean) : MyList[A]
  //instead of MyPredicate use a Function1[A,Boolean] instead (2):
  def filter2(predicate: Function1[A,Boolean]) : MyList[A]
  //def flatMap[FF](transformer2: MyTransformer2[A, MyList[FF]]) : MyList[FF]
  def flatMap[FF](transformer2: A=>MyList[FF]) : MyList[FF]
  def ++[CC >: A] (list: MyList[CC]) : MyList[CC]
}

case object Empty extends MyList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing] (element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  //override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def map[B](transformer: Nothing=> B): MyList[B] = Empty

  //override def map2[G](transformer: MyTransformer2[Nothing, G]): MyList[G] = Empty
  override def map2[G](transformer: Nothing=>G): MyList[G] = Empty

  //override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def filter(predicate: Nothing=>Boolean): MyList[Nothing] = Empty
  override def filter2(predicate: Nothing=>Boolean): MyList[Nothing] = Empty

  //override def ++[CC >: Nothing](list: MyList[CC]): MyList[CC] = list
  override def ++[CC >: Nothing](list: MyList[CC]): MyList[CC] = list

  //override def flatMap[FF](transformer2: MyTransformer2[Nothing, MyList[FF]]): MyList[FF] = Empty
  override def flatMap[FF](transformer2: Nothing=> MyList[FF]): MyList[FF] = Empty
}

case class Cons[+A](_head: A, _tail: MyList[A]) extends MyList[A] {
  override def head: A = this._head
  override def tail: MyList[A] = this._tail
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def printElements: String = {
    if (tail.isEmpty) "" + head
    else head + " " + tail.printElements
  }

  override def map[B](transformer: Function1[A, B]): MyList[B] = {
    new Cons(transformer.apply(head), _tail.map(transformer))
  }

  override def map2[ZZ](transformer: Function1[A, ZZ]): MyList[ZZ] = {
    new Cons(transformer.apply(head), _tail.map2(transformer))
  }

  override def filter(predicate: A=>Boolean): MyList[A] = {
    if(predicate.apply(_head)){
      new Cons(_head, _tail.filter(predicate))
    } else _tail.filter(predicate)
  }
  override def filter2(predicate: A=>Boolean): MyList[A] = {
    if(predicate.apply(_head)){
      new Cons(_head, _tail.filter(predicate))
    } else _tail.filter(predicate)
  }

  override def ++[CC >: A](list: MyList[CC]): MyList[CC] = {
    //return a new Cons with the same head and tail + list
    new Cons(_head , _tail ++ list)
  }

  override def flatMap[FF](transformer2: A=>MyList[FF]): MyList[FF] =
    transformer2.apply(_head) ++ _tail.flatMap(transformer2)
}

object ListTest extends App {
  val listOfIntegers : MyList[Int] = Empty
  val listOfIntegers2 : MyList[Int] = new Cons(10, new Cons(20, new Cons(30, Empty)))
  val listOfIntegers3 : MyList[Int] = new Cons(40, new Cons(50, new Cons(60, Empty)))
  val listOfStrings : MyList[String] = Empty
  val listOfStrings2 : MyList[String] = new Cons("aa", new Cons("bb", new Cons("cc", Empty)))
  println(listOfStrings2)
  println(listOfIntegers2)
  //fTripler3 is a function that takes an Int and returns an Int..and is assigned the function definition (i:Int)=>i*3
  val fTripler3 : (Int => Int)                                    = (i:Int) => i*3

  //println(listOfIntegers2.map((Int => Int = (i: Int) => i*2).toString)
  println(listOfIntegers2.map(new Function1[Int, Int] {
    override def apply(i: Int): Int = i*2}).toString)
  println(listOfIntegers2.map((i: Int) => i * 2).toString)
  println(listOfIntegers2.map(fTripler3).toString)

  println(listOfIntegers2.map2((a: Int) => a*3).toString)
  println(listOfIntegers2.filter(new Function1[Int,Boolean] {
    override def apply(t: Int): Boolean = t%6==0
  }).toString)

  //class IntToStringTransformer extends MyTransformer[MyList[Int],MyList[String]] {
  //  override def apply[T]: MyList[String] =
  //}
  println(listOfIntegers2 ++ listOfIntegers3)
  println(listOfIntegers2.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(a: Int): MyList[Int] = new Cons[Int](a, new Cons(a+1, Empty))
  })).toString
}
