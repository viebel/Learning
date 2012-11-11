

object play {
  lazy val inf: Stream[Int] = 1 #:: (inf map (2 * _))
                                                  //> inf  : Stream[Int] = <lazy>
  inf.take(10).toList                             //> res0: List[Int] = List(1, 2, 4, 8, 16, 32, 64, 128, 256, 512)
  try {
    Vector(1, 2)(8)
  } catch {
    case e: IndexOutOfBoundsException => 4
  }                                               //> res1: Int = 4

  def create: Int => Boolean = {
    (x =>  false)
  }                                               //> create: => Int => Boolean
 
 moo(4) match {
  case moo(y) => 8
  }                                               //> res2: Int = 8
  
  moo(8) == moo(9)                                //> res3: Boolean = false
  moo(8) == moo(8)                                //> res4: Boolean = true

}

case class moo(x: Int) {
}
  