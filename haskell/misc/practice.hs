import           Control.Arrow
import           Data.Char
import           Flow

fib :: Integer -> Integer
fib n
  | n <= 0 = 0
  | otherwise = fibHelper 0 1 n
  where
    fibHelper :: Integer -> Integer -> Integer -> Integer
    fibHelper prev curr 1 = curr
    fibHelper prev curr n = fibHelper curr (prev + curr) (n - 1)

seqCount :: Integer -> Integer -> Integer
seqCount n m
  | n <= 0 || m <= 0 = 1
  | otherwise = seqCount n (m - 1) + seqCount (n - 1) m

solveQuadratic :: Double -> Double -> (Double, Double)
solveQuadratic p q =
  let p' = -p / 2
      d = sqrt (p' ^ 2 - q)
   in (p' - d, p' + d)

reverseGeneric :: [t] -> [t]
reverseGeneric list = reverseGenericHelper list []
  where
    reverseGenericHelper [] acc       = acc
    reverseGenericHelper (x : xs) acc = reverseGenericHelper xs (x : acc)

filterGeneric :: (t -> Bool) -> [t] -> [t]
filterGeneric cond list = reverseGeneric $ filterGenericReverse cond list []
  where
    filterGenericReverse _ [] acc = acc
    filterGenericReverse cond (x : xs) acc
      | cond x = filterGenericReverse cond xs (x : acc)
      | otherwise = filterGenericReverse cond xs acc

mapGeneric :: (a -> b) -> [a] -> [b]
mapGeneric fn list = reverseGeneric $ mapGenericReverse fn list []
  where
    mapGenericReverse _ [] acc        = acc
    mapGenericReverse fn (x : xs) acc = mapGenericReverse fn xs (fn x : acc)

zipGeneric :: [a] -> [b] -> [(a, b)]
zipGeneric list1 list2 = reverseGeneric $ zipGenericReverse list1 list2 []
  where
    zipGenericReverse _ [] acc = acc
    zipGenericReverse [] _ acc = acc
    zipGenericReverse (x : xs) (y : ys) acc = zipGenericReverse xs ys ((x, y) : acc)

takeGeneric :: Integer -> [a] -> [a]
takeGeneric n list = reverseGeneric $ takeGenericReverse n list []
  where
    takeGenericReverse 0 _ acc        = acc
    takeGenericReverse _ [] acc       = acc
    takeGenericReverse n (x : xs) acc = takeGenericReverse (n - 1) xs (x : acc)

main = do
  print (fib 10)
  print (seqCount 2 2)
  print (solveQuadratic 1 (-2))
  print (filterGeneric (\x -> x `mod` 3 == 0) [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
  print (mapGeneric ord ['a', 'b', 'c', 'd', 'e'])
  print (zipGeneric [1, 2, 3, 4, 5] ['a', 'b', 'c'])
  print
    ( takeGeneric 10 $
        reverseGeneric $
          filterGeneric (\x -> x `mod` 5 == 0) $
            mapGeneric (* 2) [1 .. 1000000]
    )

  print
    ( ( mapGeneric (* 2)
          .> filterGeneric (\x -> x `mod` 5 == 0)
          .> reverseGeneric
          .> takeGeneric 10
      )
        [1 .. 1000000]
    )
  print
    ( ( mapGeneric (* 2)
          >>> filterGeneric (\x -> x `mod` 5 == 0)
          >>> reverseGeneric
          >>> takeGeneric 10
      )
        [1 .. 1000000]
    )

  print
    ( ( map (* 2)
          >>> filter (\x -> x `mod` 5 == 0)
          >>> reverse
          >>> take 10
      )
        [1 .. 1000000]
    )
