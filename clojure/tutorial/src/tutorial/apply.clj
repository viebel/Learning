(ns tutorial.apply
    )

(defmacro apply-macro[func args] (let [args (vec args)] `(~func ~@args)))
