(ns tutorial.test.list-to-xml
  (:use [tutorial.list-to-xml])
  (:use [clojure.test]))

(deftest test-list-to-xml[]
         (are [a b] (= a (to-xml b))
              "<gg>bb</gg>" '(:gg "bb")
              "<gg>\n<aa>bb</aa>\n</gg>" '(:gg ((:aa "bb")))
              "<gg>\n<aa>aa</aa>\n<bb>bb</bb>\n</gg>" '(:gg ((:aa "aa")(:bb "bb")))
              "<gg>\n<aa>aa</aa>\n<bb>bb</bb>\n<cc>\n<dd>dd</dd>\n<ee>ee</ee>\n</cc>\n</gg>" '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee")))))
              ))


(deftest test-xml-attribute[]
         (are [a b] (= a (xml-attribute? b))
              false "aaa"
              true "-aa"
              false "a-a"
              false "a-"
              ))
