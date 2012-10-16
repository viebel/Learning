
object play {
  new IntSerie(5, 3)                              //> res0: IntSerie = IntSerie@34fbb7cb
 def h(s : Serie) = s.head                        //> h: (s: Serie)Any
 h(new IntSerie(8,9))                             //> res1: Any = 8
 
 List((1,2),(3,4)).remove( tuple => tuple._1 == 1)//> res2: List[(Int, Int)] = List((3,4))
        List((1,2),(3,4)).filter( tuple => tuple._1 == 1).head
                                                  //> res3: (Int, Int) = (1,2)
       
 (1,2,3,4,5)                                      //> res4: (Int, Int, Int, Int, Int) = (1,2,3,4,5)
 1 :: List(2,3)                                   //> res5: List[Int] = List(1, 2, 3)
 def add(xs: List[(Char, Int)], c: Char) = {
      def find = {
        val elements = xs.filter(tuple => tuple._1 == c)
        if (elements.nonEmpty) elements.head._2 else 0
      }
      (c, find + 1) :: xs.remove(tuple => tuple._1 == c)
    }                                             //> add: (xs: List[(Char, Int)], c: Char)List[(Char, Int)]
 add(List(('a',3), ('b',7)), 'c')                 //> res6: List[(Char, Int)] = List((c,1), (a,3), (b,7))
      
      
  List((1,2),(3,4)).find( tuple => tuple._1 == 1) //> res7: Option[(Int, Int)] = Some((1,2))
    List((1,2)).sortWith((a,b) => a._1 < b._1)    //> res8: List[(Int, Int)] = List((1,2))
}


trait Serie {
   def head : Any
   def tail : Serie
}

class IntSerie(a: Int, n: Int) extends Serie{
  require(n > 0, "n must be greater than 0")
  def head: Int = a
  def tail: IntSerie = if (n > 1) new IntSerie(a, n - 1) else null
  //override def toString : String = head + (if (tail != null) "," + tail.toString else "")
}

class StringSerie(a: String, n : Int) extends Serie {
  def head: String = a
  def tail: StringSerie = if (n > 1) new StringSerie(a, n - 1) else null
}