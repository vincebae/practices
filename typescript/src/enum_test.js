var Orientation;
(function (Orientation) {
    Orientation[Orientation["East"] = 0] = "East";
    Orientation[Orientation["West"] = 1] = "West";
    Orientation[Orientation["North"] = 2] = "North";
    Orientation[Orientation["South"] = 3] = "South";
})(Orientation || (Orientation = {}));
// eslint-disable-next-line @typescript-eslint/no-namespace
(function (Orientation) {
    Orientation.size = function () { return (Object.keys(Orientation).length - 1) / 2; };
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
    OrientationString.size = function () { return Object.keys(OrientationString).length - 1; };
})(OrientationString || (OrientationString = {}));
var orientationLength = Orientation.size();
var orientationStringLength = OrientationString.size();
console.log("Orientation size: ".concat(orientationLength));
console.log("OrientationString size: ".concat(orientationStringLength));
