(ns GeoLocation.Maxmind
    (:use [clojure.java.io :only (reader)])
    (:import (com.maxmind.geoip LookupService)))

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
       (case connection-speed 
             :unknown :cellular
             nil :unknown
             :wifi)))


