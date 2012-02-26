(ns GeoLocation.sql
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
   (:use [clojureql.core])
   (:use [clojureql.predicates]))

(def db
 {:classname   "com.mysql.jdbc.Driver"
 :subprotocol "mysql"
 :user        "sa"
 :password     "sakontera"
 :subname      "//10.1.5.20 /Reports18_3"})

(defn -main[& args] 
 (println @(-> (table db :Click21_2_12)(take 5) (select (where (like :userAgent "%iPhone%")))(project [:ip :userAgent]))))

    (defn ip-and-useragent-mobile [tablename]
          @(-> (table db tablename)
               (select (where (or (like :useragent "%Android%" )
                                  (like :useragent "%iPad%")
                                  (like :userAgent "%iPhone%"))))
               (project [:ip :userAgent])))



