(import '(java.io StringReader ByteArrayInputStream))
(import '(sun.misc BASE64Decoder))
(import '(java.util.zip InflaterInputStream))

(defn inflate-text [text]
   (InflaterInputStream. (ByteArrayInputStream. (.decodeBuffer (BASE64Decoder.) text))))
(println (inflate-text "dsadsa233432dsfds"))
