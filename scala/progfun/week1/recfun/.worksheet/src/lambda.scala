
import javax.naming.InvalidNameException

object lambda {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(78); 

  val zero =  Zero;System.out.println("""zero  : Zero.type = """ + $show(zero ));$skip(27); 
  val one = new Succ(zero);System.out.println("""one  : Succ = """ + $show(one ));$skip(26); 
  val two = new Succ(one);System.out.println("""two  : Succ = """ + $show(two ));$skip(23); val res$0 = 
  one.successor == two;System.out.println("""res0: Boolean = """ + $show(res$0));$skip(23); val res$1 = 
  one.successor == one;System.out.println("""res1: Boolean = """ + $show(res$1));$skip(19); val res$2 = 
  one + one == two;System.out.println("""res2: Boolean = """ + $show(res$2));$skip(20); val res$3 = 
  one + one == zero;System.out.println("""res3: Boolean = """ + $show(res$3));$skip(19); val res$4 = 
  one + one == one;System.out.println("""res4: Boolean = """ + $show(res$4));$skip(20); val res$5 = 
  one - one == zero;System.out.println("""res5: Boolean = """ + $show(res$5));$skip(19); val res$6 = 
  two - one == one;System.out.println("""res6: Boolean = """ + $show(res$6))}
}

abstract class Nat {
  def isZero: Boolean
  def successor: Nat
  def predecessor: Nat
  def +(x: Nat): Nat
  def -(x: Nat): Nat
  def ==(x: Nat): Boolean
}

object Zero extends Nat {;$skip(216); 
  def isZero: Boolean = true;System.out.println("""isZero: => Boolean""");$skip(38); 
  def successor: Nat = new Succ(this);System.out.println("""successor: => Nat""");$skip(76); 
  def predecessor: Nat = throw new InvalidNameException("zero.predecessor");System.out.println("""predecessor: => Nat""");$skip(25); 
  def +(x: Nat): Nat = x;System.out.println("""+ : (x: Nat)Nat""");$skip(101); 
  def -(x: Nat): Nat = if (x.isZero) x else throw new InvalidNameException("zero - x , with x != 0");System.out.println("""- : (x: Nat)Nat""");$skip(37); 
  def ==(x: Nat): Boolean = x.isZero;System.out.println("""== : (x: Nat)Boolean""")
}

class Succ(n: Nat) extends Nat {
  def isZero: Boolean = false
  def successor: Nat = new Succ(this)
  def predecessor: Nat = n
  def +(x: Nat): Nat = new Succ(n + x)
  def -(x: Nat): Nat = if (x.isZero) this else n - x.predecessor
  def ==(x: Nat): Boolean = if (x.isZero) false else this.predecessor == x.predecessor
}