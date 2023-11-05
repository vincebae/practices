### [Data.Monoid](https://hackage.haskell.org/package/base-4.19.0.0/docs/Data-Monoid.html)

```
type Monoid :: * -> Constraint
class Semigroup a => Monoid a where
  mempty :: a
  mappend :: a -> a -> a
  mconcat :: [a] -> a
  {-# MINIMAL mempty #-}
```

In mathematics, a [*monoid*](https://en.wikipedia.org/wiki/Monoid)
is a set equipped with an associative binary operation and identity element.
In other words, monoids are [semigroup](Semigroup.md) with identity.

A type `a` is a `Monoid` if it provides an associative function(`<>`)
that lets you combine any two values of type `a` into one,
and a neutral element (`mempty`) such that

> `a <> mempty == mempty <> a == a`

`Monoid` instances should satisfy these laws:

**Right identity**
> `x <> mempty = x`

**Left identity**
> `mempty <> x = x`

**Associativity**
> `x <> (y <> z) = (x <> y) <> z` ([`Semigroup`](Semigroup.md) law)


* **`mempty`** is the identity value that satisfies the identity laws above

  *example*

  ```
  >>> import Data.Monoid
  >>> mempty :: [a]
  []

  >>> mempty :: String
  ""

  >>> mempty :: Sum Int
  Sum {getSum = 0}

  >>> mempty :: Product Int
  Product {getProduct = 1}

  >>> mempty :: Maybe String
  Nothing
  ```

* **`mappend`** is same as `(<>)` from [`Semigroup`](Semigroup.md)

* **`mconcat`** is same as `foldr (<>) mempty`

  *example*

  ```
  >>> mconcat ["Hello", " ", "World", "!"]
  "Hello World!"

  >>> mconcat [Product 2, Product 3, Product 4]
  Product {getProduct = 24}
  
  -- Unlike sconcat from Semigroup, mconcat can be called with empty list.
  >>> mconcat [] :: Product Int
  Product {getProduct = 1}

  >>> mconcat [Min 5, Min 4, Min 3] :: Min Int
  Min {getMin = 3}
  ```

