import {
  getOrElse,
  chain,
  map,
  none,
  some,
  fromNullable,
} from "fp-ts/lib/Option";

const upperCaseIt = (value: string) => value.toUpperCase();
const optionWithAString = some("a value");
const optionEmpty = none;
const upperCased = map(upperCaseIt)(optionWithAString);
const upperCasedEmpty = map(upperCaseIt)(optionEmpty);

console.log(getOrElse(() => "no value present")(upperCased));
console.log(getOrElse(() => "no value present")(upperCasedEmpty));

const arr = ["a", "b", undefined, "d", "e"];
const arrUpper = arr
  .map(fromNullable)
  .map(map(upperCaseIt))
  .map(getOrElse(() => "undefined"));
console.log(arrUpper);

const upperCaseSam = (name: string) =>
  name === "Sam" ? some(name.toUpperCase()) : none;
const optionWithNameSam = some("Sam");
const optionWithNameSmith = some("Smith");

const upperCasedName = chain(upperCaseSam)(optionWithNameSam);
const noneUpperCasedName = chain(upperCaseSam)(optionWithNameSmith);

console.log(getOrElse(() => "no value present")(upperCasedName));
console.log(getOrElse(() => "no value present")(noneUpperCasedName));

const arr2 = ['a', 'b', 'c', 'd', 'e']

arr2.forEach(n => console.log(n));
arr2.forEach(console.log);
arr2.forEach((n, i) => console.log(`index: ${i}, data: ${n}`));
