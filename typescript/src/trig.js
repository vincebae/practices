var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
var range = function (start, end) {
    return Array.from({ length: end - start }, function (_, k) { return k + start; });
};
var createSin = function (degree) { return ({
    kind: "sin",
    degree: degree
}); };
var createCos = function (degree) { return ({
    kind: "cos",
    degree: degree
}); };
var createTan = function (degree) { return ({
    kind: "tan",
    degree: degree
}); };
// Returns terminal angle in [0, 360).
var getTerminalAngle = function (angle) {
    var degree = angle.degree >= 0 ? angle.degree % 360 : (angle.degree % 360) + 360;
    return __assign(__assign({}, angle), { degree: degree });
};
// Return reference angle in [0, 90].
var getReferenceAngle = function (angle) {
    var modBy180 = getTerminalAngle(angle).degree % 180;
    return __assign(__assign({}, angle), { degree: modBy180 > 90 ? 180 - modBy180 : modBy180 });
};
var getAnglePosition = function (angle) {
    var degree = getTerminalAngle(angle).degree;
    switch (degree) {
        case 0:
            return "PosX";
        case 90:
            return "PosY";
        case 180:
            return "NegX";
        case 270:
            return "NegY";
        default:
            break;
    }
    if (degree < 90) {
        return "Q1";
    }
    else if (degree > 90 && degree < 180) {
        return "Q2";
    }
    else if (degree > 180 && degree < 270) {
        return "Q3";
    }
    else {
        return "Q4";
    }
};
var sinNegPos = Object.freeze(new Set(["Q3", "NegY", "Q4"]));
var cosNegPos = Object.freeze(new Set(["Q2", "NegX", "Q3"]));
var tanNegPos = Object.freeze(new Set(["Q2", "Q4"]));
var trigNegPosMap = {
    sin: sinNegPos,
    cos: cosNegPos,
    tan: tanNegPos
};
var sinValues = {
    0: "0",
    30: "1/2",
    45: "√2/2",
    60: "√3/2",
    90: "1"
};
var cosValues = {
    0: "1",
    30: "√3/2",
    45: "√2/2",
    60: "1/2",
    90: "0"
};
var tanValues = {
    0: "0",
    30: "√3/3",
    45: "1",
    60: "√3",
    90: "Inf"
};
var trigValuesMap = {
    sin: sinValues,
    cos: cosValues,
    tan: tanValues
};
var getSign = function (trig) {
    return trigNegPosMap[trig.kind].has(getAnglePosition(trig)) ? "-" : "";
};
var getTrigValue = function (trig) {
    var reference = getReferenceAngle(trig);
    return getSign(trig) + trigValuesMap[trig.kind][reference.degree];
};
var getQuestionStr = function (trig) {
    return "".concat(trig.kind, " ").concat(trig.degree, " \u00B0 = ");
};
var getAnswerStr = function (trig) {
    var questionStr = getQuestionStr(trig);
    var term = getTerminalAngle(trig);
    var terminalStr = term.degree !== trig.degree ? getQuestionStr(term) : "";
    var valueStr = getTrigValue(trig);
    return questionStr + terminalStr + valueStr;
};
var generateAngles = function (min, max) {
    var baseAngles = [0, 30, 45, 60];
    var minDivBy90 = Math.floor(min / 90);
    var maxDivBy90 = Math.floor(max / 90);
    var temp = range(minDivBy90, maxDivBy90 + 1)
        .map(function (multi) { return baseAngles.map(function (angle) { return angle + 90 * multi; }); })
        .reduce(function (a1, a2) { return __spreadArray(__spreadArray([], a1, true), a2, true); }, [])
        .filter(function (angle) { return angle >= min && angle <= max; })
        .sort(function (n1, n2) { return n1 - n2; });
    return temp;
};
var generateTrigs = function (min, max) {
    var generators = [createSin, createCos, createTan];
    return generateAngles(min, max)
        .map(function (angle) { return generators.map(function (fn) { return fn(angle); }); })
        .reduce(function (a1, a2) { return __spreadArray(__spreadArray([], a1, true), a2, true); }, []);
};
var arrayShuffle = function (array) {
    var _a;
    if (array.length === 0) {
        return [];
    }
    var copied = __spreadArray([], array, true);
    for (var index = copied.length - 1; index > 0; index--) {
        var newIndex = Math.floor(Math.random() * (index + 1));
        _a = [copied[newIndex], copied[index]], copied[index] = _a[0], copied[newIndex] = _a[1];
    }
    return copied;
};
var groupByN = function (arr, n) {
    return range(0, Math.ceil(arr.length / n))
        .map(function (i) { return arr.slice(i * n, (i + 1) * n); })
        .map(function (row) { return row.join(","); });
};
var generatesWorksheet = function (min, max, cols, shuffle) {
    var trigs = (shuffle ? arrayShuffle : function (x) { return x; })(generateTrigs(min, max));
    var questions = groupByN(trigs.map(getQuestionStr), cols).join("\n");
    var answers = groupByN(trigs.map(getAnswerStr), cols).join("\n");
    return questions + "\n" + answers;
};
var worksheet1 = generatesWorksheet(0, 360, 3, false);
var worksheet2 = generatesWorksheet(0, 360, 3, true);
console.log(worksheet1);
console.log("=".repeat(80));
console.log(worksheet2);
//type: number, integer, float, double, string, char,
//
// statically typed - typescript
// dynamically typed - javascript, python.
