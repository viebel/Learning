(defproject checkers "1.0.0-SNAPSHOT"
            :description "FIXME: write description"
            :dependencies [[org.clojure/clojure "1.3.0"]]
            :dev-dependencies [[midje "1.4.0"]
                               [com.stuartsierra/lazytest "1.2.3"]]
            :repositories {"stuart" "http://stuartsierra.com/maven2"}
            :test-selectors {
                   :default (fn[m] (not (:perf m)))
                   :perf :perf
                   :all (constantly true)
                   })
