module MonadTest where

add5 :: (Monad m, Num b) => m b -> m b -> m b -> m b -> m b -> m b
add5 a b c d e = 
  a >>= (\x ->
  b >>= (\y ->
  c >>= (\z ->
  d >>= (\w ->
  e >>= (\u -> 
  return (x + y + z + w + u))))))

add5' :: (Monad m, Num b) => m b -> m b -> m b -> m b -> m b -> m b
add5' a b c d e = do
  x <- a
  y <- b
  z <- c
  w <- d
  u <- e
  return (x + y + z + w + u)
