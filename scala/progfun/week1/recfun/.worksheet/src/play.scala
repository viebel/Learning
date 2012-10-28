

object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(35); 

  val a = (1, "a");System.out.println("""a  : (Int, java.lang.String) = """ + $show(a ));$skip(24); 
  val (c, d) = (1, "a");System.out.println("""c  : Int = """ + $show(c ));System.out.println("""d  : java.lang.String = """ + $show(d ));$skip(38); val res$0 = 

  List(1, 1, 2, 3).groupBy((x) => x);System.out.println("""res0: scala.collection.immutable.Map[Int,List[Int]] = """ + $show(res$0));$skip(65); val res$1 = 
  List(1, 1, 2, 3).groupBy((x) => x).getOrElse(19, List(9)).head;System.out.println("""res1: Int = """ + $show(res$1));$skip(73); val res$2 = 
  List('c', 'b', 'A', 'a').groupBy(_.toLower).mapValues(_.length).toList;System.out.println("""res2: List[(Char, Int)] = """ + $show(res$2));$skip(59); val res$3 = 
  List('c', 'b', 'A', 'a').groupBy(_.toLower).get('c').get;System.out.println("""res3: List[Char] = """ + $show(res$3));$skip(64); val res$4 = 
  List('c', 'b', 'A', 'a').groupBy(_.toLower).find(_._1 == 'c');System.out.println("""res4: Option[(Char, List[Char])] = """ + $show(res$4));$skip(163); 
  def combine[A](xs: Traversable[Traversable[A]]): Seq[Seq[A]] =
    xs.foldLeft(Seq(Seq.empty[A])) {
      (x, y) => for (a <- x.view; b <- y) yield a :+ b
    };System.out.println("""combine: [A](xs: Traversable[Traversable[A]])Seq[Seq[A]]""");$skip(73); val res$5 = 

  combine(Set(Set("a", "b", "c"), Set("1", "2"), Set("S", "T"))).toList;System.out.println("""res5: List[Seq[java.lang.String]] = """ + $show(res$5));$skip(17); val res$6 = 

List.range(1,9);System.out.println("""res6: List[Int] = """ + $show(res$6))}
}