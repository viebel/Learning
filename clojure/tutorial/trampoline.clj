(def factorial 
  (memoize (fn [n acc] 
             (println "factorial" n acc)
             (if (> n 1) 
               #(factorial (dec n) (* acc (dec n))) 
               acc))))

(println (trampoline (factorial 4 4)))
(println (trampoline (factorial 4 4)))
(println (trampoline (factorial 3 3)))


(def factorial 
  (memoize (fn [n acc] 
             (println "factorial" n acc)
             (if (> n 1) 
               #(factorial (dec n) (* acc (dec n))) 
               acc))))

(println (trampoline (factorial 4 4)))
(println (trampoline (factorial 4 4)))
(println (trampoline (factorial 3 3)))





(defn funb [n m]
  (letfn [(funa [n m]
          (if (= n 0)
            m
            #(funb (dec n) (inc m))))]
    (if (= n 0)
      m
      #(funa (dec n) (inc m)))))

(println "trampoline funb")
(println (trampoline funb 5 0))
      
