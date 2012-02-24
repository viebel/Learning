(ns tutorial.ant
    (:use clojure.java.shell)
    (:use clojure.java.io)
    (:require [fs])
    (:require [clojure.string :as string]))


(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn run-target [target]
  ((-> (str "tutorial.ant/" target) symbol resolve)))

(defn exec-and-touch[executable src target]
      (let [res (sh executable src)]
        (when (= 0 (:exit res)) (fs/touch target))
        res))

(defn shexec[executable files]
  (defn print-output[output]
    (println (string/join "\n" output)))
  (defn exec[executable filename]
    (defn newer? [a b] 
          (apply > (map fs/mtime [a b])))
    (let [target (str filename ".touch")]
      (if (newer? target filename)
          [0 ""]
        (let [{:keys [exit err]} (exec-and-touch executable filename (str filename ".touch"))]
          [exit (str filename ": " (if (= 0 exit) "OK" (str "FAILED\n" err)))]))))
  
  (println executable ":")
  (loop [files files output [] errorcode 0]
    (if (or (empty? files) (not= 0 errorcode))
        (do (print-output output)
            errorcode)
      (let [filename (first files)
                 [errorcode msg] (exec executable filename)]
        (recur (rest files) (conj output msg) errorcode)))))

(defn mobile[]
  (shexec "xmllint"
          (fs/glob "xml-ok/*.xml"))
  (shexec "xmllint"
          (fs/glob "xml-bad/*.xml")))

(defn -main [target & args]
  (let [res (run-target target)]
    (if (= 0 res)
        (println "BUILD SUCESSFUL")
      (println (str "BUILD FAILED: " res))))
  (shutdown-agents))

;(-main "mobile")
