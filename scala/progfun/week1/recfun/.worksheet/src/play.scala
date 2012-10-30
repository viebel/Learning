

object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(35); 

  val a = (1, "a");System.out.println("""a  : (Int, java.lang.String) = """ + $show(a ));$skip(24); 
  val (c, d) = (1, "a");System.out.println("""c  : Int = """ + $show(c ));System.out.println("""d  : java.lang.String = """ + $show(d ));$skip(38); val res$0 = 

  List(1, 1, 2, 3).groupBy((x) => x);System.out.println("""res0: scala.collection.immutable.Map[Int,List[Int]] = """ + $show(res$0));$skip(65); val res$1 = 
  List(1, 1, 2, 3).groupBy((x) => x).getOrElse(19, List(9)).head;System.out.println("""res1: Int = """ + $show(res$1));$skip(33); val res$2 = 
  (1 to 10).flatMap (List(_, 2));System.out.println("""res2: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$2));$skip(28); val res$3 = 
 
  List((1,2),(3,4)).unzip;System.out.println("""res3: (List[Int], List[Int]) = """ + $show(res$3))}
 }