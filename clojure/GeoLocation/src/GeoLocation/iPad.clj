(ns GeoLocation.iPad
    (:use [GeoLocation.Maxmind])
    (:require [clojure.string :as string])
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
    (:use [clojureql.core])
    (:use [clojureql.predicates])
    (:import (com.maxmind.geoip LookupService)))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))
(defn modify-vals [f m] (zipmap (keys m) (map f (vals m))))
(defn aggregate-by-key [m] 
      (apply merge-with into m))

(defn get-device[useragent]
      (cond 
        (re-matches #".*iPhone.*" useragent) :iPhone
        (re-matches #".*iPad.*" useragent) :iPad
        (re-matches #".*Android.*" useragent) :Android
        :else :useragent))

(defn get-day-tables [prefix] 
      (for [i (range 3 10)]
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
      (let [ips (flatten (map (partial ips-for-useragent "Android") (get-day-tables table_prefix)))]
        (for [{:keys [ip]} ips]
             [ip (p1 ip) (p2 ip) (p3 ip)])))

(defn -main[table_prefix & args]
    (let [d (compare-two-predicates table_prefix netspeed wifi-or-3g? get-isp )]
      (println (string/join "\n" (remove #(= (% 1) (% 2)) d)))))
