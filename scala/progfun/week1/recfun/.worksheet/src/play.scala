
object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(35); val res$0 = 
  new IntSerie(5, 3);System.out.println("""res0: IntSerie = """ + $show(res$0));$skip(27); 
 def h(s : Serie) = s.head;System.out.println("""h: (s: Serie)Any""");$skip(22); val res$1 = 
 h(new IntSerie(8,9));System.out.println("""res1: Any = """ + $show(res$1));$skip(53); val res$2 = 
 
 List((1,2),(3,4)).remove( tuple => tuple._1 == 1);System.out.println("""res2: List[(Int, Int)] = """ + $show(res$2));$skip(63); val res$3 = 
        List((1,2),(3,4)).filter( tuple => tuple._1 == 1).head;System.out.println("""res3: (Int, Int) = """ + $show(res$3));$skip(21); val res$4 = 
       
 (1,2,3,4,5);System.out.println("""res4: (Int, Int, Int, Int, Int) = """ + $show(res$4));$skip(16); val res$5 = 
 1 :: List(2,3);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(247); 
 def add(xs: List[(Char, Int)], c: Char) = {
      def find = {
        val elements = xs.filter(tuple => tuple._1 == c)
        if (elements.nonEmpty) elements.head._2 else 0
      }
      (c, find + 1) :: xs.remove(tuple => tuple._1 == c)
    };System.out.println("""add: (xs: List[(Char, Int)], c: Char)List[(Char, Int)]""");$skip(34); val res$6 = 
 add(List(('a',3), ('b',7)), 'c');System.out.println("""res6: List[(Char, Int)] = """ + $show(res$6));$skip(64); val res$7 = 
      
      
  List((1,2),(3,4)).find( tuple => tuple._1 == 1);System.out.println("""res7: Option[(Int, Int)] = """ + $show(res$7));$skip(47); val res$8 = 
    List((1,2)).sortWith((a,b) => a._1 < b._1);System.out.println("""res8: List[(Int, Int)] = """ + $show(res$8))}
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