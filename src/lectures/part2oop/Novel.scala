package lectures.part2oop

/**
 * Created by User on 29/12/2019
 */
class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def ageOfAuthorAtRelease() : Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(authorFullName: String) : Boolean = authorFullName.equals(this.author.fullName())
  def copy(newReleaseYear: Int) : Novel = new Novel(this.name, newReleaseYear, this.author)
}

class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
  def fullName() : String = s"$firstName $lastName"
}

class Counter(value: Int) {
  def currentCount(): Int = this.value
  def increment(): Counter = new Counter(this.value+1)
  def increment(incrementValue: Int): Counter = new Counter(this.value+incrementValue)
}

class Counter2(val value: Int) {
  def currentCount(): Int = this.value
  def increment(): Counter2 = { println("incrementing "); new Counter2(this.value+1) }
  def increment(n: Int): Counter2 = {
    if(n<=0) this
    else {
//      val countertemp = increment()
//      countertemp.increment(n-1)
//      this.increment().increment(n-1)
        increment().increment(n-1)
    }
  }
}

object Main extends App{
  var dickens = new Writer("Charles", "Dickens", 1812)
  var novel: Novel = new Novel("Great Expectations", 1861, dickens)
  println(novel.isWrittenBy("Charles Dickens"))
  println(novel.ageOfAuthorAtRelease)

  val counter : Counter2 = new Counter2(10)
  println(counter.value)
  val counter2 = counter.increment(5)
  println(counter.value)
  println(counter2.value)
}