import math

class LaplaceUnigramLanguageModel:
  def __init__(self, corpus):
    self.words = {}
    self.train(corpus)

  def num_of_occurences(self, item):
      return self.words.get(item) or 0

  def train(self, corpus):
    def inc(dic, item):
        c = dic.get(item) or 0
        dic[item] = c + 1
        return c + 1

    for sentence in corpus.corpus: # iterate over sentences in the corpus
      for datum in sentence.data: # iterate over datums in the sentence
        inc(self.words, datum.word)

  def probability(self, token):
      return math.log(float(self.num_of_occurences(token) + 1)/len(self.words))

  def score(self, sentence):
    score = 0.0
    for token in sentence: # iterate over words in the sentence
      score += self.probability(token)
    return score
