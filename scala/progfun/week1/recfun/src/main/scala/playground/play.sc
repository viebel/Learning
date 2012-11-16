


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
 

 Set(1) + 1                                       //> res2: scala.collection.immutable.Set[Int] = Set(1)
 Stream.cons(1, Stream(1,2))                      //> res3: Stream.Cons[Int] = Stream(1, ?)
 1 #:: Stream(1,2)                                //> res4: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  Set(moo(1)) contains moo(1)                     //> res5: Boolean = true
    Set(boo(moo(1))) contains boo(moo(1))         //> res6: Boolean = true
 
 Stream().isEmpty                                 //> res7: Boolean = true
 (   Stream(1,2) #::: Stream(3)).apply(2)         //> res8: Int = 3
}


case class moo(x: Int);
case class boo(m: moo);
  