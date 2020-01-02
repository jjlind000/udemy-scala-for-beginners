package lectures.part1basics

/**
 * Created by User on 29/12/2019
 */
object StringOps extends App{
  val s:String= "this is a test"
  val s2=s.split(" ")
  var s3 = Array("a", "b", "c")
  println(s2)
  println(s3)
  println(s3.toList)

  var i = 45
  var j='4'
  var k="5"
  var s4:String = j +: " foo"
  var s5:String = " foo" :+ j

  var s6="hello"
  println(s6.take(3))

  val name="Baz"; val age=44
  val s7=s"hello my name is $name and my age is $age"
  val s8=s"hello my name is $name and at my next bday my age wil be ${val inc=1; age+inc}"
  println(s7)
  println(s8)

  val height=1.763
  val s9=f"hello my name is $name and I am $height%.2fm tall"
  println(s9)

  val s10=raw"this is a string that contains a line break \n stop"
  val s11="this is a string that contains a line break \n stop"
  println(s10) //this is a string that contains a line break \n stop
  println(s11) //this is a string that contains a line break
               // stop
  println(raw"$s10") //this is a string that contains a line break \n stop
  println(raw"$s11") //this is a string that contains a line break
                     // stop
}
