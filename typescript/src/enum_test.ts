enum Orientation {
  East,
  West,
  North,
  South,
}
// eslint-disable-next-line @typescript-eslint/no-namespace
namespace Orientation {
  export const size = () => (Object.keys(Orientation).length - 1) / 2;
}

enum OrientationString {
  East = "E",
  West = "W",
  North = "N",
  South = "S",
}
// eslint-disable-next-line @typescript-eslint/no-namespace
namespace OrientationString {
  export const size = () => Object.keys(OrientationString).length - 1;
}

const orientationLength = Orientation.size();
const orientationStringLength = OrientationString.size();

console.log(`Orientation size: ${orientationLength}`);
console.log(`OrientationString size: ${orientationStringLength}`);

enum TreeType {
  Empty,
  Leaf,
  Tree,
}

type Tree =
  | { type: TreeType.Empty }
  | { type: TreeType.Leaf; value: number }
  | { type: TreeType.Tree; value: number; left: Tree; right: Tree };

const color1 = ["red", "green", "blue"] as const;
const color2: readonly ["red", "green", "blue"] = ["red", "green", "blue"];

console.log(`color1: ${color1}, type: ${typeof color1}`);
console.log(`color2: ${color2}, type: ${typeof color2}`);

const constTuple: [number, number] = [1, 2];
const readonlyTuple: readonly [number, number] = [1, 2];
const asConstTuple = [1, 2] as const;

constTuple[0] = 3;
// readonlyTuple[0] = 3;
// asConstTuple[0] = 3;

console.log(`constTuple: ${constTuple}, type: ${typeof constTuple}`);
console.log(`readonlyTuple: ${readonlyTuple}, type: ${typeof readonlyTuple}`);
console.log(`asConstTuple: ${asConstTuple}, type: ${typeof asConstTuple}`);

function sayHello(name?: string) {
  if (name) {
    console.log(`Hello, ${name.toUpperCase()}!`);
  } else {
    console.log("Hello!");
  }
}

sayHello("SB");
sayHello("");
sayHello();

type Nominal<T, K extends string> = T & { __brand: K };

type UserId = Nominal<string, "UserId">;
type ArticleId = Nominal<string, "ArticleId">;

const userId: UserId = "sbbae" as UserId;
const articleId: ArticleId = "abcde" as ArticleId;

console.log(`userId: ${userId}, type: ${typeof userId}`);
console.log(`articleId: ${articleId}, type: ${typeof articleId}`);

{
  let add = (a: number, b: number, c: number): number => a + b + c;
  const sum = (x: number, y: number): number => x + y;
  add = sum;
  console.log(add(1, 2, 3));
}
