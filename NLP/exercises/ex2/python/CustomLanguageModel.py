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
def histogram(values_with_count):
    hist = {}
    def inc(item):
        c = hist.get(item) or 0
        c += 1
        hist[item] = c

    for item in values_with_count:
        c = values_with_count[item]
        inc(c)
    return hist
    
class CustomLanguageModel:
  def __init__(self, corpus):
    self.ngram_depth = 3
    self.ngrams = { i+1 : {} for i in range(self.ngram_depth)}
    self.nwords = 0
    self.train(corpus)
    hist = histogram(self.ngrams[1])
    self.unk_probability = float(hist[1])/self.nwords
    self.unk_probability = 0.5/self.nwords
    print('nwords: ' + str(self.nwords) + ' unk_probability: ' + str(self.unk_probability))

  def get_ngram_container(self, ngram):
    return self.ngrams[len(ngram)]

  def num_of_occurences(self, ngram):
    return self.get_ngram_container(ngram).get(str(ngram)) or 0

  def train(self, corpus):
    def inc(item):
        dic = self.get_ngram_container(item)
        c = dic.get(str(item)) or 0
        dic[str(item)] = c + 1
        return c + 1

    for sentence in corpus.corpus: 
        ngrams = [[] for i in range(self.ngram_depth)]
        for datum in sentence.data: 
            self.nwords += 1
            i = 0
            while i < len(ngrams):
              ngram = ngrams[i]
              push_to_cyclic_list(i+1, ngram, datum.word)
              if(len(ngram) == i+1):
                inc(ngram)
              i += 1

  def ngram_score_raw(self, ngram):
      ngram_count = self.num_of_occurences(ngram)
      if len(ngram) == 1:
          if ngram_count:
              return float(ngram_count)/self.nwords
          return self.unk_probability
      if ngram_count != 0:
          return float(ngram_count)/self.num_of_occurences(ngram[:-1])
      return 0.4*self.ngram_score_raw(ngram[1:])

  def ngram_score(self, ngram):
      return math.log(self.ngram_score_raw(ngram))

  def score(self, sentence):
    score = 0.0
    ngram = []
    for token in sentence:
      push_to_cyclic_list(self.ngram_depth, ngram, token)
      s = self.ngram_score(ngram)
      score += s
    return score
