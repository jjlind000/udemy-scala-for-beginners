package lectures.part2oop

/**
 * Created by User on 30/12/2019
 */
abstract class MyList2 {
  def head: Int
  def tail: MyList2
  def isEmpty: Boolean
  def add(element: Int) : MyList2
  def printElements: String
  override def toString: String = String.format("[ %s ]", printElements)
}

object Empty2 extends MyList2{
  override def head: Int = throw new NoSuchElementException
  override def tail: MyList2 = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(element: Int): MyList2 = new Cons2(element, Empty2)
  override def printElements: String = ""
}

class Cons2(_head: Int, _tail: MyList2) extends MyList2 {
  override def head: Int = this._head
  override def tail: MyList2 = this._tail
  override def isEmpty: Boolean = false
  override def add(element: Int): MyList2 = new Cons2(element, this)
  override def printElements: String = {
    if (tail.isEmpty) "" + head
    else head + " " + tail.printElements
  }
}

object ListTest extends App {
  val list = new Cons2(10, Empty2)
  val list2 = new Cons2(10, new Cons2(20, new Cons2(30, Empty2)))
  println(list.head)
  println(list2.tail.head)
  println(list2.toString)
}