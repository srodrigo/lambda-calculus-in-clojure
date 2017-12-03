(ns lambda-calculus.booleans-test
  (:require [clojure.test :refer :all]
            [lambda-calculus.lambda :refer :all]
            [lambda-calculus.booleans :refer :all]))

(deftest λ-booleans
  (testing "true"
    (is (= (toBoolean T) true)))

  (testing "false"
    (is (= (toBoolean F) false)))

  (testing "If"
    (is (= (toBoolean (((If T) T) F)) true))
    (is (= (toBoolean (((If F) T) F)) false)))

  (testing "And"
    (is (= (toBoolean ((And T) T)) true))
    (is (= (toBoolean ((And T) F)) false))
    (is (= (toBoolean ((And F) T)) false))
    (is (= (toBoolean ((And F) F)) false))
    (is (= (toBoolean ((And T) ((And T) T))) true))
    (is (= (toBoolean ((And T) ((And F) T))) false)))

  (testing "Or"
    (is (= (toBoolean ((Or T) T)) true))
    (is (= (toBoolean ((Or T) F)) true))
    (is (= (toBoolean ((Or F) T)) true))
    (is (= (toBoolean ((Or F) F)) false))
    (is (= (toBoolean ((Or F) ((Or F) F))) false))
    (is (= (toBoolean ((Or F) ((Or T) F))) true)))

  (testing "Not"
    (is (= (toBoolean (Not T)) false))
    (is (= (toBoolean (Not F)) true))
    (is (= (toBoolean (Not (Not T))) true))
    (is (= (toBoolean (Not (Not F))) false)))

  (testing "Xor"
    (is (= (toBoolean ((Xor T) T)) false))
    (is (= (toBoolean ((Xor T) F)) true))
    (is (= (toBoolean ((Xor F) T)) true))
    (is (= (toBoolean ((Xor F) F)) false)))

  (testing "Expressions"
    ;T AND T AND F OR T
    (is (= (toBoolean ((And T) ((And T) ((Or F) T))))
           true))

    ;T AND F AND F OR T AND T
    (is (= (toBoolean ((And T) ((And F) ((Or F) ((And T) T)))))
           false))

    ;T OR (F AND F OR T AND T)
    (is (= (toBoolean ((Or T) ((And F) ((Or F) ((And T) T)))))
           true))

    ;F OR (F AND F OR F AND T)
    (is (= (toBoolean ((Or F) ((And F) ((Or F) ((And F) T)))))
           false))

    ;F OR F AND F OR T AND T
    (is (= (toBoolean ((And ((Or F) F)) ((And T) ((Or F) T))))
           false))

    ;T OR F AND F OR T AND T
    (is (= (toBoolean ((And ((Or T) F)) ((And T) ((Or F) T))))
           true))))
