

object play {

  val a = (1, "a")                                //> a  : (Int, java.lang.String) = (1,a)
  val (c, d) = (1, "a")                           //> c  : Int = 1
                                                  //| d  : java.lang.String = a

  List(1, 1, 2, 3).groupBy((x) => x)              //> res0: scala.collection.immutable.Map[Int,List[Int]] = Map(3 -> List(3), 1 -> 
                                                  //| List(1, 1), 2 -> List(2))
  List(1, 1, 2, 3).groupBy((x) => x).getOrElse(19, List(9)).head
                                                  //> res1: Int = 9
  List('c', 'b', 'A', 'a').groupBy(_.toLower).mapValues(_.length).toList
                                                  //> res2: List[(Char, Int)] = List((c,1), (a,2), (b,1))
  List('c', 'b', 'A', 'a').groupBy(_.toLower).get('c').get
                                                  //> res3: List[Char] = List(c)
  List('c', 'b', 'A', 'a').groupBy(_.toLower).find(_._1 == 'c')
                                                  //> res4: Option[(Char, List[Char])] = Some((c,List(c)))
  def combine[A](xs: Traversable[Traversable[A]]): Seq[Seq[A]] =
    xs.foldLeft(Seq(Seq.empty[A])) {
      (x, y) => for (a <- x.view; b <- y) yield a :+ b
    }                                             //> combine: [A](xs: Traversable[Traversable[A]])Seq[Seq[A]]

  combine(Set(Set("a", "b", "c"), Set("1", "2"), Set("S", "T"))).toList
                                                  //> res5: List[Seq[java.lang.String]] = List(List(a, 1, S), List(a, 1, T), List(
                                                  //| a, 2, S), List(a, 2, T), List(b, 1, S), List(b, 1, T), List(b, 2, S), List(b
                                                  //| , 2, T), List(c, 1, S), List(c, 1, T), List(c, 2, S), List(c, 2, T))

List.range(1,9)                                   //> res6: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
}