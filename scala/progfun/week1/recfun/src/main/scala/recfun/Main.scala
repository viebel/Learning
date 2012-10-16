package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    print(balance("())(".toList))
  }
  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c <= 0 || r <= 0) 1 else pascal(c - 1, r - 1) + (if (c <= r - 1) pascal(c, r - 1) else 0)

  /**
   * Exercise 2
   */

  def balance(chars: List[Char]): Boolean = {
    def count(chars: List[Char], res: Int): Int = {
      def update(res: Int): Int = {
        def weight(c: Char): Int =
          if (c == '(') 1 else (if (c == ')') -1 else 0)
        if (res < 0) res else res + weight(chars.head)
      }

      if (chars.isEmpty) res else count(chars.tail, update(res))
    }
    count(chars, 0) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0) 1 else (
      if (coins.isEmpty) 0 else {
        val coin = coins.head
        val rest = coins.tail
        List.range(0, money + coin, coin).foldLeft(0)((total, i) =>
          total + countChange(money - i, rest))
      }) 

}
