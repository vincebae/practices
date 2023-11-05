"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const Option_1 = require("fp-ts/lib/Option");
const upperCaseIt = (value) => value.toUpperCase();
const optionWithAString = (0, Option_1.some)("a value");
const optionEmpty = Option_1.none;
const upperCased = (0, Option_1.map)(upperCaseIt)(optionWithAString);
const upperCasedEmpty = (0, Option_1.map)(upperCaseIt)(optionEmpty);
console.log((0, Option_1.getOrElse)(() => "no value present")(upperCased));
console.log((0, Option_1.getOrElse)(() => "no value present")(upperCasedEmpty));
const arr = ["a", "b", undefined, "d", "e"];
const arrUpper = arr
    .map(Option_1.fromNullable)
    .map((0, Option_1.map)(upperCaseIt))
    .map((0, Option_1.getOrElse)(() => "undefined"));
console.log(arrUpper);
const upperCaseSam = (name) => name === "Sam" ? (0, Option_1.some)(name.toUpperCase()) : Option_1.none;
const optionWithNameSam = (0, Option_1.some)("Sam");
const optionWithNameSmith = (0, Option_1.some)("Smith");
const upperCasedName = (0, Option_1.chain)(upperCaseSam)(optionWithNameSam);
const noneUpperCasedName = (0, Option_1.chain)(upperCaseSam)(optionWithNameSmith);
console.log((0, Option_1.getOrElse)(() => "no value present")(upperCasedName));
console.log((0, Option_1.getOrElse)(() => "no value present")(noneUpperCasedName));
const arr2 = ['a', 'b', 'c', 'd', 'e'];
arr2.forEach(n => console.log(n));
arr2.forEach(console.log);
arr2.forEach((n, i) => console.log(`index: ${i}, data: ${n}`));
