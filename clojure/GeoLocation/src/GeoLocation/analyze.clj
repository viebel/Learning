(ns GeoLocation.analyze
    (:use [GeoLocation.utils])
    (:use [GeoLocation.Maxmind])
    (:use [clojure.java.io :only (reader)])
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
    (:use [clojureql.core])
    (:use [clojureql.predicates])
    (:require [clojure.string :as string]))

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

(defn ip-and-useragent-mobile [tablename]
      @(-> (table db tablename)
           (select (where (and (= :country "us") 
                               (or (like :useragent "%Android%" )
                                   (like :useragent "%iPad%")
                                   (like :userAgent "%iPhone%")))))
           (project [:ip :userAgent])))

(defn get-stats-per-device [predicate table_prefix] 
      (println "gathering data for:" table_prefix)
      (defn one-hour-data[table] 
            (let [data (ip-and-useragent-mobile table)
                       isp-and-device (for [{:keys [ip useragent]} data] 
                                           {(get-device useragent) [(predicate ip)]})] 
              (modify-vals frequencies (aggregate-by-key isp-and-device))))

      (reduce (partial merge-with (partial merge-with +))
              (map one-hour-data (get-day-tables table_prefix))))

(defn -main
      ([] (println "Nothing to run"))
      ([table_prefix & args]
       (let [d (get-stats-per-device netspeed table_prefix)]
         (println d)
         (println (csv d :wifi :cellular))))
      #_(println (time (get-stats-per-device wifi-or-3g? :Click26_2_12))))
