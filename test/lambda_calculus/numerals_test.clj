(ns lambda-calculus.numerals-test
  (:require [clojure.test :refer :all]
            [lambda-calculus.lambda :refer :all]
            [lambda-calculus.numerals :refer :all]))

(deftest λ-numbers
  (testing "zero"
    (is (= (toInt zero) 0)))

  (testing "one"
    (is (= (toInt (succ zero)) 1))
    (is (= (toInt one)) 1))

  (testing "two"
    (is (= (toInt (succ (succ zero))) 2))
    (is (= (toInt two)) 2))

  (testing "three"
    (is (= (toInt (succ (succ (succ zero)))) 3)))

  (testing "predecessor"
    (is (= (toInt (pred one)) 0))
    (is (= (toInt (pred two)) 1))
    (is (= (toInt (pred (succ (succ (succ zero))))) 2))
    (is (= (toInt (pred (fromInt 10))) 9))))

(deftest λ-numerical-operations
  (testing "addition"
    (is (=
         (toInt ((plus (fromInt 7)) (fromInt 5)))
         12))
    (is (=
         (toInt ((plus (fromInt 7)) ((plus (fromInt 6)) (fromInt 2))))
         15)))

  (testing "subtraction"
    (is (=
         (toInt ((minus (fromInt 7)) (fromInt 5)))
         2))
    (is (=
         (toInt ((minus (fromInt 7)) ((minus (fromInt 6)) (fromInt 2))))
         3)))

  (testing "multiplication"
    (is (=
          (toInt ((mult (fromInt 2)) (fromInt 3)))
          6))
    (is (=
          (toInt ((mult (fromInt 2)) ((mult (fromInt 5)) (fromInt 3))))
          30)))

  (testing "exponentiation"
    (is (=
          (toInt ((exp (fromInt 2)) (fromInt 3)))
          8))
    (is (=
          (toInt ((exp (fromInt 2)) ((exp (fromInt 2)) (fromInt 3))))
          256))))

(deftest λ-numeral-expressions
  (testing "numeral expressions"
    ; 3 * (2 + 5) - 2^3
    (is (=
         (toInt ((minus ((mult (fromInt 3))
                         ((plus (fromInt 2)) (fromInt 5))))
                 ((exp (fromInt 2)) (fromInt 3))))
         13))))

(deftest λ-toStr
  (testing "toStr"
    (is (= (toStr zero) "λf.λn.(n)"))
    (is (= (toStr one) "λf.λn.(f(n))"))
    (is (= (toStr two) "λf.λn.(f(f(n)))"))
    (is (= (toStr (succ (succ (succ zero)))) "λf.λn.(f(f(f(n))))"))
    (is (= (toStr (fromInt 5)) "λf.λn.(f(f(f(f(f(n))))))"))))
