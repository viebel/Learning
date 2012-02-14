(ns hello)
(defmacro my-eval [x] `~(eval x))
(defn hello[] "Hello")
(defn run-stuff []
  (println (hello))
  (println (my-eval "(hello)"))
  (def x "(hello)")
  (println (my-eval x)))
(ns main)
(try (hello/run-stuff)
  (catch Exception e (println e)))
