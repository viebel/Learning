(ns tutorial.ant
    (:use clojure.java.shell)
    (:use clojure.java.io)
    (:require [fs])
    (:require [clojure.string :as string]))


(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn run-target [target]
      (when-let [t (:target (meta target))]
                (t)))

(defn get-target[target]
      (when-let [res (->> target (str "tutorial.ant/") symbol resolve)]
              (var-get res)))

(defmacro deftarget[name & body]
  `(def ~(vary-meta name assoc :target `(fn[] (and ~@body)))
    (fn[] (run-target (var ~name)))))

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
            (= 0 errorcode))
      (let [filename (first files)
                 [errorcode msg] (exec executable filename)]
        (recur (rest files) (conj output msg) errorcode)))))

(defn concat-files [& opts]
      (println "concat:" opts)
      (let [{:keys [srcfile destfile]} (apply hash-map opts)
                   files (line-seq (reader srcfile))
                   content (apply str (map slurp files))]
        (spit destfile content) 0 ))



(deftarget mobile
  (shexec "xmllint"
          (fs/glob "xml-ok/*.xml"))
  (concat-files :srcfile "list.txt" :destfile "all_in_one.js"))

(defn -main [target & args]
  (if-let [res (get-target target)]
          (do (if (= 0 (res))
                  (println "BUILD SUCESSFUL")
                (println "BUILD FAILED"))
              (shutdown-agents))
          (println "TARGET NOT FOUND: " target)))
