(ns tutorial.list-to-xml
    (:require [clojure.string :as string]))

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
      [attributes children]))

(def identstr " ")
(defn to-xml
      ([[tag content] ident-level]
       (let [[attributes children] (separate-attributes-and-children content)
                    my-ident (apply str "\n" (repeat ident-level identstr))
                    children-ident (apply str "\n" (repeat (inc ident-level) identstr))]
         (str (xml-tag-open tag attributes)
              (if (coll? children)
                (str children-ident (string/join children-ident (map #(to-xml % (inc ident-level)) children)) my-ident)
                children)
              (xml-tag-close tag))))
      ([coll] (to-xml coll 0)))

(def content-0 '(:gg "bb"))
(def content-a '(:gg ((:aa "bb"))))
(def content-b '(:gg ((:aa "aa")(:bb "bb"))))
(def content-c '(:gg ((:aa "aa")(:bb "bb")(:cc ((:dd "dd")(:ee "ee"))))))
;(println (to-xml content-a))
;(println (to-xml content-b))
;(println (to-xml content-c))

(defn load-list [filename]
      (read-string (slurp filename)))
;(println (to-xml (load-list "list.clj")))
