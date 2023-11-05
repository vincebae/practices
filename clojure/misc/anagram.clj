(ns anagram)

(defn group-anagram
  [words]
  (vals (group-by frequencies words)))

(group-anagram ["abc" "bca" "aabc" "baac" "aba" "bba" "bab"])
 
