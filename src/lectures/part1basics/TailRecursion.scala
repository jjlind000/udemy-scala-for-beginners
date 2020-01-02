package lectures.part1basics

import scala.annotation.tailrec

/**
 * Created by User on 28/12/2019
 */
object TailRecursion extends App{

  def fac(n: Int): Int = {
    if(n==1 ) return 1;
    return n* fac(n-1)
  }

  //println(fac(5))

  def tailFac(n: Int): Int = {
    def helper(x: Int, accumulator: Int) : Int = {
      if(x<=1) return accumulator;
      return helper(x-1, x*accumulator)
    }
    helper(n, 1)
  }

  def tailFac2(n: Int): BigInt = {
    def helper(x: Int, accumulator: BigInt) : BigInt = {
      if(x<=1) return accumulator;
      return helper(x-1, x*accumulator)
    }
    helper(n, 1)
  }

  def tailFac3(n: Int): BigInt = {
    def helper(x: Int, accumulator: BigInt) : BigInt = {
      if(x<=1) return accumulator;
      return x* helper(x-1, x*accumulator)
    }
    helper(n, 1)
  }

  //println(fac(500))
  //println(tailFac2(500))
  //println(tailFac3(5000))

  def printx( n : Int) : Unit =
  {
    if (n < 0)  return;
    print(" " + n);
    // The last executed statement is recursive call
    printx(n-1);
  }

  //printx(10)

  def conc(s:String, n:Int) : String = {
    @tailrec
    def helper(s:String, n:Int, accum:String): String = {
      if(n==0) return accum;
      return helper(s, n-1, accum + s)
    }
    return helper(s, n, "")
  }

  def conc2(s:String, n:Int) : String = {
    @tailrec
    def helper(s:String, m:Int, accum:String): String = {
      if(m>n) return accum;
      return helper(s, m+1, accum + s)
    }
    return helper(s, 1, "")
  }



  println(conc("foo", 10))
  println(conc2("foo", 10))
  /* 13:
  13/2 : 2>13/2 no
         13%2=0 no


  */
  def isPrime(i: Int) : Unit = {
    @tailrec
    def isPrimeHelper(j: Int) : Boolean = {
      if(j>i/2) return true;
      if(i%j==0) return false;
      return isPrimeHelper(j+1);
    }
    println(String.format( "%d is prime? ==> %b" , i, isPrimeHelper(2)));
  }

  isPrime(1003)
  isPrime(2003)
/*
1 1 2 3 5 8

 */

  def fibonacci(i: Int) : Unit = {
    @tailrec
    def fibHelper(j: Int, k: Int): Unit = {
      if(j + k > i) return;
      printf("%d ", j+k)
      fibHelper(k, j+k)
    }
    if (i < 1 ) return;
    print("1 1 ");
    fibHelper(1, 1)
  }
    fibonacci(150)
}
