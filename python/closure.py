import sys
def c(b):
  def getme():
    return b
  def setme(x):
    b = x
  return {'get': getme, 'set': setme}

a = c(8)
print a
a['set'](9)
print a['get']()

print '***********\n'
def a(b):
  return (lambda f:b)

print a(9)(5)

def out(sep):
  return lambda  *x:sys.stdout.write(sep.join(map(str,x)) + "\n")

out (":")(1,2,3)

out = lambda *x:sys.stdout.write(" ".join(map(str,x)))
out (1,2,3)


