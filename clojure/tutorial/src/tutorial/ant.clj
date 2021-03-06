(ns tutorial.ant
    (:use clojure.java.shell)
    (:use clojure.java.io)
    (:require [fs])
    (:require [clojure.string :as string]))


(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))
(def my-ns *ns*)

(defn run-target [target]
  (when-let [t (:target (meta target))]
            (t)))

(defn get-target[target]
  (when-let [t (ns-resolve my-ns (symbol target))]
            (var-get t)))

(defmacro deftarget[name & body]
  `(def ~(with-meta name {:target `(fn[] (and ~@body))})
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

(defn read-file-list [srcfile]
  (defn remove-trailing-spaces[s] (string/replace s #"\s+$" ""))
  (->> srcfile
       reader
       line-seq
       (map remove-trailing-spaces)
       (remove empty?)))

(defn concat-files [& opts]
  (println "concat:")
  (let [{:keys [srcfile destfile header footer]} (apply hash-map opts)
               content (string/join (map slurp (read-file-list srcfile)))]
    (spit destfile (str header content footer)) true))

(load-file "build.properties.clj")
(load-file (str "build.properties." (-> "os.name" System/getProperty .toLowerCase) ".clj"))
(load-file "build.clj")

(defn -main [target & args]
  (try
   (if-let [res (get-target target)]
           (do (if (res)
                   (println "BUILD SUCESSFUL")
                   (println "BUILD FAILED"))
               (shutdown-agents))
           (println "TARGET NOT FOUND: " target))
   (catch Exception e (println e))))
