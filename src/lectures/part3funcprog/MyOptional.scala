package lectures.part3funcprog

abstract class MyOptional[+T] {
  //map needs to map the contained value (a T) to some other value (let's say an R)
  //READ:
  //define map, which -uses generic type R (and also implicitly the class's generic type T)
  //                  -takes a parameter called mappingFunction, which is of type T=>R, which means, a fn that takes a T and returns an R
  //                  -returns a MyOptional, of type R
  def map[R] (mappingFunction: T => R) : MyOptional[R]

  //flatMap needs to map the contained value (a T) to an Optional[R]
  //Consider that in Java Streams, on a non-Optional, stream.flatMap takes a function that takes a T and **for each T**
  //returns a Stream<R> and then flattens that collection of streams (unnests it) into a **single** stream of R.
  //Whereas on an Optional, flatMap takes a function that takes a T and returns an R; and then flattens that (unnests it) into an Optional<R>
  //So if you have a function T=>Optional<R> that you want to use on your Optional<T>, then
  //  if you use map eg myOpt.map(f) you get an Optional<Optional<R>>
  //  if you use flatMap eg myOpt.flatMap(f) you get an Optional<R>
  //So in this case we have a fn that takes a T and returns an Optional[R], and our flatMap will return an Optional[R]
  def flatMap[R] (function: T=>MyOptional[R]) : MyOptional[R]

  //filter function needs to take a predicate that takes a T and returns a Boolean
  //and the return from filter needs to be an Optional[T]
  def filter (filterFunc: T=>Boolean) : MyOptional[T]
}
case object MyOptionalEmpty extends MyOptional[Nothing] {
  override def map[R](mappingFunction: Nothing => R): MyOptional[R] = MyOptionalEmpty
  override def flatMap[R](function: Nothing => MyOptional[R]): MyOptional[R] = MyOptionalEmpty
  override def filter(filterFunc: Nothing => Boolean): MyOptional[Nothing] = MyOptionalEmpty
  override def toString: String = "EMPTY"
}
case class MyOptionalNotEmpty[+T] (t: T) extends MyOptional[T] {
  override def map[R](mappingFunction: T => R): MyOptional[R] = MyOptionalNotEmpty(mappingFunction(t))
  override def flatMap[R](flatMapFunction: T => MyOptional[R]): MyOptional[R] = flatMapFunction(t)
  override def filter(filterFunc: T => Boolean): MyOptional[T] = if(filterFunc(t)) this else MyOptionalEmpty
  override def toString: String = s"[${t}]"
}

object TestIt extends App {
  val opt = MyOptionalNotEmpty(3)
  val mapped = opt.map("===>" + _)
  val flatMapped = opt.flatMap(x => MyOptionalNotEmpty(">>>" + x + "<<<"))
  val filtered = opt.filter(x => x%3 == 0)
  val filtered2 = MyOptionalNotEmpty(2).filter(x => x%3 == 0)
  println(opt)
  println(mapped)
  println(flatMapped)
  println(filtered)
  println(filtered2)
}

