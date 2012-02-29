(ns GeoLocation.sql
    (:refer-clojure :exclude [distinct conj! case compile drop take sort disj!])
   (:use [clojureql.core])
   (:use [clojureql.predicates]))

(def db
 {:classname   "com.mysql.jdbc.Driver"
 :subprotocol "mysql"
 :user        "sa"
 :password     "sakontera"
 :subname      "//10.1.5.20/Reports18_3"})

(defn -main[& args] 
 (let [q  (-> 
              (union (table db :Click27_2_12) (table db :Click27_2_13) :all)
              (take 5)
               (aggregate [[:count/* :as :cnt]])
               )]
   (println q)
   (println @q)))

(defn ip-and-useragent-mobile [tablename]
      @(-> (table db tablename)
           (select (where (and (= :country "us") 
                               (or (like :useragent "%Android%" )
                                   (like :useragent "%iPad%")
                                   (like :userAgent "%iPhone%")))))
           (project [:ip :userAgent])))



