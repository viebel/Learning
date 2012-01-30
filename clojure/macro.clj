(defmacro configure
 [object options]
 `(doto ~object
     ~@(map (fn [[property value]]
             (let [property (name property)
              setter   (str ".set"
                  (.toUpperCase (subs property 0 1))
                  (subs property 1))]
              `(~(symbol setter) ~value)))
         options)))


(println (macroexpand-1 '(configure (MyClass.) {:username "fred" :password "wilma"})))

(defmacro configure-simple
 [object property]
 `(doto ~object
     ~(let [property (name property)
              setter   (str ".set" property)]
              `(~(symbol setter)))))


(println (macroexpand-1 '(configure-simple (MyClass.) :username)))
(defmacro call-me [f b arg]
        `(~(symbol (str f b)) ~arg))

(println (macroexpand-1 '(call-me "print" "ln" 1)))
(call-me "print" "ln" 5)

(defmacro call-me [f  b arg]
        `(~(symbol (str f b)) ~arg))

(println (macroexpand-1 '(call-me print ln 1)))
(call-me print ln 1)

#_(defn- create-english-analyzer [stopwords-file-name stemmed]
  (let [stopwords (load-stopwords stopwords-file-name)]
    (if stemmed
      (proxy [Analyzer] []
        (tokenStream [field-name reader]
          (PorterStemFilter. (.tokenStream (StandardAnalyzer. Version/LUCENE_34 stopwords)
                                           field-name
                                           reader))))
      (StandardAnalyzer. Version/LUCENE_34 stopwords))))


(println "*******************")
(defmacro create-analyzer [stopwords-file-name stemmed language]
   (let [filter-prefix-map {:French :Snowball} 
         Analyzer* (symbol (str language "Analyzer."))
         Filter* (symbol (str (language filter-prefix-map) "Filter."))
         stemmer-map {:French '(FrenchStemmer.)}
         Stemmer* (language stemmer-map "")
         ]
  `(let [stopwords (load-stopwords stopwords-file-name)]
    ~Analyzer*
    (if stemmed
      (proxy [Analyzer] []
        (tokenStream [field-name reader]
          (~Filter* (.tokenStream (~Analyzer* Version/LUCENE_34 stopwords)
                                           field-name
                                           reader)
                     ~Stemmer*)))
      (~Analyzer* Version/LUCENE_34 stopwords)))))

(println (macroexpand '(create-analyzer :sfn true :French)))
(println (macroexpand '(create-analyzer :sfn true :English)))

