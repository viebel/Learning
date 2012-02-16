
(defn get-func [a b] ((comp resolve symbol str) a b))
(println ((get-func "ma" "p") inc '(1 2 3)))

(defn get-func-by-id [id the-map] 
      (id the-map))

(println ((get-func-by-id :map {:map map}) inc '(1 2 3)))
(println ((get-func-by-id :string {:string #(String. %1)}) "333"))

(defn list-with-no-nil [& args] 
      (filter (complement nil?) args))
(def c nil)
(println (list-with-no-nil 1 2 c 8 9))
