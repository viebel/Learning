(ns aaa)
(defmacro simple [a] 
  `~a)
(println (simple "aaa"))
(defn foo [] 
    (println (simple "bbb")))

(ns bbb)
(aaa/foo)
