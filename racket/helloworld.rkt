#!/usr/bin/env racket
#lang racket

(printf "Hello World!\n")

(define (nobake flavor)
  (string-append flavor "jello"))

(define (halfbake flavor)
  (string-append flavor " creme brulee"))

(define (dayofweek day)
  (cond
    [(= day 1) "Monday"]
    [(= day 2) "Tuesday"]
    [(= day 3) "Wednesday"]
    [(= day 4) "Thursday"]
    [(= day 5) "Friday"]
    [(= day 6) "Saturday"]
    [(= day 7) "Sunday"]
    [else "No idea!"]))

(define (dayofweek2 day)
  (case day
    [(1) "Monday"]
    [(2) "Tuesday"]
    [(3) "Wednesday"]
    [(4) "Thursday"]
    [(5) "Friday"]
    [(6) "Saturday"]
    [(7) "Sunday"]
    [else "No idea!"]))

(println (map dayofweek '(0 1 2 3 4 5 6 7 8)))
(println (map dayofweek2 '(0 1 2 3 4 5 6 7 8)))

(define fight
  (let* ([x (random 4)] [o (random 4)] [diff (- x o)] [net (number->string (abs diff))])
    (cond
      [(positive? diff) (string-append "X wins by " net)]
      [(negative? diff) (string-append "O wins by " net)]
      [else "cat's game"])))

(printf (string-append fight "\n"))
