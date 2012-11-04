

object play {

  val a = (1, "a")                                //> a  : (Int, java.lang.String) = (1,a)
  val (c, d) = (1, "a")                           //> c  : Int = 1
                                                  //| d  : java.lang.String = a

  List(1, 1, 2, 3).groupBy((x) => x)              //> res0: scala.collection.immutable.Map[Int,List[Int]] = Map(3 -> List(3), 1 -> 
                                                  //| List(1, 1), 2 -> List(2))
  List(1, 1, 2, 3).groupBy((x) => x).getOrElse(19, List(9)).head
                                                  //> res1: Int = 9
  (1 to 10).flatMap(List(_, 2))                   //> res2: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 2, 2, 3, 2, 
                                                  //| 4, 2, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2, 10, 2)

  List(1, 2, 3).contains(2)                       //> res3: Boolean = true
  List((1, 2), (3, 4)).unzip                      //> res4: (List[Int], List[Int]) = (List(1, 3),List(2, 4))
  def scalarProduct(x: List[Int], y: List[Int]): Int =
    (x zip y).map { case (x, y) => x * y }.sum    //> scalarProduct: (x: List[Int], y: List[Int])Int
  def scalarProduct2(x: List[Int], y: List[Int]): Int =
    (for ((xx, yy) <- x zip y) yield xx * yy).sum //> scalarProduct2: (x: List[Int], y: List[Int])Int

List("aaa","bb")                                  //> res5: List[java.lang.String] = List(aaa, bb)
"adsad".length()                                  //> res6: Int = 5
"123" take 1                                      //> res7: String = 1
"123" drop 1                                      //> res8: String = 23
}