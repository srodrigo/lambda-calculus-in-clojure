(ns lambda-calculus.lambda-test
  (:require [clojure.test :refer :all]
            [lambda-calculus.lambda :refer :all]))

(deftest λ-macro
  (testing "creates nested λ's"
    (is (= (((λ x (λ y (+ y x))) 4) 3) 7))))
