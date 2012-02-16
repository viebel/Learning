(ns tutorial.test.constructor
    (:use [tutorial.constructor])
    (:use [clojure.test])
    (:import (java.util HashSet))
    )

(defn set<= [a b] (every? (partial contains? b) a))
(defn set= [a b] (and (set<= a b) (set<= b a)))
(deftest test-construct
         (let [hashSet-klass HashSet]
             (is (= (HashSet. [1 2 3]) (construct hashSet-klass [1 2 3])) "when class is stored on variable"))
             (is (= (HashSet. [1 2 3]) (apply construct HashSet (list [1 2 3]))) "when using apply"))

#_(deftest test-find-best-constructors
  (is (= nil (find-best-constructors java.util.HashSet [:int :float])) "constructors of HashSte [:int :float]")

  (is (= (HashSet. [1 2 3]) (construct HashSet [1 2 3])) "construct and new leads to same result on vector of integers")
  )
