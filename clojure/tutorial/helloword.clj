(ns helloworld
      (:gen-class
            :main -main))

(defn hello-world-fn []
        (println "Hello World"))

(defn -main [& args]
      (println 4444)
        (eval (read-string "(hello-world-fn)")))

