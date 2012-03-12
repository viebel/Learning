(ns GeoLocation.Device
    (:use [GeoLocation.utils])
    (:use [GeoLocation.Maxmind])
    (:require [clojure.string :as string])
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
    (:use [clojureql.core])
    (:use [clojureql.predicates])
    (:import (com.maxmind.geoip LookupService)))

(defn ips-for-useragent [useragent tablename]
      @(-> (table db tablename)
           (select (where (and (= :country "us") 
                               (like :useragent (str "%" useragent "%")))))
           (project [:ip :userAgent])))

(defn compare-two-predicates[table_prefix p1 p2 p3]
      (let [ips (flatten (map (partial ips-for-useragent "Android") (get-day-tables table_prefix)))]
        (for [{:keys [ip]} ips]
             [ip (p1 ip) (p2 ip) (p3 ip)])))

(defn -main[table_prefix & args]
    (let [d (compare-two-predicates table_prefix netspeed wifi-or-3g? get-isp )]
      (println (string/join "\n" (remove #(= (% 1) (% 2)) d)))))
