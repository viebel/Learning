(ns tutorial.list-to-xml)

(defn load-list [filename]
      (read-string (slurp filename)))

(declare to-xml)
(defn simple-value-to-xml [content]
      content)
(defn interpose-envelop-and-stringify [coll sep]
      (str sep 
           (apply str (interpose sep coll)) 
           sep))
(defn content-to-xml [content]
        (if (seq? content)
          (interpose-envelop-and-stringify (map to-xml content) "\n")
          (simple-value-to-xml content )))

(defn xml-tag-open [tag-keyword attributes]
      (str "<" (name tag-keyword) ">"))

(defn xml-tag-close [tag-keyword]
      (str "</" (name tag-keyword) ">"))

(defn xml-attribute? [value]
      ((complement nil?) (re-matches #"^-.*" value)))

(defn tag-and-content-to-xml [tag content]
      (let [{attributes true children false} (if (seq? content) 
                                               (group-by (comp xml-attribute? name first) content)
                                               {false content})]
      (str (xml-tag-open tag attributes)
           (content-to-xml content) 
           (xml-tag-close tag))))

(defn to-xml [coll] 
      (if (empty? coll) ""
        (tag-and-content-to-xml (first coll) (second coll))))

(def content-0 '(:gg "bb"))
(def content-a '(:gg ((:aa "bb"))))
(def content-b '(:gg ((:aa "aa")(:bb "bb"))))
(def content-c '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee"))))))
(println (to-xml content-a))

;(println (to-xml (load-list "list.clj")))
