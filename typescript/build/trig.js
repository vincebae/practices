const range = (start, end) => Array.from({ length: end - start }, (_, k) => k + start);
const createSin = (degree) => ({
    kind: "sin",
    degree,
});
const createCos = (degree) => ({
    kind: "cos",
    degree,
});
const createTan = (degree) => ({
    kind: "tan",
    degree,
});
// Returns terminal angle in [0, 360).
const getTerminalAngle = (angle) => {
    const degree = angle.degree >= 0 ? angle.degree % 360 : (angle.degree % 360) + 360;
    return Object.assign(Object.assign({}, angle), { degree });
};
// Return reference angle in [0, 90].
const getReferenceAngle = (angle) => {
    const modBy180 = getTerminalAngle(angle).degree % 180;
    return Object.assign(Object.assign({}, angle), { degree: modBy180 > 90 ? 180 - modBy180 : modBy180 });
};
const getAnglePosition = (angle) => {
    const { degree } = getTerminalAngle(angle);
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
const sinNegPos = new Set(["Q3", "NegY", "Q4"]);
const cosNegPos = new Set(["Q2", "NegX", "Q3"]);
const tanNegPos = new Set(["Q2", "Q4"]);
const trigNegPosMap = {
    sin: sinNegPos,
    cos: cosNegPos,
    tan: tanNegPos,
};
const sinValues = {
    0: "0",
    30: "1/2",
    45: "√2/2",
    60: "√3/2",
    90: "1",
};
const cosValues = {
    0: "1",
    30: "√3/2",
    45: "√2/2",
    60: "1/2",
    90: "0",
};
const tanValues = {
    0: "0",
    30: "√3/3",
    45: "1",
    60: "√3",
    90: "Inf",
};
const trigValuesMap = {
    sin: sinValues,
    cos: cosValues,
    tan: tanValues,
};
const getSign = (trig) => {
    return trigNegPosMap[trig.kind].has(getAnglePosition(trig)) ? "-" : "";
};
const getTrigValue = (trig) => {
    const reference = getReferenceAngle(trig);
    return getSign(trig) + trigValuesMap[trig.kind][reference.degree];
};
const getQuestionStr = (trig) => {
    return `${trig.kind} ${trig.degree} ° = `;
};
const getAnswerStr = (trig) => {
    const questionStr = getQuestionStr(trig);
    const term = getTerminalAngle(trig);
    const terminalStr = term.degree !== trig.degree ? getQuestionStr(term) : "";
    const valueStr = getTrigValue(trig);
    return questionStr + terminalStr + valueStr;
};
const generateAngles = (min, max) => {
    const baseAngles = [0, 30, 45, 60];
    const minDivBy90 = Math.floor(min / 90);
    const maxDivBy90 = Math.floor(max / 90);
    const temp = range(minDivBy90, maxDivBy90 + 1)
        .map((multi) => baseAngles.map((angle) => angle + 90 * multi))
        .reduce((a1, a2) => [...a1, ...a2], [])
        .filter((angle) => angle >= min && angle <= max)
        .sort((n1, n2) => n1 - n2);
    return temp;
};
const generateTrigs = (min, max) => {
    const generators = [createSin, createCos, createTan];
    return generateAngles(min, max)
        .map((angle) => generators.map((fn) => fn(angle)))
        .reduce((a1, a2) => [...a1, ...a2], []);
};
const arrayShuffle = (array) => {
    if (array.length === 0) {
        return [];
    }
    const copied = [...array];
    for (let index = copied.length - 1; index > 0; index--) {
        const newIndex = Math.floor(Math.random() * (index + 1));
        [copied[index], copied[newIndex]] = [copied[newIndex], copied[index]];
    }
    return copied;
};
const groupByN = (arr, n) => {
    return range(0, Math.ceil(arr.length / n))
        .map((i) => arr.slice(i * n, (i + 1) * n))
        .map((row) => row.join(","));
};
const generatesWorksheet = (min, max, cols, shuffle) => {
    const trigs = (shuffle ? arrayShuffle : (x) => x)(generateTrigs(min, max));
    const questions = groupByN(trigs.map(getQuestionStr), cols).join("\n");
    const answers = groupByN(trigs.map(getAnswerStr), cols).join("\n");
    return `${questions}"\n"${answers}`;
};
const worksheet1 = generatesWorksheet(0, 360, 3, false);
const worksheet2 = generatesWorksheet(0, 360, 3, true);
console.log(worksheet1);
console.log("=".repeat(80));
console.log(worksheet2);
