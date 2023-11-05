const matrix1 = [
    [0, 0, 0, 1, 1, 1],
    [0, 0, 0, 1, 1, 1],
    [0, 0, 0, 1, 1, 1],
    [0, 1, 1, 0, 0, 1],
    [0, 1, 1, 0, 0, 1],
    [0, 0, 0, 0, 0, 0],
];
const matrix = [
    [1, 0, 1],
    [1, 1, 0],
    [0, 1, 1],
];
const knows = (from, to) => {
    return matrix[from][to] == 1;
};
const isCelebrity = (person, n) => {
    let celeb = true;
    for (let i = 0; i < n; i++) {
        if (person !== i && knows(person, i)) {
            celeb = false;
            if (!knows(i, person)) {
                return i;
            }
        }
    }
    return celeb;
};
const findCelebrity = (n) => {
    let candidate = 0;
    while (true) {
        const possible = isCelebrity(candidate, n);
        if (possible === true) {
            return candidate;
        }
        else if (possible === false) {
            return -1;
        }
        else {
            candidate = possible;
        }
    }
};
const celebrity = findCelebrity(matrix.length);
if (celebrity === -1) {
    console.log("No celebrity");
}
else {
    console.log(`Found celebrity: ${celebrity}!`);
}
