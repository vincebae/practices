

sumFromTo :: Int -> Int -> Int
sumFromTo start end
  | start >= end = start
  | otherwise = start + sumFromTo (start + 1) end

main = do
   print (sumFromTo 1 10)
