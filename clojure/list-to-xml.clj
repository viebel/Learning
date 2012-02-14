(defn load-list [filename]
      (read-string (slurp filename)))

(declare to-xml)
(defn simple-value-to-xml [content]
      content)
(defn content-to-xml [content]
      (if (seq? content)
        (apply str (map to-xml content))
        (simple-value-to-xml content)))

(defn xml-tag-open [tag-keyword]
      (str "<" (name tag-keyword) ">"))

(defn xml-tag-close [tag-keyword]
      (str "</" (name tag-keyword) ">"))

(defn to-xml [coll] 
      (if (empty? coll) ""
        (let [tag (first coll)
                  content (second coll)]
          (str (xml-tag-open tag)
               (if (seq? content) 
                 (to-xml content)
                 (simple-value-to-xml content))
               (xml-tag-close tag))
          )))

(def content '(:aa "bb"))
(println (to-xml content))

(def content-complex '(:bb (:aa "aa")(:cc "cc")))
(println (to-xml content-complex))
(println (to-xml (load-list "list.clj")))
