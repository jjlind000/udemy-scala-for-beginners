package lectures.part2oop

/**
 * Created by User on 29/12/2019
 */
object Objects extends App{
  object Foo
  val foo1 = Foo; val foo2 = Foo
  println(foo1==foo2)

  object Bar {
    def apply(s: String, i: Int): Bar = new Bar(s, i)
  }
  class Bar(val s: String, val i: Int) {
    override def toString: String = s"$s, $i"
  }

  println(Bar("frobber", 44))

}
