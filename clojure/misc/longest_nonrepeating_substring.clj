#!/usr/bin/env bb

(require '[clojure.set :as set])


(defn length-of-longest-substring
  ([string] (length-of-longest-substring (seq string) [] #{} 0))
  ([chars window char-set longest]
   (let [[x & xs] chars]
     (if x
       (if (contains? char-set x)
         (recur chars (rest window) (set/difference char-set #{(first window)}) longest)
         (recur xs (conj window x) (conj char-set x) (max longest (inc (count window)))))
       longest))))


(defn print-answer
  [string expected]
  (let [answer (length-of-longest-substring string)]
    (println "Input:" string
             ", answer:" answer
             ", expected:" expected
             ", result:" (if (= answer expected) "success" "failure"))))


(print-answer "abcabcbb" 3)
(print-answer "bbbbb" 1)
(print-answer "pwwkew" 3)
