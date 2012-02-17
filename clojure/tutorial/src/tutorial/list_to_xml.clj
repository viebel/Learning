(ns tutorial.list-to-xml
    (:require [clojure.string :as string]))

(defn load-list [filename]
      (read-string (slurp filename)))

(declare to-xml)

(defn content-to-xml [content]
        (if (coll? content)
          (str "\n" (string/join "\n" (map to-xml content)) "\n")
          content))

(defn xml-tag-open [tag-keyword attributes]
      (defn- rename-key[[key value]] 
             (list (string/replace (name key) #"^-" "") value))
      (defn- join-me[[key value]]
             (str key "=" \" value \"))
      (str "<" (name tag-keyword) (when attributes " ")
           (string/join " " (map (comp join-me rename-key) attributes))
           ">"))

(defn xml-tag-close [tag-keyword]
      (str "</" (name tag-keyword) ">"))

(defn xml-attribute? [value]
      ((complement nil?) (re-matches #"^-.*" value)))

(defn separate-attributes-and-children [content]
    (let [{attributes true children false} (if (coll? content) 
                                               (group-by (comp xml-attribute? name first) content)
                                               {false content})]
      {:attributes attributes :children children}))

(defn tag-and-content-to-xml [tag content]
      (let [{:keys [attributes children]} (separate-attributes-and-children content)]
        (str (xml-tag-open tag attributes)
             (content-to-xml children) 
             (xml-tag-close tag))))

(defn to-xml [coll] 
      (if (empty? coll) ""
        (tag-and-content-to-xml (first coll) (second coll))))

(def content-0 '(:gg "bb"))
(def content-a '(:gg ((:aa "bb"))))
(def content-b '(:gg ((:aa "aa")(:bb "bb"))))
(def content-c '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee"))))))
;(println (to-xml content-a))

;(println (to-xml (load-list "list.clj")))
