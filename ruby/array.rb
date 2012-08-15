class MySqlArray < Array
  def initialize arr
    @array = arr
  end

  def each(arg, &block)
    @array.each &block
  end
end


(MySqlArray.new [1,2,3]).each(:a=>:b) {|k|
  p k
}
