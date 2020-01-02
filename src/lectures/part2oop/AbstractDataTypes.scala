package lectures.part2oop

import lectures.part2oop.Objects.Foo

/**
 * Created by User on 29/12/2019
 */
object AbstractDataTypes extends App {
  abstract class Foo {
    val s : String
    def baz(t: String) : String
  }
  class Bar extends Foo {
    val s = "Bar" //or def val s = ...
    def baz(t: String): String = "(Bar) ==> " + s + " " + t //or def baz(t: String): ...
  }
  trait Frobber{
    val s2 : String = "frobber #1"
    var s3 : String
  }

  class Baz extends Foo with Frobber {
    override val s: String = "Baz"
    override val s2: String = "Frobber #2"
    override var s3: String = "Frobber #3"
    override def baz(t: String): String = "(Baz) ==> " + s + " " + t
  }

  val foo = new Baz();
  println(foo.baz("aaa"))
  println(foo.s2)
  println(foo.s3)
}
