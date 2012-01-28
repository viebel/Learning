def fibonacci(n)
    return 1 if n < 2
    pre = 1
    val = 1
    (2..n).each { 
        tmp = val
        val = val + pre
        pre = tmp
    }
    return val
end
print (1..25).map {|i| fibonacci i}
