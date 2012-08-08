def FizzBuzz(n):
  def disp(i):
    if (i % 3 == 0):
      if (i % 5 == 0):
        return "FizzBuzz"
      return "Fizz"
    if (i % 5 == 0):
      return "Buzz"
    return i

  return [disp(i) for i in filter(lambda n: n>0, range(n))]
    
print FizzBuzz(20)

