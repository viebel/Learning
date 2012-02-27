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

(defn get-stats-per-device [predicate dbtable] 
      (defn ratio [m]
            (let [total (reduce + (vals m))]
              (modify-vals #(float (/ % total)) m)))
      (let [isp-and-device (for [{:keys [ip useragent]} (ip-and-useragent-mobile dbtable)] 
                                {(get-device useragent) [(predicate ip)]})] 
        (modify-vals (comp ratio frequencies) (aggregate-by-key isp-and-device))))

(defn -main[& args]
    #_(println (time (get-netspeed-per-device :Click26_2_12)))
    (println (time (get-stats-per-device wifi-or-3g? :Click26_2_12))))
