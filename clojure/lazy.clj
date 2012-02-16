(def ll '(3 4 5 (println "allo") (+ 5 6)))
(println (every? (complement nil?) ll))
(doall ll)


