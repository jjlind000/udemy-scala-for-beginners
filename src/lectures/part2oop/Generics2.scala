package lectures.part2oop

/**
 * Created by User on 30/12/2019
 */
object Generics2 extends App {

  class Animal
  class Dog extends Animal
  class Cat extends Animal
  class MyList[+T]{
//    var value1 = ???
//    var value2 = ???
    //CANNOT DO:
    //def add(t:T) : MyList[T]
    //OK:
    def add[U>:T](u:U) : MyList[U] = {
      val list = new MyList[U]
      //list.value2 = u
      return list
    }
  }
  val myList:MyList[Animal] = new MyList[Animal]
  val myList2 = new MyList[Cat]
  val myList3 : MyList[Animal] = myList2.add(new Dog)
}
