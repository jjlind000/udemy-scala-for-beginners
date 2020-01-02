package lectures.part1basics

/**
 * Created by User on 29/12/2019
 */
object DefaultArgs extends App {
  def foo(i:Int = 10, j:Int = 20) = printf("%d %d", i, j)
  foo()
}
