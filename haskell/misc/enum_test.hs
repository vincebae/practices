data Bearing = North | East | South | West
  deriving (Enum, Eq, Ord, Show)

instance Enum Bearing where
  succ :: Bearing -> Bearing
  succ West = North
  pred North = West
