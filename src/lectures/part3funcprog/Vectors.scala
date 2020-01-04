package lectures.part3funcprog

import scala.util.Random

object Vectors extends App {

  val numRuns = 1000
  val capacity = 1000000
  //get avg w time for coll
  def getWriteTime (collection: Seq[Int]) : Double = {
    val rnd = new Random()
    val times = for {
          iter <- 1 to numRuns
        } yield { //yield will run at each iteration of the loop
          val currentTime = System.nanoTime()
          //replace an element at a random index with zero
          collection.updated(rnd.nextInt(capacity), 0)
          System.nanoTime() - currentTime
        }
        //calculate and return the average time
        times.sum * 1.0 / numRuns
    }

    val numbersList = (1 to capacity).toList
    val numbersVector = (1 to capacity).toVector

    println(getWriteTime(numbersList)) //9370723.487
    println(getWriteTime(numbersVector))//8350.865
}
