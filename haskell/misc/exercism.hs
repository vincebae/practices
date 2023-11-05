import Control.Applicative
  ( Applicative (liftA2),
    ZipList (ZipList, getZipList),
  )
import Control.Arrow ()
import Data.Bits (Bits (testBit))
import Data.Char
  ( digitToInt,
    intToDigit,
    isAlphaNum,
    isDigit,
    isLetter,
    isSpace,
    isUpper,
    toLower,
    toUpper,
  )
import Data.Either ()
import Data.List (foldl', intercalate, sort)
import Data.List.Split ()
import qualified Data.Map as M
import qualified Data.Text as T
import Text.Printf

toRNA :: String -> Either Char String
toRNA = foldr combineEither (Right []) . fmap transform
  where
    transform 'G' = Right 'C'
    transform 'C' = Right 'G'
    transform 'T' = Right 'A'
    transform 'A' = Right 'U'
    transform chr = Left chr

combineEither :: Either a a -> Either a [a] -> Either a [a]
combineEither (Left x) _ = Left x
combineEither _ (Left x) = Left x
combineEither (Right x) (Right y) = Right (x : y)

data Nucleotide
  = A
  | C
  | G
  | T
  deriving (Eq, Ord, Show, Enum, Bounded)

nucleotideCounts :: String -> Either String (M.Map Nucleotide Int)
nucleotideCounts =
  fmap (foldr (\x y -> M.insertWith (+) x 1 y) M.empty) . traverse nu
  where
    nu :: Char -> Either String Nucleotide
    nu 'A' = Right A
    nu 'C' = Right C
    nu 'G' = Right G
    nu 'T' = Right T
    nu _ = Left "error"

count :: (Ord a) => [a] -> M.Map a Int
count = foldr (\x y -> M.insertWith (+) x 1 y) M.empty

sumOfMultiples :: [Integer] -> Integer -> Integer
sumOfMultiples factors limit =
  sum [x | x <- [1 .. (limit - 1)], shouldEarnPoints x factors]
  where
    shouldEarnPoints level = any ((== 0) . mod level)

abbreviate :: String -> String
abbreviate = concatMap (extract . filter isLetter) . words . map replaceHypen

extract :: String -> String
extract "" = ""
extract str
  | all isUpper str = [head str]
  | otherwise = toUpper (head str) : filter isUpper (tail str)

replaceHypen :: Char -> Char
replaceHypen '-' = ' '
replaceHypen chr = chr

anagramsFor :: String -> [String] -> [String]
anagramsFor xs = filter (isAnagram xs)

isAnagram :: String -> String -> Bool
isAnagram xs ys = isAnagram' (map toLower xs) (map toLower ys)
  where
    isAnagram' xs ys = length xs == length ys && xs /= ys && sort xs == sort ys

data Clock = Clock
  { hour :: Int,
    min :: Int
  }
  deriving (Eq, Show)

minsInHour :: Int
minsInHour = 60

hoursInDay :: Int
hoursInDay = 24

fromHourMin :: Int -> Int -> Clock
fromHourMin hour min = Clock hour'' min'
  where
    min' = min `mod` minsInHour + if min < 0 then minsInHour else 0
    hour' = hour + if min >= 0 then min `div` minsInHour else (min + 1) `div` minsInHour - 1
    hour'' = hour' `mod` hoursInDay + if hour' < 0 then hoursInDay else 0

toString :: Clock -> String
toString (Clock hour min) = printf "%02d:%02d" hour min

addDelta :: Int -> Int -> Clock -> Clock
addDelta hour min (Clock hour' min') = Clock (hour + hour') (min + min')

encode :: String -> String
encode = T.unpack . T.unwords . T.transpose . squarize . T.toLower . T.filter isAlphaNum . T.pack

squarize :: T.Text -> [T.Text]
squarize xs
  | T.null xs = []
  | otherwise = T.chunksOf width padded
  where
    width = ceiling . sqrt . fromIntegral . T.length $ xs
    height = (T.length xs - 1) `div` width + 1
    padded = T.justifyLeft (width * height) ' ' xs

part :: Int -> [a] -> [[a]]
part 0 _ = []
part _ [] = []
part n xs = take n xs : part n (drop n xs)

mirror :: [String] -> [String]
mirror [] = []
mirror xs = getZipList . foldr (liftA2 (:) . ZipList) initial $ xs
  where
    initial = ZipList (replicate (length . head $ xs) [])

isValid :: String -> Bool
isValid n
  | length normalized <= 1 = False
  | not . all isDigit $ normalized = False
  | otherwise = checksum normalized `mod` 10 == 0
  where
    normalized = filter (not . isSpace) n

checksum :: String -> Int
checksum = checksum' 0 . map digitToInt . reverse
  where
    checksum' :: Int -> [Int] -> Int
    checksum' acc [] = acc
    checksum' acc [x] = x + acc
    checksum' acc (x : y : zs) = checksum' (acc + x + y * 2 - if y >= 5 then 9 else 0) zs

divides :: (Integral a) => a -> a -> Bool
divides x y = (y `mod` x) == 0

isDivisibleBy :: (Integral a) => a -> a -> Bool
isDivisibleBy = flip divides

newtype PrimeIterator = PrimeIterator
  { primes :: [Integer]
  }

newPrimeIterator :: PrimeIterator
newPrimeIterator = PrimeIterator [2 ..]

curr :: PrimeIterator -> Integer
curr (PrimeIterator (x : xs)) = x

next :: PrimeIterator -> PrimeIterator
next (PrimeIterator (x : xs)) = PrimeIterator (filter (not . divides x) xs)

nth :: Int -> Maybe Integer
nth n
  | n <= 0 = Nothing
  | otherwise = Just . nth' newPrimeIterator $ n
  where
    nth' iter 1 = curr iter
    nth' iter n = nth' (next iter) (n - 1)

annotate :: [String] -> [String]
annotate board = (zipWith . zipWith) annotateSingle board (countNeighbors board)

annotateSingle :: Char -> Int -> Char
annotateSingle '*' _ = '*'
annotateSingle _ 0 = ' '
annotateSingle _ n = intToDigit n

countNeighbors :: [String] -> [[Int]]
countNeighbors = map add3InRow . add3List . (map . map) (fromEnum . (== '*'))

add3InRow :: [Int] -> [Int]
add3InRow row = map3 add3 (0 : row ++ [0])

add3List :: [[Int]] -> [[Int]]
add3List lists = map3 (zipWith3 add3) (repeat 0 : lists ++ [repeat 0])

map3 :: (a -> a -> a -> b) -> [a] -> [b]
map3 f l@(x : y : z : _) = f x y z : map3 f (tail l)
map3 _ _ = []

add3 :: (Num a) => a -> a -> a -> a
add3 x y z = x + y + z

countNeighbors2 :: [String] -> [[Int]]
countNeighbors2 board = foldr1 addMatrix . map (\(x, y) -> shiftBoard x y counts) $ neighbors
  where
    counts = (map . map) (fromEnum . (== '*')) board
    neighbors = [(x, y) | x <- [-1, 0, 1], y <- [-1, 0, 1], not (x == 0 && y == 0)]
    addMatrix = zipWith (zipWith (+))

shiftBoard :: Int -> Int -> [[Int]] -> [[Int]]
shiftBoard x y = shiftBoardY y . shiftBoardX x

shiftBoardX :: Int -> [[Int]] -> [[Int]]
shiftBoardX x board
  | x == 0 = board
  | x > 0 = map (\row -> replicate x 0 ++ take (length row - x) row) board
  | x < 0 = map (\row -> drop (-x) row ++ replicate (-x) 0) board

shiftBoardY :: Int -> [[Int]] -> [[Int]]
shiftBoardY y board
  | y == 0 = board
  | y > 0 = replicate y emptyRow ++ take (rows - y) board
  | y < 0 = drop (-y) board ++ replicate (-y) emptyRow
  where
    rows = length board
    emptyRow = replicate (length (head board)) 0

number :: String -> Maybe String
number = normalize . filter isDigit
  where
    normalize :: String -> Maybe String
    normalize ys = case length ys of
      11 -> if head ys == '1' then normalize (tail ys) else Nothing
      10 -> if all (`elem` ['2' .. '9']) [(ys !! 0), (ys !! 3)] then Just ys else Nothing
      _ -> Nothing

handshake :: Int -> [String]
handshake n = (if testBit n 4 then reverse else id) codes
  where
    codes = map code . filter (testBit n) $ [0 .. 3]
    code 0 = "wink"
    code 1 = "double blink"
    code 2 = "close your eyes"
    code 3 = "jump"
    code _ = ""

hammingDistance :: String -> String -> Maybe Int
hammingDistance str1 str2
  | length str1 /= length str2 = Nothing
  | otherwise = Just (hammingDistance' 0 str1 str2)
  where
    hammingDistance' acc [] [] = acc
    hammingDistance' acc (x : xs) (y : ys) =
      hammingDistance' (if x == y then acc else succ acc) xs ys

data Bearing
  = North
  | East
  | South
  | West
  deriving (Enum, Eq, Show)

data Robot = Robot
  { direction :: Bearing,
    coord :: (Integer, Integer)
  }
  deriving (Show)

bearing :: Robot -> Bearing
bearing = direction

coordinates :: Robot -> (Integer, Integer)
coordinates = coord

mkRobot :: Bearing -> (Integer, Integer) -> Robot
mkRobot = Robot

move :: Robot -> String -> Robot
move = foldl' execute

execute :: Robot -> Char -> Robot
execute robot 'A' = advance robot
execute robot 'R' = turnRight robot
execute robot 'L' = turnLeft robot

advance :: Robot -> Robot
advance (Robot North (x, y)) = Robot North (x, y + 1)
advance (Robot East (x, y)) = Robot East (x + 1, y)
advance (Robot South (x, y)) = Robot South (x, y - 1)
advance (Robot West (x, y)) = Robot West (x - 1, y)

turnRight :: Robot -> Robot
turnRight (Robot West coord) = Robot North coord
turnRight (Robot bearing coord) = Robot (succ bearing) coord

turnLeft :: Robot -> Robot
turnLeft (Robot North coord) = Robot West coord
turnLeft (Robot bearing coord) = Robot (pred bearing) coord

data Ordinal
  = First
  | Second
  | Third
  | Fourth
  | Fifth
  | Sixth
  | Seventh
  | Eighth
  | Ninth
  | Tenth
  | Eleventh
  | Twelfth
  deriving (Enum, Eq, Show, Ord, Bounded)

gift :: Ordinal -> String
gift First = "a Partridge in a Pear Tree"
gift Second = "two Turtle Doves, and "
gift Third = "three French Hens, "
gift Fourth = "four Calling Birds, "
gift Fifth = "five Gold Rings, "
gift Sixth = "six Geese-a-Laying, "
gift Seventh = "seven Swans-a-Swimming, "
gift Eighth = "eight Maids-a-Milking, "
gift Ninth = "nine Ladies Dancing, "
gift Tenth = "ten Lords-a-Leaping, "
gift Eleventh = "eleven Pipers Piping, "
gift Twelfth = "twelve Drummers Drumming, "

recite :: Int -> Int -> [String]
recite start end = map (reciteSingle . toEnum . subtract 1) [start .. end]

reciteSingle :: Ordinal -> String
reciteSingle day =
  "On the " ++ dayStr ++ " day of Christmas my true love gave to me: " ++ gifts ++ "."
  where
    dayStr = map toLower . show $ day
    gifts = intercalate "" (map gift . reverse $ [First .. day])

pascalRows :: Int -> [[Integer]]
pascalRows x = take x $ iterate nextRow [1]
  where
    nextRow row = zipWith (+) (0 : row) (row ++ [0])

scanSum :: [Integer] -> [Integer]
scanSum l@(x : y : _) = (x + y) : (scanSum . tail $ l)
scanSum _ = []
