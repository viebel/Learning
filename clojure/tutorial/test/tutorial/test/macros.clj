(ns tutorial.test.macros
    (:use [tutorial.macros])
    (:use [clojure.test]))

(deftest test-stringify
         (is (= "allo" (stringify allo))))
