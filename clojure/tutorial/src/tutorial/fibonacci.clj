(ns tutorial.fibonacci)

(defn fib [n] (second (reduce (fn [[pre cur], _] [cur (+ pre cur)]) [0 1] (range n))))
(defn -main [args] (println (fib(read-string args))))
