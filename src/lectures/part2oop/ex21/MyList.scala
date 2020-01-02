package lectures.part2oop.ex21

import scala.annotation.tailrec

/**
 * Created by User on 0/12/2019
 */
trait MyPredicate[-TT] {
  def test(t : TT) : Boolean
}

//trait MyTransformer[T,U] {
//  def transform[T] : U
//}

trait MyTransformer[-A,B] {
  def transform (a : A) : B
}
trait MyTransformer2[-XX,YY] {
  def transform (a : XX) : YY
}

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  //NO: def add(element: A) : MyList[A]
  def add[B >: A] (element: B) : MyList[B]
  def printElements: String
  override def toString: String = String.format("[ %s ]", printElements)
  //def map[A,U](transformer: MyTransformer[MyList[A],MyList[U]]) : MyList[U] = transformer.apply
  //map is a method that takes "a MyTransformer object that transforms an A into a XXX" and returns a MyList[XXX]
  def map[B](transformer: MyTransformer[A,B]) : MyList[B]
  def map2[G](transformer: MyTransformer2[A,G]) : MyList[G]
  //filter is a method that takes "a MyPredicate that does some kind of test on an element (implicitly an A) and returns a Boolean" and returns a MyList[A]
  def filter(predicate: MyPredicate[A]) : MyList[A]
  def flatMap[FF](transformer2: MyTransformer2[A, MyList[FF]]) : MyList[FF]
  def ++[CC >: A] (list: MyList[CC]) : MyList[CC]
}

case object Empty extends MyList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing] (element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def map2[G](transformer: MyTransformer2[Nothing, G]): MyList[G] = Empty

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def ++[CC >: Nothing](list: MyList[CC]): MyList[CC] = list

  override def flatMap[FF](transformer2: MyTransformer2[Nothing, MyList[FF]]): MyList[FF] = Empty
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

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(head), _tail.map(transformer))
  }

  override def map2[ZZ](transformer: MyTransformer2[A, ZZ]): MyList[ZZ] = {
    new Cons(transformer.transform(head), _tail.map2(transformer))
  }

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if(predicate.test(_head)){
      new Cons(_head, _tail.filter(predicate))
    } else _tail.filter(predicate)
  }

  override def ++[CC >: A](list: MyList[CC]): MyList[CC] = {
    //return a new Cons with the same head and tail + list
    new Cons(_head , _tail ++ list)
  }

  override def flatMap[FF](transformer2: MyTransformer2[A, MyList[FF]]): MyList[FF] =
    transformer2.transform(_head) ++ _tail.flatMap(transformer2)
}

object ListTest extends App {
  val listOfIntegers : MyList[Int] = Empty
  val listOfIntegers2 : MyList[Int] = new Cons(10, new Cons(20, new Cons(30, Empty)))
  val listOfIntegers3 : MyList[Int] = new Cons(40, new Cons(50, new Cons(60, Empty)))
  val listOfStrings : MyList[String] = Empty
  val listOfStrings2 : MyList[String] = new Cons("aa", new Cons("bb", new Cons("cc", Empty)))
  println(listOfStrings2)
  println(listOfIntegers2)
  println(listOfIntegers2.map(new MyTransformer[Int, Int] {
    override def transform(a: Int): Int = a*2;
  }).toString)
  println(listOfIntegers2.map2(new MyTransformer2[Int, Int] {
    override def transform(a: Int): Int = a*3;
  }).toString)
  println(listOfIntegers2.filter(new MyPredicate[Int] {
    override def test(t: Int): Boolean = t%6==0
  }).toString)

  //class IntToStringTransformer extends MyTransformer[MyList[Int],MyList[String]] {
  //  override def apply[T]: MyList[String] =
  //}
  println(listOfIntegers2 ++ listOfIntegers3)
  println(listOfIntegers2.flatMap(new MyTransformer2[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new Cons[Int](a, new Cons(a+1, Empty))
  })).toString
}
