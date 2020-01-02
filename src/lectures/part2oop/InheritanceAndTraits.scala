package lectures.part2oop

/**
 * Created by User on 29/12/2019
 */
object InheritanceAndTraits extends App{
  class Foo(s: String) {
      override def toString() = "Foo: " + this.s
      val t: String = "abc"
      val u: String = "abc"
      val v: String = "ghi"
  }
  class Bar(s: String, override val v: String = "jkl") extends Foo(s){
    override def toString() = "Bar: " + this.s
    override val t: String = "def"
    //CANNOT DO THIS: val u: String = "def"
  }
  val bar = new Bar("barrr")
  println(bar)
  println(bar.t)
  println(bar.u)
  println(bar.v)
}
