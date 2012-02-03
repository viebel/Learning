(ns tutorial.macros)
(defmacro stringify [s]
  `~(name s))
