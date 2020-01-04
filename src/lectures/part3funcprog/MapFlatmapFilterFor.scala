package lectures.part3funcprog

import scala.annotation.tailrec

object MapFlatmapFilterFor extends App {
  var list=List(1,2,3) //calling List.apply on companion object
  println(list)                           //List(1, 2, 3)
  println(list.head)                      //List(1)
  println(list.tail)                      //List(2, 3)
  println(list.filter(x=>x%2==0))         //List(2)
  println(list.map("value: " + _ ))       //List(value: 1, value: 2, value: 3)
  println(list.flatMap(List(_, 4, 6, 8))) //List(1, 4, 6, 8, 2, 4, 6, 8, 3, 4, 6, 8)

  val nums = List(1,2,3,4)
  val chars= List('a','b','c','d')

  val resultListx: List[String] = List("a","b","c")
//  val resultList2 = resultList.flatMap("1==>" + _)
//  val resultList3 = resultList.flatMap( x => "==>" + x)


  //println(resultListx.flatMap(x=> List(x, list.head.toString + list.head.toString)))

  //a b c d 1 2 3 4
  //do list1.head with list2.head
  //   list1.head with list2.tail a b c d 2 3 4
  //...
  //   until list2 is empty
  // then start again with list2.tail

  def combinemessy[A,B] (listA : List[A], listB: List[B]):List[String] = {
    def helper (listA : List[A], listB: List[B], origlistA : List[A], origlistB: List[B], resultList: List[String]) : List[String] = {
      if(listA.isEmpty/* && listB.isEmpty*/) {
          println("returning .. ")
          return resultList
        }
      else if (listB.isEmpty) {
        println(s"listB empty - listA = ${listA} .. ")
          return helper(listA.tail, origlistB, origlistA, origlistB, resultList)
      }
      else {
        println(s"listA = ${listA} .. , listB = ${listB}")
        println(s"doing...resultList=${resultList}")
        val result : List[String]=List(listA.head.toString + listB.head.toString)
        val resultList2 : List[String] = resultList.concat(result)
        println(s"doing...resultList2=${resultList2}")
        return helper(listA, listB.tail, origlistA, origlistB, resultList2)
      }
    }
    return helper(listA, listB, listA, listB, List.empty)
  }

  def combine[A,B] (listA : List[A], listB: List[B]):List[String] = {
    @tailrec
    def helper (listA : List[A], listB: List[B], origlistA : List[A], origlistB: List[B], resultList: List[String]) : List[String] = {
      if(listA.isEmpty)
        resultList
      else if (listB.isEmpty) {
        helper(listA.tail, origlistB, origlistA, origlistB, resultList)
      }
      else {
        val result : List[String]=List(listA.head.toString + listB.head.toString)
        val resultList2 : List[String] = resultList.concat(result)
        helper(listA, listB.tail, origlistA, origlistB, resultList2)
      }
    }
    helper(listA, listB, listA, listB, List.empty)
  }

  println(combine(nums, chars))

  val thisList = nums;
  val otherList = chars;
  val strings = List("AA", "BB", "CC")
  val f: (Int, Char) => String = (x:Int, y:Char) => "" + x + "==>" + y
  val g: (Int, Char, String) => String = (x:Int, y:Char, z:String) => "" + x + "==>" + y + "(" + z + ")" + "\n"

  println(thisList.flatMap(x=>otherList.map(y=>f(x,y))))
  println(thisList.flatMap(a=>otherList.flatMap(b=>strings.map(c=> g(a,b,c)))))

  val forComp = for {
    i <- nums if i%2 == 0
    c <- chars
    s <- strings
  } yield "" + i + "==>" + c + "(" + s + ")"

  for {
    i <- nums if i % 2 == 0
  } println (">>" + i)

  val l = nums.map {
    i => i*2
  }

  println(l)

  //println(forComp)
}
