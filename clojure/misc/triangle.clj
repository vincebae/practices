#!/usr/bin/env bb
(defn range-closed
  [start end]
  (range start (inc end)))


(defn sq
  [n]
  (* n n))


(def triangle
  (for [c (range-closed 1 20)
        b (range-closed 1 c)
        a (range-closed 1 b)
        :when (= (+ (sq a) (sq b)) (sq c))]
    (vector a b c)))


(doall (map println triangle))

