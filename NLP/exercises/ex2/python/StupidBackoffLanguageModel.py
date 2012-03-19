import math

def push_to_cyclic_list(n, l,a):
    length = len(l)
    if(length < n):
        l.append(a)
        return l
    i = 0
    while i < length - 1:
        l[i] = l[i+1]
        i += 1
    l[-1] = a
    return l
    
class StupidBackoffLanguageModel:
  def __init__(self, corpus):
    self.trigrams = {}
    self.bigrams = {}
    self.unigrams = {}
    self.ngrams = {
            1: self.unigrams,
            2: self.bigrams,
            3: self.trigrams
            }
    self.nwords = 0
    self.train(corpus)
    print('nwords: ' + str(self.nwords))

  def num_of_occurences(self, ngram):
      return self.ngrams[len(ngram)].get(str(ngram)) or 0

  def train(self, corpus):
    def inc(dic, item):
        c = dic.get(str(item)) or 0
        dic[str(item)] = c + 1
        return c + 1

    for sentence in corpus.corpus: 
      unigram = []
      bigram = []
      trigram = []
      for datum in sentence.data: 
        self.nwords += 1
        push_to_cyclic_list(1, unigram, datum.word)
        inc(self.unigrams, unigram)
        push_to_cyclic_list(2, bigram, datum.word)
        if(len(bigram) == 2):
            inc(self.bigrams, bigram)
        push_to_cyclic_list(3, trigram, datum.word)
        if(len(trigram) == 3):
            inc(self.trigrams, trigram)

  def ngram_score_raw(self, ngram):
      ngram_count = self.num_of_occurences(ngram)
      if len(ngram) == 1:
          return float(ngram_count + 1)/self.nwords
      if ngram_count != 0:
          return float(ngram_count)/self.num_of_occurences(ngram[:-1])
      return 0.4*self.ngram_score_raw(ngram[1:])

  def ngram_score(self, ngram):
      return math.log(self.ngram_score_raw(ngram))

  def score(self, sentence):
    score = 0.0
    trigram = []
    for token in sentence:
      push_to_cyclic_list(3, trigram, token)
      s = self.ngram_score(trigram)
      score += s
    return score
