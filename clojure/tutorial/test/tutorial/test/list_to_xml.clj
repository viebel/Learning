(ns tutorial.test.list-to-xml
  (:use [tutorial.list-to-xml])
  (:use [clojure.test]))

(defmacro deftest-several [tested-func & args]
  (let [testname (symbol (str "test-" tested-func))]
    `(deftest ~testname [] 
              (are [a b] (= a (~tested-func b))
                   ~@args))))

(deftest-several to-xml
              "<gg>bb</gg>" '(:gg bb)
              "<gg>bb</gg>" '(:gg bb)
              "<gg>\n<aa>bb</aa>\n</gg>" '(:gg ((:aa bb)))
              "<gg>\n<aa>aa</aa>\n<bb>bb</bb>\n</gg>" '(:gg ((:aa aa)(:bb bb)))
              "<gg>\n<aa>aa</aa>\n<bb>bb</bb>\n<cc>\n<dd>dd</dd>\n<ee>ee</ee>\n</cc>\n</gg>" '(:gg ((:aa aa)(:bb bb)(:cc ((:dd "dd")(:ee "ee")))))
              )

(deftest-several xml-attribute?
              false "aaa"
              true "-aa"
              false "a-a"
              false "a-"
              )

