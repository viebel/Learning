

object play {

  val a = (1, "a")                                //> a  : (Int, java.lang.String) = (1,a)
  val (c, d) = (1, "a")                           //> c  : Int = 1
                                                  //| d  : java.lang.String = a

  List(1, 1, 2, 3).groupBy((x) => x)              //> res0: scala.collection.immutable.Map[Int,List[Int]] = Map(3 -> List(3), 1 -> 
                                                  //| List(1, 1), 2 -> List(2))
  List(1, 1, 2, 3).groupBy((x) => x).getOrElse(19, List(9)).head
                                                  //> res1: Int = 9
  (1 to 10).flatMap (List(_, 2))                  //> res2: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 2, 2, 3, 2, 
                                                  //| 4, 2, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2, 10, 2)
 
  List((1,2),(3,4)).unzip                         //> res3: (List[Int], List[Int]) = (List(1, 3),List(2, 4))
 }