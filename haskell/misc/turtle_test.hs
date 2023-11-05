#!/usr/bin/env runghc
-- stack --resolver lts-21.11 script

{-# LANGUAGE OverloadedStrings #-}

import Turtle

line1 = "Hello, world!"
line2 = "Hello, Vince!"
line3 = "Hello, everyone!"

main :: IO ()
main = do
  echo line1
  print line2
  putStrLn line3
  dir <- pwd
  putStrLn dir
  time <- datefile dir
  print time
  sh (do
      processes <- inshell "ps" empty
      liftIO (print processes))
