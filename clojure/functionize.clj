(ns functionize)

(defmacro functionize [macro]
    `(fn [& args#] (eval (cons '~macro args#))))

(def a true)
(apply (functionize and) [a true])

(defn new-class [klass & args]
        (eval `(new ~klass ~@args)))

(def kstring String)
(defn foo[] 
      (eval kstring))

(foo)
(eval kstring)
(ns cool)
(functionize/foo)

