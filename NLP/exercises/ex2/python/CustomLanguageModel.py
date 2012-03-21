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

def create_coeffs(n,k):
    def inner(remainder, res, i):
        if(i == 1):
            res[i] = remainder
            return res
        res[i] = k*remainder
        return inner((1-k)*remainder, res, i - 1)
    return inner(1, {}, n)

   
class CustomLanguageModel:
  def __init__(self, corpus):
    self.ngram_depth = 3
    self.ngrams = { i+1 : {} for i in range(self.ngram_depth)}
    self.nwords = 0
    self.train(corpus)
    hist = histogram(self.ngrams[1])
    self.unk_probability = float(hist[1])/self.nwords
    self.unk_probability = 0.5/self.nwords
    self.coeffs = create_coeffs(self.ngram_depth, 0.6)
    print('nwords: ' + str(self.nwords) + ' unk_probability: ' + str(self.unk_probability))

  def get_ngram_container(self, ngram):
    return self.ngrams[len(ngram)]

  def num_of_occurences(self, ngram):
    if len(ngram) == 0:
        return self.nwords
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

  def ngram_prob(self, ngram, ngram_count):
      if ngram_count == 0:
          return 0
      return float(ngram_count)/self.num_of_occurences(ngram[:-1])

  def ngram_score_stupid_backoff(self, ngram):
      ngram_count = self.num_of_occurences(ngram)
      if ngram_count != 0:
          return self.ngram_prob(ngram, ngram_count)
      if len(ngram) == 1:
          return self.unk_probability
      return 0.4*self.ngram_score_raw(ngram[1:])

  def prob_coeffs(self, ngram):
      return self.coeffs[len(ngram)]

  def ngram_score_interpolation(self, ngram, res):
      if len(ngram) == 0:
          return res
      ngram_count = self.num_of_occurences(ngram)
      k = self.prob_coeffs(ngram)
      if len(ngram) == 1 and ngram_count == 0:
          p = self.unk_probability
      else:
          p = self.ngram_prob(ngram, ngram_count)
      return self.ngram_score_interpolation(ngram[1:], res + k*p)

  def ngram_score(self, ngram):
      return math.log(self.ngram_score_interpolation(ngram, 0))

  def score(self, sentence):
    score = 0.0
    ngram = []
    for token in sentence:
      push_to_cyclic_list(self.ngram_depth, ngram, token)
      s = self.ngram_score(ngram)
      score += s
    return score
