package lectures.part1basics

import scala.annotation.tailrec

/**
 * Created by User on 28/12/2019
 */
object Functions extends App {

  def foo(a: String, b: String): String = a + "===>" + b;

  //println(foo("x","y"))

  def rec(a: String, n: Int): String = if (n == 1) a else a + rec(a, n - 1)

  //println(rec("bar", 10))

  def bar(a: String): Unit = println("hello " + a)

  //bar("world")

  def foo2(a: String, b: String): String = {
    def inner(c: String, d: String): String = "===>" + c + "===>" + d

    a + "===>" + b + inner(a, b)
  }

  //println(foo2("aaa", "bbb"))

  def fact(n: Int): Int = if (n == 1) n else n * fact(n - 1)

  //println(fact(4))

  def fibbo(n: Int): Int = if (n <= 2) 1 else fibbo(n - 1) + fibbo(n - 2)

  //println(fibbo(10))

  /*
    def isPrime(n: Int) : Boolean = {
      //println("isPrime:" + n)
      def hasdivisor(m: Int, x: Int): Boolean = {
        //println("hasDivisor:" + m + ", " + x)
        if (x > m / 2) {
          //println("returning false");
          return false; }
        if (m % x == 0) {
          //println("returning true");
          return true;}
        //println(String.format("calling hasDivisor(%d,%d)",m, x+1));
        return hasdivisor(m, x + 1)
      }
      var b:Boolean = false
      if (n == 1) { b=false;
        //println("n==1 returning " + b);
        return b;}
      b=(!hasdivisor(n, 2)); {
        //println("n!=1 returning " + b);
        return b;}
    }

    def primes(n: Int) {
      if(n==1) return
      println(n + "==>" + isPrime(n))
      primes(n-1)
    }
  */

  def isPrime(n: Int): Boolean = {
    @tailrec
    def hasdivisor(m: Int, x: Int): Boolean = {
      if (x > m / 2) return false
      if (m % x == 0) return true
      return hasdivisor(m, x + 1)
    }

    if (n == 1) return false
    return !hasdivisor(n, 2)
  }

  def primes(n: Int) {
    if (n == 1) return
    println(n + "==>" + isPrime(n))
    primes(n - 1)
  }

  //primes(10)
  //isPrime(10)


  def isPrime2(n: Int) : Boolean = {
    @tailrec
    def isPrimeUntil(x: Int) : Boolean = {
      if(x==1) return true;
      if(n%x==0) { println("divides by " + x); return false; }
      return isPrimeUntil(x-1)
    }
    return isPrimeUntil(n/2)
  }
  def primes2(n: Int) {
    if (n == 1) return
    println(n + "==>" + isPrime2(n))
    primes2(n - 1)
  }
  //primes2(10)

  //println(isPrime(1003))
  //println(isPrime2(1003))

}