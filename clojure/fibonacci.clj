(defn fibonacci [n] 
    (if (< n 2) 1
    (loop [i 2 pre-pre 1 pre 1]
        (def current (+ pre-pre pre))
        (if (>= i n) current
            (recur (inc i) pre current))))
)
(def nn (read-string (nth  *command-line-args* 0)))
(println (map fibonacci (range 2 nn)))
