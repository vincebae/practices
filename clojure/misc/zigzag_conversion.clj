#!/usr/bin/env bb

(defn jump
  [string steps]
  (loop [[x & _ :as ch-seq] string steps steps res []]
    (if x (recur (drop (first steps) ch-seq) (rest steps) (conj res x)) res)))


(defn zigzag1
  [string rows]
  (if (or (= rows 1) (<= (count string) rows))
    string
    (loop [string string
           step1 (* (- rows 1) 2)
           step2 0
           res []]
      (if (neg? step1)
        (apply str res)
        (let [steps (cycle (filter pos? [step1 step2]))]
          (recur (rest string) (- step1 2) (+ step2 2) (into res (jump string steps))))))))


(defn zigzag-pattern
  [rows]
  (-> (concat (range rows) (range (- rows 2) 0 -1))
      cycle))


(defn zigzag2
  [string rows]
  (if (or (= rows 1) (<= (count string) rows))
    string
    (->> (map vector (seq string) (zigzag-pattern rows))
         (reduce (fn [acc [ch index]] (update acc index #(conj % ch)))
                 (vec (repeat rows [])))
         flatten
         (apply str))))


(def zigzag zigzag2)


(defn print-answer
  [string rows expected]
  (let [answer (zigzag string rows)]
    (println "Input:" string
             ", answer:" answer
             ", expected:" expected
             ", result:" (if (= answer expected) "success" "failure"))))


(print-answer "PAYPALISHIRING" 3 "PAHNAPLSIIGYIR")
(print-answer "PAYPALISHIRING" 4 "PINALSIGYAHRPI")
(print-answer "A" 1 "A")
