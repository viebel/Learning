(ns tutorial.ant
    (:use clojure.java.shell)
    (:use clojure.java.io)
    (:require [clojure.string :as string]))

(defn run-target [target]
      ((-> target symbol resolve)))

(defn shexec[executable files]
      (defn print-output[output]
            (println (string/join "\n" (reverse output))))
      (println executable ":")
      (loop [files files output '() errorcode 0]
            (if (or (empty? files) (not= 0 errorcode))
              (do (print-output output)
               errorcode)
              (let [file (first files) 
                         res (sh executable file)
                         errorcode (:exit res)
                         msg (str file ": " (if (= 0 errorcode) "OK" (str "FAILED\n" (:err res))))]
                  (recur (rest files) (cons msg output) errorcode)))))

(defn fileset 
      ([dir] (map as-relative-path (file-seq (file dir))))
      ([regexp-filter dir] 
       (filter (partial re-matches regexp-filter) (fileset dir))))

(defn mobile[]
      (shexec "xmllint" 
              (fileset #".*\.xml$" "bb")))

(defn -main [target & args]
      (let [res (run-target target)]
        (if (= 0 res)
          (println "BUILD SUCESSFUL")
          (println "BUILD FAILED")))
      (shutdown-agents))

(-main "mobile")
