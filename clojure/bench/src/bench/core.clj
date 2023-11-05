(ns bench.core
  (:gen-class)
  (:require
    [criterium.core :refer [bench]]))


;; Bench tests map / vec vs into vs reduce / conj vs conj / recur
(def calculate inc)
(def num-items 100000)


(defn bench-map-vec
  []
  (vec (map calculate (range num-items))))


(defn bench-into
  []
  (into [] (map calculate (range num-items))))


(defn bench-reduce-conj
  []
  (reduce #(conj %1 (calculate %2)) [] (range num-items)))


(defn bench-conj-recur
  []
  (loop [[x & xs] (range num-items) acc []]
    (if x
      (recur xs (conj acc (calculate x)))
      acc)))


(defn bench-1
  []
  (println "Bench 1 start")
  (println "Bench conj-recur")
  (bench (doall (bench-conj-recur)))
  (println "Bench map-vec")
  (bench (doall (bench-map-vec)))
  (println "Bench into")
  (bench (doall (bench-into)))
  (println "Bench reduce-conf")
  (bench (doall (bench-reduce-conj)))
  (println "Bench 1 done"))


;; Bench tests map vs for
(defn bench-map
  []
  (map calculate (range num-items)))


(defn bench-for
  []
  (for [x (range num-items)] (calculate x)))


(defn bench-2
  []
  (println "Bench 2 start")
  (println "Bench map")
  (bench (doall (bench-map)))
  (println "Bench for")
  (bench (doall (bench-for)))
  (println "Bench 2 done"))


;; bench tests list vs vector
(defn bench-into-list
  []
  (into (list) (range num-items)))


(defn bench-into-vector
  []
  (into (vector) (range num-items)))


(defn bench-3
  []
  (println "Bench 3 start")
  (println "Bench into list")
  (bench (doall (bench-into-list)))
  (println "Bench into vector")
  (bench (doall (bench-into-vector)))
  (println "Bench 3 done"))


(defn -main
  [& args]
  (println "Hello, World!")
  ;; (bench-1)
  ; (bench-2)
  (bench-3)
  (println "All bench done")
  ;;
  )
