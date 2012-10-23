

object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(35); 

  val a = (1, "a");System.out.println("""a  : (Int, java.lang.String) = """ + $show(a ));$skip(24); 
  val (c, d) = (1, "a");System.out.println("""c  : Int = """ + $show(c ));System.out.println("""d  : java.lang.String = """ + $show(d ));$skip(24); 

  val k = Tuple2(1, 2);System.out.println("""k  : (Int, Int) = """ + $show(k ));$skip(233); 
  def merge[T <: Int](xs: List[T], ys: List[T]): List[T] =
    (xs, ys) match {
      case (List(), yy) => yy
      case (xx, List()) => xx
      case (x :: xx, y :: yy) => if (x < y) x :: merge(xx, ys) else y :: merge(xs, yy)
    };System.out.println("""merge: [T <: Int](xs: List[T], ys: List[T])List[T]""");$skip(39); val res$0 = 

  merge(List(1, 4, 5), List(2, 3, 6));System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(160); 
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (t, d) = xs span (y => y == x)
      t :: pack(d)
  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(35); val res$1 = 

  pack(List(1, 1, 2, 2, 3, 4, 4));System.out.println("""res1: List[List[Int]] = """ + $show(res$1));$skip(94); 

  def reverse[T](xs: List[T]): List[T] =
    xs.foldLeft(Nil: List[T])((acc, x) => x :: acc);System.out.println("""reverse: [T](xs: List[T])List[T]""");$skip(24); val res$2 = 
	reverse(List(1,2,3,4));System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(29); val res$3 = 
	
	
	
	
	
	List(1,2,1).toSet;System.out.println("""res3: scala.collection.immutable.Set[Int] = """ + $show(res$3))}
}