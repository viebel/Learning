(ns tutorial.test.list-to-xml
  (:use [tutorial.list-to-xml])
  (:use [clojure.test]))

(deftest test-list-to-xml[]
         (are [a b] (= a b)
              "<gg>\n<aa>bb</aa>\n</gg>" (to-xml '(:gg ((:aa "bb"))))
              "<gg>\n<aa>aa</aa>\n<bb>bb</bb>\n</gg>" (to-xml '(:gg ((:aa "aa")(:bb "bb"))))
              "<gg>\n<aa>aa</aa>\n<bb>bb</bb>\n<cc>\n<dd>dd</dd>\n<ee>ee</ee>\n</cc>\n</gg>" (to-xml '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee"))))))
              ))
