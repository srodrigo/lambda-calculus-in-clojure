(ns lambda-calculus.lambda)

(defmacro λ
  [args & body]
  `(fn [~args] ~@body))
