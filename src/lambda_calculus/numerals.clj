(ns lambda-calculus.numerals
  (:require [lambda-calculus.lambda :refer :all]))

(def zero
  (λ f (λ n n)))

(def one
  (λ f (λ n (f n))))

(def two
  (λ f (λ n (f (f n)))))

(def succ
  (λ n (λ f (λ x (f ((n f) x))))))

(def pred
  (λ n (λ f (λ x (((n (λ g (λ h (h (g f))))) (λ u x)) (λ u u))))))

(def plus
  (λ m (λ n ((n succ) m))))

(def substract
  (λ m (λ n ((n pred) m))))

(def mult
  (λ m (λ n (λ f (m (n f))))))

(def exp
  (λ m (λ n (n m))))

(def fromInt
  (λ n
    (if (= n 0)
      zero
      (succ (fromInt (- n 1))))))

(def toInt
  (λ f ((f (λ n (+ n 1))) 0)))

(def λToStr
  (λ f ((f (λ n (format "f(%s)" n))) "n")))

(def toStr
  (λ f (format "λf.λn.(%s)" (λToStr f))))