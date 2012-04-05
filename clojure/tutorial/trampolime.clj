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



