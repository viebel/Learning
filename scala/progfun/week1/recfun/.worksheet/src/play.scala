

object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(69); 
  lazy val inf: Stream[Int] = 1 #:: (inf map (2 * _));System.out.println("""inf  : Stream[Int] = <lazy>""");$skip(22); val res$0 = 
  inf.take(10).toList;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(87); val res$1 = 
  try {
    Vector(1, 2)(8)
  } catch {
    case e: IndexOutOfBoundsException => 4
  };System.out.println("""res1: Int = """ + $show(res$1));$skip(56); 

  def create: Int => Boolean = {
    (x =>  false)
  };System.out.println("""create: => Int => Boolean""");$skip(41); val res$2 = 
 
 moo(4) match {
  case moo(y) => 8
  };System.out.println("""res2: Int = """ + $show(res$2));$skip(22); val res$3 = 
  
  moo(8) == moo(9);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(19); val res$4 = 
  moo(8) == moo(8);System.out.println("""res4: Boolean = """ + $show(res$4))}

}

case class moo(x: Int) {
}
  