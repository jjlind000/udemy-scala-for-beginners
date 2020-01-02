package lectures.part2oop

/**
 * Created by User on 30/12/2019
 */
abstract class MyList3[+A] {
  def head: A
  def tail: MyList3[A]
  def isEmpty: Boolean
  //NO: def add(element: A) : MyList3[A]
  def add[B >: A] (element: B) : MyList3[B]
  def printElements: String
  override def toString: String = String.format("[ %s ]", printElements)
}

object Empty3 extends MyList3[Nothing]{
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList3[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing] (element: B): MyList3[B] = new Cons3(element, Empty3)
  override def printElements: String = ""
}

class Cons3[+A](_head: A, _tail: MyList3[A]) extends MyList3[A] {
  override def head: A = this._head
  override def tail: MyList3[A] = this._tail
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList3[B] = new Cons3(element, this)
  override def printElements: String = {
    if (tail.isEmpty) "" + head
    else head + " " + tail.printElements
  }
}

object ListTest3 extends App {
//  val list = new Cons(10, Empty)
//  val list2 = new Cons(10, new Cons(20, new Cons(30, Empty)))
//  println(list.head)
//  println(list2.tail.head)
//  println(list2.toString)
  val listOfIntegers : MyList3[Int] = Empty3
  val listOfIntegers2 : MyList3[Int] = new Cons3(10, new Cons3(20, new Cons3(30, Empty3)))
  val listOfStrings : MyList3[String] = Empty3
  val listOfStrings2 : MyList3[String] = new Cons3("aa", new Cons3("bb", new Cons3("cc", Empty3)))
  println(listOfIntegers2)
  println(listOfStrings2)

}
/*
trait MyPredicate[T] {
  def test : Boolean
}

trait MyTransformer[A,B] {
  def apply[A] : B
}*/


