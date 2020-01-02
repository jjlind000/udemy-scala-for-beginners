package lectures.part2oop

/**
 * Created by User on 30/12/2019
 */
object AnonymousClasses extends App {
  trait Animal {
    def eat : Unit
  }

  val fooAnimal = new Animal {
    override def eat: Unit = println("some foo")
  }

  println(fooAnimal.getClass)
}
