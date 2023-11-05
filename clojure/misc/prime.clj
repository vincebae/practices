#!/usr/bin/env bb


(defn sieve
  ([] (sieve (iterate inc 2)))
  ([candidates]
   (lazy-seq
    (let [[p & ps] candidates]
      (cons p (sieve (remove #(zero? (mod % p)) ps)))))))

(defn nthPrime
  [n]
  (cond
    (<= n 0) nil
    :else (nth (sieve) (dec n))))

(defrecord Test
           [a b]

  Object)
