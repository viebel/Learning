(first (map println '(1 2 3 4) ))
   (defn my-func [x] (println x))
    (doseq [x '(1 2 3) ]
                   (my-func x))

     (println ((comp doall map) my-func '(1 2 3)))
