package lectures.part2oop

/**
 * Created by User on 30/12/2019
 */
object Exceptions extends App{
  //val e = throw new RuntimeException ("Uck")
  val exp = try { throw new
    //IllegalArgumentException("IAE")
    RuntimeException("RE")
  }
  catch { case excep : IllegalArgumentException => println("caught this: " + excep.getMessage)}
  finally { println ("I'm done")}


}
