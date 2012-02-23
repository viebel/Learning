(ns tutorial.ant
    (:use clojure.java.shell)
    (:use clojure.java.io)
    (:require [fs])
    (:require [clojure.string :as string]))


(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn run-target [target]
  (-> (str "tutorial.ant/" target) symbol resolve))

(defn shexec[executable files]
  (defn print-output[output]
    (println (string/join "\n" output)))
  (println executable ":")
  (loop [files files output [] errorcode 0]
    (if (or (empty? files) (not= 0 errorcode))
        (do (print-output output)
            errorcode)
      (let [file (first files)
                 res (sh executable file)
                 errorcode (:exit res)
                 msg (str file ": " (if (= 0 errorcode) "OK" (str "FAILED\n" (:err res))))]
        (do (if (= 0 errorcode) (fs/touch (str file ".touch")))
            (recur (rest files) (conj output msg) errorcode))))))

(defn fileset
  ([dir] (map as-relative-path (file-seq (file dir))))
  ([regexp-filter dir]
   (filter (partial re-matches regexp-filter) (fileset dir))))

(defn mobile[]
  (shexec "xmllint"
          (fileset #".*\.xml$" "xml-ok")))

(defn -main [target & args]
  (let [res (run-target target)]
    (if (= 0 res)
        (println "BUILD SUCESSFUL")
      (println "BUILD FAILED")))
  (shutdown-agents))

;(-main "mobile")
