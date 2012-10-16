
import javax.naming.InvalidNameException

object lambda {

  val zero =  Zero
  val one = new Succ(zero)
  val two = new Succ(one)
  one.successor == two
  one.successor == one
  one + one == two
  one + one == zero
  one + one == one
  one - one == zero
  two - one == one
}

abstract class Nat {
  def isZero: Boolean
  def successor: Nat
  def predecessor: Nat
  def +(x: Nat): Nat
  def -(x: Nat): Nat
  def ==(x: Nat): Boolean
}

object Zero extends Nat {
  def isZero: Boolean = true
  def successor: Nat = new Succ(this)
  def predecessor: Nat = throw new InvalidNameException("zero.predecessor")
  def +(x: Nat): Nat = x
  def -(x: Nat): Nat = if (x.isZero) x else throw new InvalidNameException("zero - x , with x != 0")
  def ==(x: Nat): Boolean = x.isZero
}

class Succ(n: Nat) extends Nat {
  def isZero: Boolean = false
  def successor: Nat = new Succ(this)
  def predecessor: Nat = n
  def +(x: Nat): Nat = new Succ(n + x)
  def -(x: Nat): Nat = if (x.isZero) this else n - x.predecessor
  def ==(x: Nat): Boolean = if (x.isZero) false else this.predecessor == x.predecessor
}