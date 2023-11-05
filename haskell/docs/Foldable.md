### [Data.Foldable](https://hackage.haskell.org/package/base-4.19.0.0/docs/Data-Foldable.html)

```
type Foldable :: (* -> *) -> Constraint
class Foldable t where
  Data.Foldable.fold :: Monoid m => t m -> m
  foldMap :: Monoid m => (a -> m) -> t a -> m
  Data.Foldable.foldMap' :: Monoid m => (a -> m) -> t a -> m
  foldr :: (a -> b -> b) -> b -> t a -> b
  Data.Foldable.foldr' :: (a -> b -> b) -> b -> t a -> b
  foldl :: (b -> a -> b) -> b -> t a -> b
  Data.Foldable.foldl' :: (b -> a -> b) -> b -> t a -> b
  foldr1 :: (a -> a -> a) -> t a -> a
  foldl1 :: (a -> a -> a) -> t a -> a
  Data.Foldable.toList :: t a -> [a]
  null :: t a -> Bool
  length :: t a -> Int
  elem :: Eq a => a -> t a -> Bool
  maximum :: Ord a => t a -> a
  minimum :: Ord a => t a -> a
  sum :: Num a => t a -> a
  product :: Num a => t a -> a
  {-# MINIMAL foldMap | foldr #-}
```

The `Foldable` class represents data structures that can be reduced to a summary value.

* **`foldMap`** maps each element of the structure into a monoid, and combines the results with `(<>)`.

  *example*

  ```
  >>> foldMap Sum [1, 3, 5]
  Sum {getSum = 9}

  >>> foldMap Product [1, 3, 5]
  Product {getProduct = 15}

  >>> foldMap (\x -> [1..x]) [1, 2, 3, 4]
  [1,1,2,1,2,3,1,2,3,4]
  ```

* **`fold`** combines the `Monoid` elements from the structure via `(<>)`.

  *example*

  ```
  >>> fold [Sum 1, Sum 3, Sum 5]
  Sum {getSum = 9}

  >>> fold [[1, 2, 3], [4, 5], [6], []]
  [1,2,3,4,5,6]
  ```

* **`foldr`** is a right-associative fold of a structure.

  *example*

  ```
  >>> foldr (\c acc -> acc ++ [c]) "foo" ['a', 'b', 'c', 'd']
  "foodcba"

 v >>> foldr ((++) . (:[])) "foo" ['a', 'b', 'c', 'd']
  "abcdfoo"
  ```
