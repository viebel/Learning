(ns GeoLocation.iPad
    (:use [clojure.java.io :only (reader)])
    (:require [clojure.string :as string])
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
    (:use [clojureql.core])
    (:use [clojureql.predicates])
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
(defn get-device[useragent]
      (cond 
        (re-matches #".*iPhone.*" useragent) :iPhone
        (re-matches #".*iPad.*" useragent) :iPad
        (re-matches #".*Android.*" useragent) :Android
        :else :useragent))

(defn get-day-tables [prefix] 
      (for [i (range 24)]
        (keyword (str prefix i))))


(defn csv[data c1 c2] 
      (string/join "\n"
                   (cons (string/join "," (list "" (name c1) (name c2)))
                         (for [[device d] data]
                              (string/join "," [(name device) (d c1) (d c2)])))))
(def db
 {:classname   "com.mysql.jdbc.Driver"
 :subprotocol "mysql"
 :user        "sa"
 :password     "sakontera"
 :subname      "//10.1.5.20/Reports18_3"})

(defn ips-for-useragent [useragent tablename]
      @(-> (table db tablename)
           (select (where (and (= :country "us") 
                               (like :useragent (str "%" useragent "%")))))
           (project [:ip :userAgent])))

(defn compare-two-predicates[table_prefix p1 p2 p3]
      (let [ips (flatten (map (partial ips-for-useragent "iPad") (get-day-tables table_prefix)))]
        (for [{:keys [ip]} ips]
             [ip (p1 ip) (p2 ip) (p3 ip)])))

(defn -main[table_prefix & args]
    (let [d (compare-two-predicates table_prefix netspeed wifi-or-3g? get-isp )]
      (println (string/join "\n" (remove #(= (% 1) (% 2)) d)))))
