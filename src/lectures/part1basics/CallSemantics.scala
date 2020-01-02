package lectures.part1basics

/**
 * Created by User on 29/12/2019
 */
object CallSemantics extends App{
  def calledByValue(x: Long) : Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long) : Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

}
