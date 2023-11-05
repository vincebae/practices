module BST
  ( BST,
    bstLeft,
    bstRight,
    bstValue,
    empty,
    fromList,
    insert,
    singleton,
    toList,
  )
where

data BST a
  = BST
      { value :: a,
        left :: BST a,
        right :: BST a
      }
  | Empty
  deriving (Eq, Show)

bstValue :: BST a -> Maybe a
bstValue Empty = Nothing
bstValue tree = Just $ value tree

bstLeft :: BST a -> Maybe (BST a)
bstLeft Empty = Nothing
bstLeft tree = Just $ left tree

bstRight :: BST a -> Maybe (BST a)
bstRight Empty = Nothing
bstRight tree = Just $ right tree

empty :: BST a
empty = Empty

singleton :: a -> BST a
singleton x = BST x Empty Empty

fromList :: (Ord a) => [a] -> BST a
fromList = foldr insert empty . reverse

insert :: (Ord a) => a -> BST a -> BST a
insert x Empty = singleton x
insert x (BST y left right)
  | x > y = BST y left (insert x right)
  | otherwise = BST y (insert x left) right

toList :: BST a -> [a]
toList Empty = []
toList (BST x left right) = toList left ++ x : toList right
