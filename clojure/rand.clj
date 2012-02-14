(defn rand-char-seq [size] 
      (loop [s '() n size]
            (if (= n 0) s
              (recur (cons (char (+ 65 (rand-int 26))) s) (dec n)))))

(println (apply str (rand-char-seq 7)))


