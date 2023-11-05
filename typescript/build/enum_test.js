var Orientation;
(function (Orientation) {
    Orientation[Orientation["East"] = 0] = "East";
    Orientation[Orientation["West"] = 1] = "West";
    Orientation[Orientation["North"] = 2] = "North";
    Orientation[Orientation["South"] = 3] = "South";
})(Orientation || (Orientation = {}));
// eslint-disable-next-line @typescript-eslint/no-namespace
(function (Orientation) {
    Orientation.size = () => (Object.keys(Orientation).length - 1) / 2;
})(Orientation || (Orientation = {}));
var OrientationString;
(function (OrientationString) {
    OrientationString["East"] = "E";
    OrientationString["West"] = "W";
    OrientationString["North"] = "N";
    OrientationString["South"] = "S";
})(OrientationString || (OrientationString = {}));
// eslint-disable-next-line @typescript-eslint/no-namespace
(function (OrientationString) {
    OrientationString.size = () => Object.keys(OrientationString).length - 1;
})(OrientationString || (OrientationString = {}));
const orientationLength = Orientation.size();
const orientationStringLength = OrientationString.size();
console.log(`Orientation size: ${orientationLength}`);
console.log(`OrientationString size: ${orientationStringLength}`);
var TreeType;
(function (TreeType) {
    TreeType[TreeType["Empty"] = 0] = "Empty";
    TreeType[TreeType["Leaf"] = 1] = "Leaf";
    TreeType[TreeType["Tree"] = 2] = "Tree";
})(TreeType || (TreeType = {}));
const color1 = ["red", "green", "blue"];
const color2 = ["red", "green", "blue"];
console.log(`color1: ${color1}, type: ${typeof color1}`);
console.log(`color2: ${color2}, type: ${typeof color2}`);
const constTuple = [1, 2];
const readonlyTuple = [1, 2];
const asConstTuple = [1, 2];
constTuple[0] = 3;
// readonlyTuple[0] = 3;
// asConstTuple[0] = 3;
console.log(`constTuple: ${constTuple}, type: ${typeof constTuple}`);
console.log(`readonlyTuple: ${readonlyTuple}, type: ${typeof readonlyTuple}`);
console.log(`asConstTuple: ${asConstTuple}, type: ${typeof asConstTuple}`);
function sayHello(name) {
    if (name) {
        console.log(`Hello, ${name.toUpperCase()}!`);
    }
    else {
        console.log("Hello!");
    }
}
sayHello("SB");
sayHello("");
sayHello();
const userId = "sbbae";
const articleId = "abcde";
console.log(`userId: ${userId}, type: ${typeof userId}`);
console.log(`articleId: ${articleId}, type: ${typeof articleId}`);
{
    let add = (a, b, c) => a + b + c;
    const sum = (x, y) => x + y;
    add = sum;
    console.log(add(1, 2, 3));
}
