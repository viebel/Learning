(defn- create-english-analyzer [stopwords-file-name stemmed]
  (let [stopwords (load-stopwords stopwords-file-name)]
    (if stemmed
      (proxy [Analyzer] []
        (tokenStream [field-name reader]
          (PorterStemFilter. (.tokenStream (StandardAnalyzer. Version/LUCENE_34 stopwords)
                                           field-name
                                           reader))))
      (StandardAnalyzer. Version/LUCENE_34 stopwords))))

(defn create-analyzer [language stopwords-file-name stemmed]
                 (let [filter-creator-map {:english #(PorterStemFilter. %)}
                       analyzer-creator-map {:english #(StandardAnalyzer. %1 %2)}
                       stopwords (load-stopwords stopwords-file-name)
                       Filter-creator (language filter-creator-map)
                       Analyzer (language analyzer-creator-map)]
                   (if stemmed
                     (proxy [Analyzer] []
                            (tokenStream [field-name reader]
                                         (Filter-creator (.tokenStream (Analyzer Version/LUCENE_34 stopwords)
                                                                          field-name
                                                                          reader))))
                     (Analyzer Version/LUCENE_34 stopwords))))
