#!/usr/bin/env bb

(defn longest-palindrome
  [string]
  "")

(defn print-answer
  [string expected]
  (let [answer (longest-palindrome string)]
    (println "Input:" string
             ", answer:" answer
             ", expected:" expected
             ", result:" (if (= answer expected) "success" "failure"))))

(print-answer "babad" "bab")
(print-answer "cbbd" "bb")

