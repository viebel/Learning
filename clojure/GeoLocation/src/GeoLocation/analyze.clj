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

(def isps-3g (set (line-seq (reader "3g-isps.txt"))))

(defn get-isp [ip]
      (.getOrg lookupService ip))

(defn wifi-or-3g?[ip]
    (defn is-3g-isp[isp] 
      (contains? isps-3g isp))
    (if (is-3g-isp (get-isp ip)) :cellular :wifi))

(defn get-device[useragent]
      (cond 
        (re-matches #".*iPhone.*" useragent) :iPhone
        (re-matches #".*iPad.*" useragent) :iPad
        (re-matches #".*Android.*" useragent) :Android
        :else :useragent))

(defn get-wifi-stats-per-device [filename] 
      (defn ratio [{:keys [wifi cellular]}]
            (if (every? number? [wifi cellular])
              (float (/ wifi (+ wifi cellular))) 0))
      (let [isp-and-device (for [{:keys [ip useragent]} (ip-and-useragent-mobile :Impression25_2_12)] 
                                {(get-device useragent) [(wifi-or-3g? ip)]})] 
        (modify-vals (comp ratio frequencies) (aggregate-by-key isp-and-device))))

(defn -main[& args]
    (println (time (get-wifi-stats-per-device "ips.txt"))))

