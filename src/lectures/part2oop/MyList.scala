package lectures.part2oop

import scala.annotation.tailrec

/**
 * Created by User on 29/12/2019
 */
class MyList() {
  class Node(val x: Int, var next: Node) {
    override def toString: String = String.valueOf(x)
  }
  var head: Node = null;
  def tail : Node = head.next;
  @tailrec
  final def getlast(current: Node = this.head) : Node = {
      if(current == null ) null
      else if(current.next == null ) current
      else getlast(current.next)
  }
  def isEmpty : Boolean = {
    if (this.head == null) return true else false
  }

  override def toString: String = {
    if (isEmpty) "[EMPTY]"
    else "[ " + nodesAsString() + "]"
  }

  @tailrec
  final def nodesAsString(current: Node = head, accum: String = "") : String = {
    if(current == null) accum;
    else nodesAsString(current.next, accum + current.toString + " ")
  }

  def add(j : Int) : Unit = {
    val newNode : Node = new Node(j, null)
    val lastNode : Node = getlast()
    if(lastNode==null) head = newNode
    else lastNode.next = newNode
  }
}

object Runner extends App {
  val myList : MyList = new MyList()
  myList.add(10);
  myList.add(20);
  myList.add(30);
  println(myList.toString)
}
