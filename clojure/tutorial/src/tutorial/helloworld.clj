(ns tutorial.helloworld
      (:gen-class
            :main -main))

(defn hello-world-fn []
        (println "Hello World"))

(defmacro my-eval [s] `~(read-string s))
(defn -main [& args]
      (println (macroexpand '(my-eval "(hello-world-fn)")))
        (my-eval "(hello-world-fn)")
        #_(eval (read-string "(hello-world-fn)"))
)

