


(defn div [n] (every? #(= 0 (mod n %)) (range 1 21)))
(println (take 1 (filter div (range 20 1e11 20))))

