(ns GeoLocation.analyze
    (:use [GeoLocation.utils])
    (:use [GeoLocation.Maxmind])
    (:use [clojure.java.io :only (reader)])
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
    (:use [clojureql.core])
    (:use [clojureql.predicates])
    (:require [clojure.string :as string]))

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
