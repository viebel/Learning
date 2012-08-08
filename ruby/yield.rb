
class Array
  def my_each(val)
    for i in 0..self.size
      yield i
    end
  end
end

class Hash
  def my_map()
    h = {}
    self.each { |k,v|
      h[k] = yield k,v
    }
    h
  end
end

p ({:a=>2}.my_map {|k,v|
  "#{k}: #{v}"
})

[1,2,3].my_each(9) { |i|
  p i
}
