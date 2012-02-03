(ns tutorial.test.iterate-over-several-seqs
  (:use [tutorial.iterate-over-several-seqs])
  (:use [clojure.test]))

(deftest iterate-over-several-seqs
  (is (= [2/3 4/7 8/9] (map / '(2 4 8) '(3 7 9))))
  )
