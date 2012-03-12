(ns GeoLocation.analyze
    (:use [GeoLocation.Maxmind])
    (:use [clojure.java.io :only (reader)])
    (:use [GeoLocation.sql :exclude [-main]])
    (:require [clojure.string :as string]))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))
(defn modify-vals [f m] (zipmap (keys m) (map f (vals m))))
(defn aggregate-by-key [m] 
      (apply merge-with into m))
;(dbg (netspeed "4.0.0.0"));Dialup
;(dbg (netspeed "3.0.0.0"));corporate
;(dbg (netspeed "12.39.246.64"));Cable/DSL
;(dbg (netspeed "32.21.248.0"));Cellular
;(dbg (get-isp "32.21.248.0"));Cellular
(dbg (.getID netspeedService "208.54.32.145'"));Cellular
(dbg (netspeed "208.54.32.145'"));Cellular

;(println (every? (partial = 11) (map netspeed (line-seq (reader "dialup.txt")))))
;(println (every? (partial = 1) (map netspeed (line-seq (reader "corporate.txt")))))
;(println (every? (partial = 18) (map netspeed (line-seq (reader "cable.txt")))))
;(println (string/join "\n" (set (map get-isp (line-seq (reader "cellular.csv"))))))

(defn get-device[useragent]
      (cond 
        (re-matches #".*iPhone.*" useragent) :iPhone
        (re-matches #".*iPad.*" useragent) :iPad
        (re-matches #".*Android.*" useragent) :Android
        :else :useragent))

(defn get-day-tables [prefix] 
      (for [i (range 24)]
        (keyword (str prefix i))))

(defn get-stats-per-device [predicate table_prefix] 
      (println "gathering data for:" table_prefix)
      (defn one-hour-data[table] 
            (let [data (ip-and-useragent-mobile table)
                       isp-and-device (for [{:keys [ip useragent]} data] 
                                           {(get-device useragent) [(predicate ip)]})] 
              (modify-vals frequencies (aggregate-by-key isp-and-device))))

      (reduce (partial merge-with (partial merge-with +))
              (map one-hour-data (get-day-tables table_prefix))))

(defn csv[data c1 c2] 
      (string/join "\n"
                   (cons (string/join "," (list "" (name c1) (name c2)))
                         (for [[device d] data]
                              (string/join "," [(name device) (d c1) (d c2)])))))

(defn -main
      ([] (println "Nothing to run"))
      ([table_prefix & args]
       (let [d (get-stats-per-device netspeed table_prefix)]
         (println d)
         (println (csv d :wifi :cellular))))
      #_(println (time (get-stats-per-device wifi-or-3g? :Click26_2_12))))
