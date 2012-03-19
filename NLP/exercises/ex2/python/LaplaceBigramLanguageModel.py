import math

def create_couple(a,b):
  return a + '<_>' + b

class LaplaceBigramLanguageModel:
  def __init__(self, corpus):
    self.bigrams = {}
    self.unigrams = {}
    self.train(corpus)

  def num_of_occurences(self, a,b):
      return self.bigrams.get(create_couple(a,b)) or 0

  def train(self, corpus):
    def inc(dic, item):
        c = dic.get(item) or 0
        dic[item] = c + 1
        return c + 1

    for sentence in corpus.corpus: # iterate over sentences in the corpus
      previous = '<s>'
      for datum in sentence.data[1:]: # iterate over datums in the sentence
        inc(self.unigrams, datum.word)
        inc(self.bigrams, create_couple(previous,datum.word))
        previous = datum.word

  def probability(self, token, previous):
      return math.log(float(self.num_of_occurences(previous,token) + 1)/len(self.unigrams))

  def score(self, sentence):
    score = 0.0
    previous = '<s>'
    for token in sentence[1:]: # iterate over bigrams in the sentence
      score += self.probability(token, previous)
      previous = token
    return score
