def createGenerator(n):
  for i in range(n):
    yield 10*i

g = createGenerator(2)
for i in g:
  print i

print '**********'
for i in g:
  print i

