package lectures.part3funcprog.ex28

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
  def map2a[G](transformer: A=>G) : MyList[G]
  //filter is a method that takes "a MyPredicate that does some kind of test on an element (implicitly an A) and returns a Boolean" and returns a MyList[A]
  //def filter(predicate: MyPredicate[A]) : MyList[A]
  //instead of MyPredicate use a Function1[A,Boolean] instead (1):
  def filter(predicate: A=>Boolean) : MyList[A]
  //instead of MyPredicate use a Function1[A,Boolean] instead (2):
  def filter2(predicate: Function1[A,Boolean]) : MyList[A]
  //def flatMap[FF](transformer2: MyTransformer2[A, MyList[FF]]) : MyList[FF]
  def flatMap[FF](transformer2: A=>MyList[FF]) : MyList[FF]
  def ++[CC >: A] (list: MyList[CC]) : MyList[CC]
  def foreach(f: A=>Unit) : Unit
  def sort(f : (A, A)=>Int)
  def sort2(f: (A, A) => Int): MyList[A]
  //def zipWith[C](list:MyList[A], f:(A, A)=>C) : MyList[C]
  //def zipWith[C](list:MyList[A], function2: Function2[A,A,C]) : MyList[C]
  def zipWith[B,C](list: MyList[B], zipFunc: (A, B)=>C) : MyList[C]

  def fold[B](start: B)(operator: (B,A)=>B) : B

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

  override def map2a[G](transformer: Nothing => G): MyList[G] = Empty

  override def foreach(f: Nothing => Unit): Unit = () //{}
  override def sort(f: (Nothing, Nothing) => Int): Unit = 0

  override def sort2(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], zipFunc: (Nothing, B) => C): MyList[C] = {
    if(!list.isEmpty) throw new RuntimeException(s"Cannot zip list ${list.toString} with this empty list")
    else Empty
  }

  override def fold[B](start: B)( operator: (B, Nothing) => B): B = start
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

  override def map2a[ZZ](transformer: A=> ZZ): MyList[ZZ] = {
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

/* NO!
  override def foreach(f: A => Unit): Unit = {
    def foreachfrom(f2: A=>Unit, myList: MyList[A]): Unit ={
      if(myList.head != null) {
        f2(myList.head)
        if (myList.tail != null)
          foreachfrom(f2, myList.tail)
      }
    }
    foreachfrom(f, this)
  }
*/
  //foreach is an instance method; it's applied to "this" List, and each time it's applied, the argument f is applied
  //to the head element of "this" list; then foreach(f) is called on the tail element of "this" list
  //When tail.foreach(f) is invoked, the tail becomes the new "this" list; f is then applied to the head of the tail and so on
  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(f: (A, A) => Int): Unit = (a1 : A, a2: A) => f(a1, a2)

  override def sort2(compare: (A, A) => Int): MyList[A] = {
    def insert(element: A, sortedList : MyList[A]): MyList[A] = {
      if(sortedList.isEmpty) {
        println(String.format("sortedList.isEmpty - %s - %s", element.toString, sortedList.toString))
        new Cons(element, Empty)
      }
      else if (compare(element, sortedList.head) <=0) {
        println(String.format("element %s < head - %s - %s", element.toString, sortedList.head, sortedList.toString))
        new Cons(element, sortedList)
      }
      else {
        println(String.format("element %s > head - %s - inserting element %s into sortedList.tail %s ", element.toString, sortedList.head, element.toString, sortedList.tail.toString))
        new Cons(sortedList.head, insert(element, sortedList.tail))
      }
    }
    //uses insertion sort: call tail.sort with the same compare function
    println(String.format("sorting list %s - about to call sort2 on this list's tail - %s", this.toString, this.tail.toString))
    val sortedTailList = tail.sort2(compare)
    println(String.format("sortedTailList is %s - about to insert head %s ito it", sortedTailList.toString, head.toString))
    insert(head, sortedTailList)
  }

  override def zipWith[B, C](list: MyList[B], zipFunc: (A, B) => C): MyList[C] = {
    if(list.isEmpty) throw new RuntimeException(s"Cannot zip empty list with this list - ${list.toString}")
    else {
      new Cons(zipFunc(this.head, list.head), tail.zipWith(list.tail,zipFunc))
    }
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    val newStart = operator(start, this.head)
    tail.fold(newStart)(operator)
  }
}

object ListTest extends App {
  val listOfIntegers : MyList[Int] = Empty
  val listOfIntegers2 : MyList[Int] = new Cons(10, new Cons(20, new Cons(30, Empty)))
  val listOfIntegers3 : MyList[Int] = new Cons(40, new Cons(50, new Cons(60, Empty)))
  val listOfIntegers4 : MyList[Int] = new Cons(60, new Cons(50, new Cons(10, Empty)))
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
  println(listOfIntegers2.map( _ * 2).toString)
  println(listOfIntegers2.map(fTripler3).toString)

  println(listOfIntegers2.map2((a: Int) => a*3).toString)
  println(listOfIntegers2.map2(_*3).toString)
  println(listOfIntegers2.filter(new Function1[Int,Boolean] {
    override def apply(t: Int): Boolean = t%6==0
  }).toString)
  println(listOfIntegers2.filter((t:Int)=>t%6==0).toString)

  //class IntToStringTransformer extends MyTransformer[MyList[Int],MyList[String]] {
  //  override def apply[T]: MyList[String] =
  //}
  println(listOfIntegers2 ++ listOfIntegers3)
  println(listOfIntegers2.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(a: Int): MyList[Int] = new Cons[Int](a, new Cons(a+1, Empty))
  })).toString
  println(listOfIntegers2.flatMap((a: Int) => new Cons[Int](a, new Cons(a+1, Empty)))).toString

  listOfIntegers3.foreach((i:Int)=> println("i ===> " + i))
  listOfIntegers3.foreach((i:Int)=> println("i ===> " + i))
  listOfIntegers3.foreach(println)
  println(listOfIntegers4.sort2((a1, a2)=> a1-a2))

  println(listOfIntegers2.zipWith(listOfStrings2, ((i: Int, s: String) => String.format("[i = %s, s = %s]", i, s))))
  println(listOfIntegers2.zipWith[String,String](listOfStrings2, String.format("[i = %s, s = %s]", _, _)))

  println(listOfIntegers3)
  println(listOfIntegers3.fold(1000)((i: Int, j: Int)=> i + j))

  val forComp = for {
    n <- listOfIntegers3
    s <- listOfStrings2
  } yield "=======>>>> " + n + "=======> " + s

  println(forComp)
}
