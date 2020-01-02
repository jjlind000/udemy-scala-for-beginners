package lectures.part1basics

/**
 * Created by User on 25/12/2019
 */
object Expressions extends App{
  val x = 1+2
  println(x)
    var b1 = true
    var b2 = if(b1) 3 else 4
    println(b2)
    b1=false
    println(b2)

  var i=0
  while(i<10) { println(i); i+=1;}

  val y= (i=3)
  println(y)

  var whilevar=while(i<10) { println(i); i+=1;}
  println(whilevar)

  val cb = { val y=3; val z=4; if(y>2 && z<5) "hello" else "goodbye"}
  println(cb)

  val xy = {
    if(b1) 33 else 44
    55
  }

}
