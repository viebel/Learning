(ns tutorial.list-to-xml)

(defn load-list [filename]
      (read-string (slurp filename)))

(declare to-xml)
(defn simple-value-to-xml [content]
      content)
(defn content-to-xml [content]
        (if (seq? content)
          (str "\n" 
              (apply str (interpose "\n" (map to-xml content)))
              "\n")
          (simple-value-to-xml content )))

(defn xml-tag-open [tag-keyword]
      (str "<" (name tag-keyword) ">"))

(defn xml-tag-close [tag-keyword]
      (str "</" (name tag-keyword) ">"))

(defn to-xml [coll] 
      (if (empty? coll) ""
        (let [tag (first coll)
                  content (second coll)]
          (str (xml-tag-open tag)
               (content-to-xml content) 
               (xml-tag-close tag))
          )))

(def content-a '(:gg ((:aa "bb"))))
(def content-b '(:gg ((:aa "aa")(:bb "bb"))))
(def content-c '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee"))))))
(println (to-xml content-b))

;(println (to-xml (load-list "list.clj")))
