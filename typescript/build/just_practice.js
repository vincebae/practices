console.log("Hello World");
function showResult(status, score) {
    const msg = status === "SUCCESS"
        ? score === 5
            ? "Great, you got the highest possible score"
            : `Nice, you got ${score}`
        : "Sorry you failed";
    console.log(msg);
}
showResult("SUCCESS", 5);
showResult("FAILED", 5);
const withInnerFunction = (outer) => (inner) => console.log(`Outer function received ${outer}, inner ${inner}`);
const returnedFunction = withInnerFunction("a");
returnedFunction("b");
const exampleArray = [10, 20, 30];
function sum(arr) {
    if (arr.length === 0) {
        console.log("Reached terminal condition");
        return 0;
    }
    const [head, ...tail] = arr;
    console.log(`Adding ${head} to the sum`);
    return head + sum(tail);
}
console.log(sum(exampleArray));
const sum2 = (arr) => {
    const sum_internal = (acc, arr) => {
        if (arr.length === 0) {
            return acc;
        }
        const [head, ...tail] = arr;
        return sum_internal(head + acc, tail);
    };
    return sum_internal(0, arr);
};
console.log(sum2(exampleArray));
const add = (x, y) => x + y;
const sum3 = (arr) => arr.reduce(add, 0);
console.log(sum3(exampleArray));
const sum4 = (arr) => arr.reduceRight(add, 0);
console.log(sum4(exampleArray));
const addCurr = (x) => (y) => x + y;
const addOne = addCurr(1);
const addTwo = addCurr(2);
const multiCurr = (x) => (y) => x * y;
const multiTwo = multiCurr(2);
const multiThree = multiCurr(3);
const toStr = (n) => n.toString();
// const compose =
//   (...fns) =>
//   (...args) =>
//     fns.reduceRight((res, fn) => [fn.call(...res)], args)[0];
// const compose = <R>(fn1: (a: R) => R, ...fns: Array<(a: R) => R>) =>
//   fns.reduce((prevFn, nextFn) => value => prevFn(nextFn(value)), fn1);
// type ArityOneFn = (arg: any) => any;
// type PickLastInTuple<T extends any[]> = T extends [
//   ...rest: infer U,
//   argn: infer L
// ]
//   ? L
//   : never;
// type FirstFnParameterType<T extends any[]> = Parameters<
//   PickLastInTuple<T>
// >[any];
// type LastFnReturnType<T extends any[]> = ReturnType<T[0]>;
//
// const compose =
//   <T extends ArityOneFn[]>(...fns: T) =>
//   (p: FirstFnParameterType<T>): LastFnReturnType<T> =>
//     fns.reduceRight((acc: any, cur: any) => cur(acc), p);
//
// const composeResult = compose(toStr, multiThree, addOne, multiTwo, addTwo)(1);
//
// const regularResult = multiThree(addOne(multiTwo(addTwo(1))));
//
// console.log(`compose: ${composeResult}, regular: ${regularResult}`);
