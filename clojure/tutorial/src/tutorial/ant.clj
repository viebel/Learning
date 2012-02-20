(ns tutorial.ant
    (:use clojure.java.shell)
    (:use clojure.java.io)
    (:require [clojure.string :as string]))

(def targets (ref {}))

(defmacro deftarget [name & body]
  (let [name (str name)] 
  `(dosync (ref-set targets (assoc @targets ~name (fn[] ~@body))))))

(defn run-target [target]
  ((@targets target)))

(defn shexec[executable files]
      (defn print-output[output]
            (println (string/join "\n" (reverse output))))
      (loop [files files output []]
            (if (empty? files) 
              (do (print-output output)
               0)
              (let [file (first files) 
                         res (sh executable file)
                         errorcode (:exit res)]
                (if (= 0 errorcode)
                  (recur (rest files) (cons (str file ": OK") output))
                  (do (print-output (cons (str file ": FAILED " (:err res)) output))
                   errorcode))))))


(defn filenames-of-dir 
      ([dir] (map as-relative-path (file-seq (file dir))))
      ([regexp-filter dir] 
       (filter (partial re-matches regexp-filter) (filenames-of-dir dir))))

(deftarget mobile
      (shexec "xmllint" (filenames-of-dir #".*\.xml$" "aa")))


(defn -main [target & args]
      (let [res (run-target target)]
        (if (= 0 res)
          (println "BUILD SUCESSFUL")
          (println "BUILD FAILED"))))

(run-target "mobile")
(shutdown-agents)
