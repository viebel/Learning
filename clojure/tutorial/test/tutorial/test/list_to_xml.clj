(ns tutorial.test.list-to-xml
  (:use [tutorial.list-to-xml])
  (:use [clojure.test]))

(defmacro deftest-several [testname tested-func & args]
  (let [testname (symbol (str "test-" tested-func))]
    `(deftest ~testname [] 
              (are [a b] (= a (~tested-func b))
                   ~@args))))

(deftest-several test-to-xml to-xml
              "<gg>bb</gg>" '(:gg bb)
              "<gg>bb</gg>" '(:gg bb)
              "<gg>\n <aa>bb</aa>\n</gg>" '(:gg ((:aa bb)))
              "<gg aa=\"bb\"></gg>" '(:gg ((:-aa bb)))
              "<gg aa=\"bb\">\n <cc>dd</cc>\n</gg>" '(:gg ((:-aa bb)(:cc dd)))
              "<gg>\n <aa>aa</aa>\n <bb>bb</bb>\n</gg>" '(:gg ((:aa aa)(:bb bb)))
              "<gg>\n <aa>aa</aa>\n <bb>bb</bb>\n <cc>\n  <dd>dd</dd>\n  <ee>ee</ee>\n </cc>\n</gg>" '(:gg ((:aa aa)(:bb bb)(:cc ((:dd "dd")(:ee "ee")))))
              )

(deftest-several test-xml-attribute? xml-attribute?
              false "aaa"
              true "-aa"
              false "a-a"
              false "a-"
              )

(deftest test-xml-tag-open
         (are [a b c] (= a (xml-tag-open b c))
              "<gg aa=\"bb\">" :gg '((:-aa bb))
              "<gg a-aa=\"bb\">" :gg '((:a-aa bb))
              "<gg aa=\"bb\" cc=\"dd\">" :gg '((:-aa bb)(:-cc dd))
              ))

(defn test-ns-hook []
      (test-xml-attribute?)
      (test-xml-tag-open)
      (with-redefs [tutorial.list-to-xml/identstr " "] 
         (test-to-xml)))
