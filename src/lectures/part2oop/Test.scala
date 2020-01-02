package lectures.part2oop

/**
 * Created by User on 30/12/2019
 */
object Test extends App{
  def foo : String = "foo!"
  val exp = {
    println(foo)
    println("running")
    "exp done"
  }
  println(exp)

}
