(ns GeoLocation.analyze
    (:use [clojure.java.io :only (reader)])
    (:use [GeoLocation.sql :exclude [-main]])
    (:require [clojure.string :as string])
    (:import (com.maxmind.geoip LookupService)))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))
(defn modify-vals [f m] (zipmap (keys m) (map f (vals m))))
(defn aggregate-by-key [m] 
      (apply merge-with into m))

(def lookupService (LookupService. "GeoIPISP.dat") )
(def netspeedService (LookupService. "GeoIPNetSpeedCell.dat") )

(def isps-3g (set (line-seq (reader "3g-isps.txt"))))

(defn get-isp [ip]
      (.getOrg lookupService ip))

(defn wifi-or-3g?[ip]
    (defn is-3g-isp[isp] 
      (contains? isps-3g isp))
    (if (is-3g-isp (get-isp ip)) :cellular :wifi))

(defn netspeed[ip]
    (let [connection-speed ({11 :dialup 1 :corporate 18 :cable/dsl 28 :unknown} (.getID netspeedService ip))]
       (if (= :unknown connection-speed) 
         :cellular 
         :wifi)))
;(dbg (netspeed "4.0.0.0"));Dialup
;(dbg (netspeed "3.0.0.0"));corporate
;(dbg (netspeed "12.39.246.64"));Cable/DSL
;(dbg (netspeed "32.21.248.0"));Cellular
;(dbg (get-isp "32.21.248.0"));Cellular

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
      (defn ratio [m]
            (let [total (reduce + (vals m))]
              (modify-vals #(format "%.2f" (float (/ % total))) m)))
      (let [data (time (reduce into [] (map ip-and-useragent-mobile (get-day-tables table_prefix))))
            isp-and-device (for [{:keys [ip useragent]} data] 
                                {(get-device useragent) [(predicate ip)]})] 
        (println table_prefix " --- total entries:" (count data))
        (modify-vals frequencies (aggregate-by-key isp-and-device))))

(defn csv[data] 
      (doseq [[device d] data]
             (println (string/join "," [(name device) (d :wifi) (d :cellular)]))))

(defn -main[& args]
    (let [d (get-stats-per-device netspeed "Click1_3_")]
      (println d)
      (csv d)))
    #_(println (time (get-stats-per-device wifi-or-3g? :Click26_2_12)))
