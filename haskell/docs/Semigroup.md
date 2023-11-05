### [Data.Semigroup](https://hackage.haskell.org/package/base-4.19.0.0/docs/Data-Semigroup.html)

```
type Semigroup :: * -> Constraint
class Semigroup a where
  (<>) :: a -> a -> a
  sconcat :: NonEmpty a -> a
  stimes :: Integral b => b -> a -> a
  {-# MINIMAL (<>) #-}
```

In mathematics, a [*semigroup*](https://en.wikipedia.org/wiki/Semigroup)
is an algebraic structure consisting of a set together
with an associative internal binary operation on it.

A type `a` is a `Semigroup` if it provides an associative function `(<>)`
that lets you combine any two values of type `a` into one,
where being associative means that the following must always hold:

> `(a <> b) <> c == a <> (b <> c)`

A `Semigroup` is a generalization of a [`Monoid`](Monoid.md),
except that `Monoid` requires the presence of a neutral element (`mempty`).

* **`(<>)`** is an associative operation

  *example*

  ```
  >>> [1,2,3] <> [4,5,6]
  [1,2,3,4,5,6]

  >>> Min 1 <> Min 2 <> Min 3
  Min {getMin = 1}

  >>> Sum 1 <> Sum 2 <> Sum 3
  Sum {getSum = 6}

  >>> Product 2 <> Product 3 <> Product 4
  Product {getProduct = 24}

  >>> Just "a" <> Just "b"
  Just "ab"

  >>> Just "a" <> Nothing
  Just "a"

  >>> Nothing <> Just "a"
  Just "a"

  >>> Just "a" <> Just "b" <> Just "c"
  Just "abc"
  ```

* **`sconcat`** reduces a non-empty list with `<>`

  *example*

  ```
  >>> sconcat $ "a" :| ["b","c"]
  "abc"
  ```

* **`stimes`** repeats a value n times.

  *example*

  ```
  >>> stimes 4 [1]
  [1,1,1,1]

  >>> stimes 0 [1]
  []
  ```

