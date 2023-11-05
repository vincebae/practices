## [Control.Applicative](https://hackage.haskell.org/package/base-4.18.1.0/docs/Control-Applicative.html)

```
type Applicative :: (* -> *) -> Constraint
class Functor f => Applicative f where
  pure :: a -> f a
  (<*>) :: f (a -> b) -> f a -> f b
  liftA2 :: (a -> b -> c) -> f a -> f b -> f c
  (*>) :: f a -> f b -> f b
  (<*) :: f a -> f b -> f a
  {-# MINIMAL pure, ((<*>) | liftA2) #-}
```

In functional programming, [*an applicative functor*](https://en.wikipedia.org/wiki/Applicative_functor)
is an intermediate structure between [`Functor`](Functor.md) and [`Monad`](Monad.md).
Applicative functors allow for functorial computations to be sequenced (unlike plain functors),
but don't allow using results from prior computations in the definition of subsequent ones
(unlike monads).

A functor is an applicative functor if it provides operations to

* embed pure expressions (`pure`)
* sequence computations and combine their results (`<*>` and/or `liftA2`)

In case both `<*>` and `liftA2` are defined, they must behave
> `(<*>) = liftA2 id`

> `liftA2 f x y = f <$> x <*> y`

Further, any definition must satisfy the following:

**Identity**

> `pure id <*> v = v`

**Composition**

> `pure (.) <*> u <*> v <*> w = u <*> (v <*> w)`

**Homomorphism**

> `pure f <*> pure x = pure (f x)`

**Interchange**

> `u <*> pure y = pure ($ y) <*> u`

* **`pure`** lifts a value

  *example*

  ```
  >>> pure 1 :: [Int]
  [1]

  >>> pure 1 :: Maybe Int
  Just 1
  ```

* **`(<*>)`** sequences application

  *example*

  ```
  >>> Just (+1) <*> Just 2
  Just 3

  >>> [(+1), (*2)] <*> [1,2,3]
  [2,3,4,2,4,6]

  -- Below three are same.
  >>>  pure (++) <*> ["a","b","c"] <*> ["x","y","z"]
  ["ax","ay","az","bx","by","bz","cx","cy","cz"]

  >>> fmap (++) ["a", "b", "c"] <*> ["x", "y", "z"]
  ["ax","ay","az","bx","by","bz","cx","cy","cz"]

  >>> (++) <$> ["a", "b", "c"] <*> ["x", "y", "z"]
  ["ax","ay","az","bx","by","bz","cx","cy","cz"]

  >>> (+) <$> ZipList [1,2,3] <*> ZipList [4,5,6]
  ZipList {getZipList = [5,7,9]}
  
  >>> add4 a b c d = a + b + c + d
  >>> add4 <$> Just 1 <*> Just 2 <*> Just 3 <*> Just 4
  Just 10

  -- Below are cases for 'function as applicatives'.
  -- (g <*> f) x = g x (f x)
  -- So, ((*) <*> (+2)) 3 = (*) 3 ((+2) 3) = 3 * 5 = 15
  >>> ((*) <*> (+2)) 3
  15

  -- (flip (-) <*> (*5)) 3 = flip (-) 3 ((*5) 3) = 15 - 3 = 12
  >>> (flip (-) <*> (*5)) 3
  12
  ```

* **`liftA2`** lifts a binary function to actions.
  `liftA2 f x y` is same as `f <$> x <*> y`

  *example*

  ```
  >>> liftA2 (+) (Just 2) (Just 3)
  Just 5

  >>> liftA2 (+) [1,2,3] [10,20,30]
  [11,21,31,12,22,32,13,23,33]
  ```

* **`(*>)`** sequences actions, discarding the value of the first argument

* **`(<*)`** sequences actions, discarding the value of the second argument

  *example*

  ```
  >>> Just 5 *> Just 'a'
  Just 'a'

  -- When used with Maybe, Maybe computations can be chained
  -- with a possible "early return" in case of Nothing.
  >>> Nothing *> Just 'a'
  Nothing

  >>> Just 5 *> Nothing
  Nothing

  >>> Just 5 <* Just 'a'
  Just 5

  >>> Just 5 <* Nothing
  Nothing

  >>> Nothing <* Just 'a'
  Nothing

  >>> [1,2,3] *> ['a','b','c']
  "abcabcabc"

  >>> [1,2,3] *> []
  []

  >>> [] *> ['a','b','c']
  ""

  >>> [1,2,3] <* ['a','b','c']
  [1,1,1,2,2,2,3,3,3]

  >>> [1,2,3] <* []
  []

  >>> [] <* ['a','b','c']
  []
  ```
