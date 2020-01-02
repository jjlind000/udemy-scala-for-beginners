package lectures.part2oop

/**
 * Created by User on 29/12/2019
 */
object MethodNotations extends App{
  class Person(val name:String, favouriteFilm: String ){
    def likesUsingStringEquals(film: String ) : Boolean = film.equals(favouriteFilm)
    def likesUsingEqualsOperator(film: String ) : Boolean = film == favouriteFilm
  }

  val Mary: Person = new Person("Mary","Inception")
  println(Mary.likesUsingStringEquals("Inception"))
  println(Mary likesUsingStringEquals "Inception")
  println(Mary.likesUsingEqualsOperator("Inception"))

  class Foo(val s: String)
  val foo1 = new Foo("abc");
  val foo2 = new Foo("abc");
  println(foo1.equals(foo2))     //false
  println(foo1 == foo2)          //false
  println(foo1.s.equals(foo2.s)) //true
  println(foo1.s == foo2.s)      //true

  class Bar() {
    def baz(s: String = "abc", t:String = "def") = s"hello this is baz with $s and $t"
  }

  val bar1 = new Bar()
  val s = bar1 baz "xyz"
  println(s) //hello this is baz with xyz and def

  class Baz(val name: String){
    def +(other: Baz): Baz = new Baz(this.name + other.name)
    def unary_! : String = s"this is $name!"
  }
  val baz0 = new Baz("Foobar")
  println(!baz0)
  println(baz0.unary_!)
  val baz1 = new Baz("abc")
  val baz2 = new Baz("def")
  println((baz1 + baz2).name)
  println(baz1.+(baz2).name)
  println(1 + 2)
  println(1.+(2))

  val b: Boolean = true
  println(b.unary_!)

  class Frobber(val s: String = "blah" ){
    def apply(): String = s"hey this is $s"
    def apply(i: Int): String = s"hey this is $s with $i widgets"
  }
  val frobber = new Frobber()
  println(frobber)         //lectures.part2oop.MethodNotations$Frobber@f5f2bb7
  println(frobber())       //hey this is blah
  println(frobber.apply()) //hey this is blah
  println(frobber(10))     //hey this is blah with 10 widgets
}
