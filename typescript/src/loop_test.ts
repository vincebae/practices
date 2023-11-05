

function sumWithLoop(start: number, end: number): number {
  let sum = 0;
  for (let i = start; i <= end; i++) {
    sum = sum + i;
  }
  return sum;
}

function sumWithRecur(start: number, end: number): number {
  if (start >= end) {
    return start;
  }
  return start + sumWithRecur(start + 1, end);
}

console.log(sumWithLoop(1, 10));
console.log(sumWithRecur(1, 10));
