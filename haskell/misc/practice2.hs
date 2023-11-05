take' :: Int -> [a] -> [a]
take' 0 _ = []
take' _ [] = []
take' n (x:xs) = x : take' (n - 1) xs

myfoldl :: (b -> a -> b) -> b -> [a] -> b
myfoldl _ acc [] = acc
myfoldl f acc (x:xs) = myfoldl f (f acc x) xs

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

triangles :: [(Int, Int, Int)]
triangles =
  [ (a, b, c)
  | c <- [1 .. 20]
  , b <- [1 .. c]
  , a <- [1 .. b]
  , a ^ 2 + b ^ 2 == c ^ 2
  ]

elemr :: (Eq a) => a -> [a] -> Bool
elemr x = foldr (\y acc -> (x == y) || acc) False

eleml :: (Eq a) => a -> [a] -> Bool
eleml x = foldl (\acc y -> (x == y) || acc) False

identityr :: [a] -> [a]
identityr = foldr (:) []

identityl :: [a] -> [a]
identityl = foldl (flip (:)) []

mapr :: (a -> b) -> [a] -> [b]
mapr f = foldr (\x acc -> f x : acc) []

mapl :: (a -> b) -> [a] -> [b]
mapl f = foldl (\acc x -> f x : acc) []

solveRPN :: String -> Double
solveRPN = head . foldl solveFn [] . words
  where
    solveFn (x:y:zs) "*" = (y * x) : zs
    solveFn (x:y:zs) "+" = (y + x) : zs
    solveFn (x:y:zs) "-" = (y - x) : zs
    solveFn xs word = read word : xs
