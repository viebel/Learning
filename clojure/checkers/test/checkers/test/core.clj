(ns checkers.test.core
  (:use [checkers.core])
  (:use [clojure.test])
  (:use [midje.sweet]))

(fact "addition has a unit element"
        (+ 12345 0) => 12345)
(fact  (+ 2 3) => truthy)
(future-fact  "false is not truthy" false =not=> truthy)

(defn go-advah [] 5)
(defn go-nehoray[]
  :whatever)

(fact "mocks"
      (go-nehoray) => :whatever
      (provided (go-advah) => :whatever :times 0))


(fact "me"
      (println "me and u")
      0 => 0)


(deftest ^{:perf true} perf
         (is (= 5 4)))

(deftest  bug
         (is (= 5 4)))
