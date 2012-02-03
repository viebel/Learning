(ns tutorial.test.fibonacci
    (:use [tutorial.fibonacci])
    (:use [clojure.test]))

(deftest fibonacci
         (are [a b] (= a b)
              1 (fib 0)
              1 (fib 1)
              2 (fib 2)
              3 (fib 3)
              5 (fib 4)
              8 (fib 5)))
