(ns tutorial.construct
  (:import (java.util HashSet)
           ))

(defn construct [klass & args]
      (.newInstance
        (.getConstructor klass (into-array java.lang.Class (map type args)))
        (object-array args)))

(println (new HashSet '(8) ))
(println (construct HashSet '(8) ))
