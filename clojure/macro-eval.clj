(defn hello-world-fn [] (println "Hello World!"))
(defmacro my-eval [s] `(~(read-string s)))
(println (macroexpand '(my-eval "hello-world-fn")))
(my-eval "hello-world-fn")
