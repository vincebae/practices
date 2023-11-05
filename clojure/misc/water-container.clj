#!/usr/bin/env bb

(defn area
  [heights]
  (let [width (dec (count heights))
        height (min (first heights) (last heights))]
    (* width height)))


(defn max-area
  ([heights] (max-area heights 0))
  ([heights curr-max]
   (if (empty? heights)
     curr-max
     (recur (if (< (first heights) (last heights))
              (subvec heights 1)
              (pop heights))
            (max curr-max (area heights))))))


(defn print-answer
  [heights expected]
  (let [answer (max-area heights)]
    (println "Input:" heights
             ", answer:" answer
             ", expected:" expected
             ", result:" (if (= answer expected) "success" "failure"))))


(print-answer [1 8 6 2 5 4 8 3 7] 49)
(print-answer [1 1] 1)
