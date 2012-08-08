class LambdaDict(dict):
    def __init__(self, l):
      super(LambdaDict, self).__init__()
      self.l = l
 
    def __getitem__(self, key):
        if key in self:
            return self.get(key)
        else:
            self.__setitem__(key, self.l(key))
            return self.get(key)
 
results = LambdaDict(lambda  key:0)
print results[999]
results[67]  += 1
print results[67]
results = LambdaDict(lambda  key:LambdaDict(lambda key:0))
print results[98][9]
results[98][99] += 1
print results[98][99]
