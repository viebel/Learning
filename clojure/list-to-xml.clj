(defn load-list [filename]
      (read-string (slurp filename)))

(declare to-xml)
(defn simple-value-to-xml [content]
      content)
(defn content-to-xml [content nidents]
      (let [ident #(str (apply str (repeat nidents "\t")) %)]
        (if (seq? content)
          (apply str (interpose "\n" (map (comp ident #(to-xml % (inc nidents))) content)))
          (simple-value-to-xml content (inc nidents)))))

(defn xml-tag-open [tag-keyword]
      (str "<" (name tag-keyword) ">"))

(defn xml-tag-close [tag-keyword]
      (str "</" (name tag-keyword) ">"))

(defn to-xml [coll nidents] 
      (if (empty? coll) ""
        (let [tag (first coll)
                  content (second coll)]
          (str (xml-tag-open tag)
               (if (seq? content) 
                 (str "\n" (content-to-xml content nidents) "\n")
                 (simple-value-to-xml content))
               (xml-tag-close tag))
          )))

(def content-a '(:gg ((:aa "bb"))))
(def content-b '(:gg ((:aa "aa")(:bb "bb"))))
(def content-c '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee"))))))
(println (to-xml content-c 1))

;(println (to-xml (load-list "list.clj")))
