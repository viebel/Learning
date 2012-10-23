package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times(\"hha\")") {
    assert(times(string2Chars("hha")).toSet === List(('h',2),('a',1)).toSet)
  }

  test("times(\"hhahah\")") {
    assert(times(string2Chars("hhahah")).toSet === List(('h',4),('a',2)).toSet)
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    assert(singleton(List(Leaf('e', 1))) == true)
    assert(singleton(List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))) == false)
    assert(singleton(List()) == false)
  }

  test("combine of some leaf list (1)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine of some leaf list (2)") {
    val leaflist = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4))
    assert(combine(leaflist) === List(Leaf('x', 4), Fork(Leaf('e',2),Leaf('t',3),List('e', 't'),5)))
  }

  test("until") {
    val leaflist = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4))
    assert(until(singleton, combine, leaflist) === Fork(Leaf('x',4),Fork(Leaf('e',2),Leaf('t',3),List('e', 't'),5),List('x', 'e', 't'),9))
  }
  test("createCodeTree") {
    assert(createCodeTree("hhahbb".toList) === Fork(Leaf('h',3),Fork(Leaf('a',1),Leaf('b',2),List('a', 'b'),3),List('h', 'a', 'b'),6) )
  }

 test("decode (1)") {
    new TestTrees {
      assert(decode(t1, List(0,1)) === "ab".toList)
    }
  }

  test("decode (2)") {
    new TestTrees {
      assert(decode(t2, List(0,0,0,1,1)) === "abd".toList)
    }
  }
  test("decodedSecret") {
    assert(decodedSecret.mkString === "huffmanestcool")
  }

  test("encode (1)") {
    new TestTrees {
      assert(encode(t1)("ab".toList) === List(0,1))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a long text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ababaaaabaab".toList)) === "ababaaaabaab".toList)
    }
  }
  test("convert (1)") {
    new TestTrees {
      assert(convert(t1).toSet === List(('a',List(0)), ('b',List(1))).toSet) 
    }
  }
  test("convert (2)") {
    new TestTrees {
      assert(convert(t2).toSet === List(('a',List(0, 0)), ('b',List(0, 1)), ('d',List(1))).toSet)
    }
  }
  test("quickEncode (1)") {
    new TestTrees {
      assert(quickEncode(t1)("ab".toList) === List(0,1))
    }
  }

  test("decode and quickEncode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and quickEncode a long text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ababaaaabaab".toList)) === "ababaaaabaab".toList)
    }
  }
 
}
