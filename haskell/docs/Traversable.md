### Traversable

```
type Traversable :: (* -> *) -> Constraint
class (Functor t, Foldable t) => Traversable t where
  traverse :: Applicative f => (a -> f b) -> t a -> f (t b)
  sequenceA :: Applicative f => t (f a) -> f (t a)
  mapM :: Monad m => (a -> m b) -> t a -> m (t b)
  sequence :: Monad m => t (m a) -> m (t a)
  {-# MINIMAL traverse | sequenceA #-}
```

Traversals are [`Functor`s](Functor.md) representing data structures that can be transformed to structures of the *same shape*
by performing an [`Applicative`](Applicative.md) (or [`Monad`](Monad.md)) action
on each element from left to right.

*Same shape* means that
* for input `[a]`, each output structure is `[b]` of the same length as the input.
* for input `Tree a`, each output `Tree b` has the same graph of intermediate nodes and leaves.
* for input `(x, a)`, each output is a 2-tuple `(x, b)`

* **`traverse`** maps each element of a structure to an action,
  evaluates these actions from left to right, and collect the results.

  *example*

  ```
  >>> traverse Just [1,2,3,4]
  Just [1,2,3,4]

  >>> traverse id [Right 1, Right 2, Right 3, Right 4]
  Right [1,2,3,4]

  >>> traverse (\x -> [x]) $ Just 1
  [Just 1]
  
  >>> traverse (\x -> [x]) [1,2,3,4]
  [[1,2,3,4]]

  >>> traverse id [[1], [2,3], [4,5,6]]
  [[1,2,4],[1,2,5],[1,2,6],[1,3,4],[1,3,5],[1,3,6]]

  -- Nothing and Left values short circuit the structure
  >>> traverse (\x -> if odd x then Just x else Nothing)  [1..]
  Nothing

  >>> traverse id [Right 1, Left 2, Right 3, Left 4]
  Left 2
  ```

* **`sequenceA`** evaluates each action in the structure from left to right,
  and collect the results. Same as `traverse id`

  *example*

  ```
  >>> sequenceA [Just 1, Just 2, Just 3]
  Just [1,2,3]

  >>> sequenceA [Right 1, Right 2, Right 3]
  Right [1,2,3]

  >>> sequenceA $ Just [1,2,3]
  [Just 1, Just 2, Just 3]

  >>> sequenceA [[1,2], [3,4], [5,6]]
  [[1,3,5],[1,3,6],[1,4,5],[1,4,6],[2,3,5],[2,3,6],[2,4,5],[2,4,6]]

  -- Nothing and Left valuies short circuit the structure
  >>> sequenceA [Just 1, Just 2, Just 3, Nothing]
  Nothing

  >>> sequenceA [Right 1, Right 2, Right 3, Left 4]
  Left 4  
  ```

* **`mapM`** is basically a `traverse` restricted to `Monad`

* **`sequence`** is basically a `sequenceA` restricted to `Monad`
