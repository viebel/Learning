(ns tutorial.test.apply
    (:use [tutorial.apply])
    (:use [clojure.test])
    )

(deftest test-apply-on-vec
         (let [falsy 0 truthy 2]
         (are [a b] (= a b)
              true (apply-macro and [true])
              false (apply-macro and [true false])
              falsy (apply-macro and [true falsy])
              true (apply-macro and [true true])
              truthy (apply-macro and [true truthy])
              true (apply-macro or [true])
              )))
