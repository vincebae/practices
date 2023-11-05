## [Data.Functor](https://hackage.haskell.org/package/base-4.18.1.0/docs/Data-Functor.html)

```
type Functor :: (* -> *) -> Constraint
class Functor f where
  fmap :: (a -> b) -> f a -> f b
  (<$) :: a -> f b -> f a
  {-# MINIMAL fmap #-}
```

In mathematics, specifically category theory, a [*functor*](https://en.wikipedia.org/wiki/Functor)
is a mapping between categories.

A type `f` is a `Functor` if it provides a function `fmap` which, 
given any types `a` and `b` lets you apply any function from `(a -> b)` 
to turn an `f a` into an `f b`, preserving the structure of `f`.
Furthermore `f` needs to adhere to the following:

**Identity**
> `fmap id == id`

**Composition**
> `fmap (f . g) == fmap f . fmap g`

Note, that the second law follows from the free theorem of the type fmap and the first law,
so you need only check that the former condition holds.
([reference](https://www.schoolofhaskell.com/user/edwardk/snippets/fmap))

* **`fmap`** is used to apply a function of type `(a -> b)` to a value of type `f a`,
where `f` is a functor, to produce a value of type f b.
Note that for any type constructor with more than one parameter (e.g., `Either`),
only the last type parameter can be modified with `fmap` (e.g., b in `Either a b`).

  Some type constructors with two parameters or more have a `Bifunctor` instance
that allows both the last and the penultimate parameters to be mapped over.

  *examples*

  ```
  >>> fmap (+1) [1,2,3,4,5]
  [2,3,4,5,6]

  >>> fmap (+1) []
  []

  >>> fmap (+1) $ Just 5
  Just 6

  >>> fmap (+1) Nothing
  Nothing

  >>> fmap (+1) $ Left 5
  Left 5

  >>> fmap (+1) $ Right 5
  Right 6

  -- Unary functions are Functors. fmap is same as the function composition.
  -- fmap (*2) (+1) $ 5 = (*2) . (+1) $ 5 = 12
  >>> fmap (*2) (+1) $ 5
  12
  ```

* **`(<$)`** replaces all locations in the input with the same value.
The default definition is `fmap . const`, but this may be overridden with a more efficient version.

  *example*
  ```
  >>> 'a' <$ [1, 2, 3, 4, 5]
  "aaaaa"
  
  >>> 'a' <$ []
  ""
  
  >>> 'a' <$ Just 2
  Just 'a'
  
  >>> 'a' <$ Nothing
  Nothing
  
  >>> 'a' <$ Left 2
  Left 2
  
  >>> 'a' <$ Right 2
  Right 'a'
  ```

* **`(<$>)`** is an infix synonym for `fmap`

  *example*
  ```
  >>> (+1) <$> [1,2,3,4,5]
  [2,3,4,5,6]
  ```

* **`(<&>)`** is a flipped version of `<$>`

  *example*
  ```
  >>> [1,2,3,4,5] <&> (+1)
  [2,3,4,5,6]
  ```

* **`($>)`** is a flipped version of `(<$)`

  *example*
  ```
  >>> [1,2,3,4,5] $> 'a'
  "aaaaa"
  ```

* **`void :: Functor f => f a -> f () `**

  `void` discards or ignores the result of evaluation, such as the return value of an `IO` action

  *example*
  ```
  >>> void [1,2,3,4,5]
  [(),(),(),(),()]
  ```



