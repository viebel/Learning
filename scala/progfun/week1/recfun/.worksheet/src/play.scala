


object play {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(70); 
  lazy val inf: Stream[Int] = 1 #:: (inf map (2 * _));System.out.println("""inf  : Stream[Int] = <lazy>""");$skip(22); val res$0 = 
  inf.take(10).toList;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(87); val res$1 = 
  try {
    Vector(1, 2)(8)
  } catch {
    case e: IndexOutOfBoundsException => 4
  };System.out.println("""res1: Int = """ + $show(res$1));$skip(56); 

  def create: Int => Boolean = {
    (x =>  false)
  };System.out.println("""create: => Int => Boolean""");$skip(15); val res$2 = 
 

 Set(1) + 1;System.out.println("""res2: scala.collection.immutable.Set[Int] = """ + $show(res$2));$skip(29); val res$3 = 
 Stream.cons(1, Stream(1,2));System.out.println("""res3: Stream.Cons[Int] = """ + $show(res$3));$skip(19); val res$4 = 
 1 #:: Stream(1,2);System.out.println("""res4: scala.collection.immutable.Stream[Int] = """ + $show(res$4));$skip(30); val res$5 = 
  Set(moo(1)) contains moo(1);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(42); val res$6 = 
    Set(boo(moo(1))) contains boo(moo(1));System.out.println("""res6: Boolean = """ + $show(res$6));$skip(20); val res$7 = 
 
 Stream().isEmpty;System.out.println("""res7: Boolean = """ + $show(res$7));$skip(42); val res$8 = 
 (   Stream(1,2) #::: Stream(3)).apply(2);System.out.println("""res8: Int = """ + $show(res$8))}
}


case class moo(x: Int);
case class boo(m: moo);
  