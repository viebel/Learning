(defn create-long-document [direction]
      (str "long document " direction))

(defn create-short-document[]
      "short document")

(def creator-map {
     :english create-short-document 
     :hebrew  create-long-document 
})
(def additional-arg-map {
     :english nil
     :hebrew "rtl"
})

(defn create-documents [language n]
      (let [creator (language creator-map) arg (language additional-arg-map)]
        (if arg (repeat n (creator arg)) 
          (repeat n (creator)))))

(println (create-documents :hebrew 3))
(println (create-documents :english 3))


