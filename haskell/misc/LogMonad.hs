#!/usr/bin/env runghc
-- --resolver lts-21.11 script

module LogMonad where

import Data.Function

data ValueWithLog a = ValueWithLog
  { log :: [String],
    value :: a
  }
  deriving (Show)

instance Functor ValueWithLog where
  fmap :: (a -> b) -> ValueWithLog a -> ValueWithLog b
  fmap f (ValueWithLog l v) = ValueWithLog l (f v)

instance Applicative ValueWithLog where
  pure :: a -> ValueWithLog a
  pure = ValueWithLog []
  (<*>) :: ValueWithLog (a -> b) -> ValueWithLog a -> ValueWithLog b
  (<*>) (ValueWithLog l1 f) (ValueWithLog l2 v) = ValueWithLog (l1 <> l2) (f v)

instance Monad ValueWithLog where
  (>>=) (ValueWithLog l1 value) f =
    let (ValueWithLog l2 newValue) = f value
     in ValueWithLog (l1 <> l2) newValue

main :: IO ()
main = do
  let n = 5
  -- same as pred . (* 2) . (^ 2) . succ $ n
  let plainResult = n & succ & (^ 2) & (* 2) & pred
  putStrLn $ "plain result = " <> show plainResult
  let fmapResult = fmap pred . fmap (* 2) . fmap (^ 2) . fmap succ $ ValueWithLog ["start"] n
  putStrLn $ "fmap result = " <> show fmapResult
  let bindResult =
        ValueWithLog ["start"] n
          >>= (ValueWithLog ["increase by 1"] . succ)
          >>= (ValueWithLog ["square"] . (^ 2))
          >>= (ValueWithLog ["double"] . (* 2))
          >>= (ValueWithLog ["decrease by 1"] . pred)
  putStrLn $ "bind result = " <> show bindResult
