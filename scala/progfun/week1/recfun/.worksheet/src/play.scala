

object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(35); 

  val a = (1, "a");System.out.println("""a  : (Int, java.lang.String) = """ + $show(a ));$skip(24); 
  val (c, d) = (1, "a");System.out.println("""c  : Int = """ + $show(c ));System.out.println("""d  : java.lang.String = """ + $show(d ));$skip(38); val res$0 = 

  List(1, 1, 2, 3).groupBy((x) => x);System.out.println("""res0: scala.collection.immutable.Map[Int,List[Int]] = """ + $show(res$0));$skip(65); val res$1 = 
  List(1, 1, 2, 3).groupBy((x) => x).getOrElse(19, List(9)).head;System.out.println("""res1: Int = """ + $show(res$1));$skip(32); val res$2 = 
  (1 to 10).flatMap(List(_, 2));System.out.println("""res2: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$2));$skip(29); val res$3 = 

  List(1, 2, 3).contains(2);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(29); val res$4 = 
  List((1, 2), (3, 4)).unzip;System.out.println("""res4: (List[Int], List[Int]) = """ + $show(res$4));$skip(102); 
  def scalarProduct(x: List[Int], y: List[Int]): Int =
    (x zip y).map { case (x, y) => x * y }.sum;System.out.println("""scalarProduct: (x: List[Int], y: List[Int])Int""");$skip(106); 
  def scalarProduct2(x: List[Int], y: List[Int]): Int =
    (for ((xx, yy) <- x zip y) yield xx * yy).sum;System.out.println("""scalarProduct2: (x: List[Int], y: List[Int])Int""");$skip(18); val res$5 = 

List("aaa","bb");System.out.println("""res5: List[java.lang.String] = """ + $show(res$5));$skip(17); val res$6 = 
"adsad".length();System.out.println("""res6: Int = """ + $show(res$6));$skip(13); val res$7 = 
"123" take 1;System.out.println("""res7: String = """ + $show(res$7));$skip(13); val res$8 = 
"123" drop 1;System.out.println("""res8: String = """ + $show(res$8))}
}