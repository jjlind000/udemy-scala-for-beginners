package lectures.part2oop

/**
 * Created by User on 29/12/2019
 */
class Person(val name:String, age:Int) {
  val x=100; println("foobar")
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
class Rectangle(height: Int = 0, length: Int = 0)
object OOBasics extends App {
  val person = new Person("Bob", 55)
  //cannot do this: println(person.age)
  println(person.name)
  println(person.x)
}

