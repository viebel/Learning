



(defmacro my-and
  "Evaluates exprs one at a time, from left to right. If a form
  returns logical false (nil or false), and returns that value and
  doesn't evaluate any of the other expressions, otherwise it returns
  the value of the last expr. (and) returns true."
  {:added "1.0"}
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and# (my-and ~@next) and#))))

(defn your-and 
      ([] true)
      ([& coll] 
       (defn inner[] 
             (loop [coll coll res true]
                   (cond (empty? coll) res
                         (not res) false
                         :else (recur (rest coll) (and res (first coll))))))
       (inner)))
        
(defn best-and 
      ([] true)
      ([x & coll] 
       (if x x (apply best-and coll))))

(println (macroexpand '(my-and true true false true)))
(time (my-and true true true true true true true true true true true true true true true true))
(time (best-and true true true true true true true true true true true true true true true true))
