(ns GeoLocation.adhoc
    (:use [GeoLocation.utils])
    (:use [GeoLocation.Maxmind]))

;(dbg (netspeed "4.0.0.0"));Dialup
;(dbg (netspeed "3.0.0.0"));corporate
;(dbg (netspeed "12.39.246.64"));Cable/DSL
;(dbg (netspeed "32.21.248.0"));Cellular
;(dbg (get-isp "32.21.248.0"));Cellular

;(println (every? (partial = 11) (map netspeed (line-seq (reader "dialup.txt")))))
;(println (every? (partial = 1) (map netspeed (line-seq (reader "corporate.txt")))))
;(println (every? (partial = 18) (map netspeed (line-seq (reader "cable.txt")))))
;(println (string/join "\n" (set (map get-isp (line-seq (reader "cellular.csv"))))))

(defn -main
      ([] (println "Nothing to run"))
      ([ip]
       (dbg (.getID netspeedService ip))
       (dbg (netspeed ip))))

