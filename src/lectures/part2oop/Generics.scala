package lectures.part2oop

/**
 * Created by User on 29/12/2019
 */
object Generics extends App {
  class Foo[T](val t: T) {
  }
  val obj = new Foo[String]("hello world")
  println(obj.t)

  trait Bar[X]{
    def get : X
    def set(x : X) : Unit
    override def toString: String = "===> " + get.toString
  }
  class Baz extends Bar[String] {
    var x : String = null
    override def get: String = x
    override def set(x : String) = this.x = x
  }

  val baz = new Baz
  baz.set("hello world!")
  println(baz.toString)

  object Yabba {
    //generic method
    def foo[U](val1: U, val2: U) : Boolean = val1 == val2
  }

  val yabba1 = Yabba.foo("aaa", "bbb")
  println("yabba1:" + yabba1)
  val yabba2 = Yabba.foo("aaa", "aaa")
  println("yabba2:" + yabba2)

  class Shape
  class Triangle extends Shape
  class BigTriangle extends Triangle
  class Circle extends Shape
  class InvariantList[T]
  /*CANNOT DO: InvariantList is an invariant type
  val invariantList : InvariantList[Shape] = new InvariantList[Triangle]
  */
  //OK:
  val invariantList : InvariantList[Triangle] = new InvariantList[Triangle]

  class CovariantList[+T]
  val covariantList : CovariantList[Shape] = new CovariantList[Triangle]

  class ContravariantList[-T]
  val contravariantList : ContravariantList[Triangle] = new ContravariantList[Shape]

  //X <: Y means X must be Y or subclass of Y
  class Drawing[T <: Shape] (val shape: T)
  val myTriangleDrawing = new Drawing(new Triangle)
  val myShapeDrawing = new Drawing(new Shape)

  //X >: Y means X must be Y or superclass of Y
  class Sketch[T >: Triangle] (val shape: T)
  //Does not compile:
  //val myTriangleSketch = new Sketch[BigTriangle](new BigTriangle)
  val myShapeSketch = new Sketch[Shape](new Shape)
}
