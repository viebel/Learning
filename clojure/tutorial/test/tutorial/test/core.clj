(ns tutorial.test.core
  (:use [tutorial.core])
  (:use [clojure.test]))

(deftest first-test
  (is (= 5 (+ 2 3)))
  (is (= 5 (+ 2 4)))
  )
