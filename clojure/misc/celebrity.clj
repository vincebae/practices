#!/usr/bin/env bb

(def ^:dynamic knowledge-map {})


(defn- knows?
  "Return true if source knows target, false otherwise"
  [source target]
  (or (= source target)
      (contains? (get knowledge-map source) target)))


(defn- find-candidate
  [people]
  (reduce #(if (knows? %1 %2) %2 %1) (first people) (rest people)))


(defn- celebrity?
  [candidate people]
  (every?
    #(or (= candidate %)
         (and (knows? % candidate)
              (not (knows? candidate %))))
    people))


(defn find-celebrity
  [k-map]
  (binding
    [knowledge-map k-map]
    (let [people (keys k-map)
          candidate (find-candidate people)]
      (if (celebrity? candidate people) candidate nil))))


(println (find-celebrity
           {:a #{:b :c :d}
            :b #{}
            :c #{:b}
            :d #{:a :b}}))


(println (find-celebrity {}))
