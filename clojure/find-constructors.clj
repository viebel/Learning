(ns test
    :import (java.util HashSet)
    )
(defn find-best-constructors [klass args]
      (let [keym {:boolean Boolean/TYPE
                 :byte    Byte/TYPE
                 :double  Double/TYPE
                 :float   Float/TYPE
                 :int     Integer/TYPE
                 :long    Long/TYPE
                 :short   Short/TYPE}
                 args (->> args
                           (map #(if (class? %) % (keyword %)))
                           (map #(keym % %)))
                 prims (map keym [:boolean :byte :double :float :int :long :short])
                 boxed [Boolean Byte Double Float Integer Long Short]
                 convm (zipmap (concat prims boxed) (concat boxed prims))
                 ctors (->> (.getConstructors klass)
                            (filter #(== (count args) (count (.getParameterTypes %))))
                            (filter #(every? (fn [[pt a]]
                                                 (or (.isAssignableFrom pt a)
                                                     (if-let [pt* (convm pt)]
                                                             (.isAssignableFrom pt* a))))
                                             (zipmap (.getParameterTypes %) args))))]
        (when (seq ctors)
          (let [count-steps (fn count-steps [pt a]
                                (loop [ks #{a} cnt 0]
                                      (if (or (ks pt) (ks (convm pt)))
                                        cnt
                                        (recur (set (mapcat parents ks)) (inc cnt)))))
                            steps (map (fn [ctor]
                                           (map count-steps (.getParameterTypes ctor) args))
                                       ctors)
                            m (zipmap steps ctors)
                            min-steps (->> steps
                                           (apply min-key (partial apply max))
                                           (apply max))]
            (->> m
                 (filter (comp #{min-steps} (partial apply max) key))
                 vals)))))

