(deftarget mobile
  (shexec "xmllint"
          (fs/glob "xml-ok/*.xml"))
  (concat-files :srcfile "list.txt" :destfile "all_in_one.js"
                :header (string/join "\n" ["//This is a generated file"
                                           "//Be careful"
                                           "\n"])
                :footer "//End of generated file\n"))
