

object play {

  val a = (1, "a")                                //> a  : (Int, java.lang.String) = (1,a)
  val (c, d) = (1, "a")                           //> c  : Int = 1
                                                  //| d  : java.lang.String = a

  val k = Tuple2(1, 2)                            //> k  : (Int, Int) = (1,2)
  def merge[T <: Int](xs: List[T], ys: List[T]): List[T] =
    (xs, ys) match {
      case (List(), yy) => yy
      case (xx, List()) => xx
      case (x :: xx, y :: yy) => if (x < y) x :: merge(xx, ys) else y :: merge(xs, yy)
    }                                             //> merge: [T <: Int](xs: List[T], ys: List[T])List[T]

  merge(List(1, 4, 5), List(2, 3, 6))             //> res0: List[Int] = List(1, 2, 3, 4, 5, 6)
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (t, d) = xs span (y => y == x)
      t :: pack(d)
  }                                               //> pack: [T](xs: List[T])List[List[T]]

  pack(List(1, 1, 2, 2, 3, 4, 4))                 //> res1: List[List[Int]] = List(List(1, 1), List(2, 2), List(3), List(4, 4))

  def reverse[T](xs: List[T]): List[T] =
    xs.foldLeft(Nil: List[T])((acc, x) => x :: acc)
                                                  //> reverse: [T](xs: List[T])List[T]
	reverse(List(1,2,3,4))                    //> res2: List[Int] = List(4, 3, 2, 1)
	
	
	
	
	
	List(1,2,1).toSet                         //> res3: scala.collection.immutable.Set[Int] = Set(1, 2)
}