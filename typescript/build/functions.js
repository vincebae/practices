"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.flow = void 0;
const function_1 = require("fp-ts/lib/function");
const PositiveInteger_1 = require("newtype-ts/lib/PositiveInteger");
const Apply_1 = require("fp-ts/lib/Apply");
const E = require("fp-ts/lib/Either");
const O = require("fp-ts/lib/Option");
const D = require("./domain");
const fieldsNotEmpty = (e) => {
    return e.firstName && e.lastName && e.age && e.sex && e.country
        ? E.right(e)
        : E.left("Not all required fields were filled in.");
};
const validateAge = (e) => {
    return e.age >= 18 && e.age < 150
        ? E.right(e)
        : E.left(`Received an invalid age of ${e.age}`);
};
const validateGender = (e) => {
    return e.sex === "M" || e.sex === "F" || e.sex === "X"
        ? E.right(e)
        : E.left(`Received an invalid sex ${e.sex}`);
};
const validateDto = (dto) => {
    return (0, function_1.pipe)(E.right(dto), E.chain(fieldsNotEmpty), E.chain(validateAge), E.chain(validateGender));
};
const countryMappings = {
    Belgium: "Europe" /* RegionEnum.EUROPE */,
    USA: "NorthAmerica" /* RegionEnum.NORTH_AMERICA */,
    Germany: "Europe" /* RegionEnum.EUROPE */,
    China: "Other" /* RegionEnum.OTHER */,
};
const createUser = (firstName, lastName, age, gender, region) => ({
    firstName,
    lastName,
    age,
    gender,
    region,
});
const findRegion = (country) => {
    return countryMappings[country] ? O.some(countryMappings[country]) : O.none;
};
const findGender = (sex) => {
    return sex === "M" || sex === "F" || sex === "X" ? O.some(sex) : O.none;
};
const createdResponse = (message) => ({
    status: 201,
    message,
});
const badRequest = (exception) => ({
    status: 400,
    message: exception,
});
const internalServerError = () => ({
    status: 500,
    message: "failed to create",
});
const sequenceForOption = (0, Apply_1.sequenceT)(O.option);
const userResponse = (userDto) => {
    return (0, function_1.pipe)(userDto, (e) => sequenceForOption(O.some(D.firstNameIso.wrap(e.firstName)), O.some(D.lastNameIso.wrap(e.lastName)), PositiveInteger_1.prismPositiveInteger.getOption(e.age), findGender(e.sex), findRegion(e.country)), O.map(([f, l, a, g, c]) => createUser(f, l, a, g, c)), O.map((u) => createdResponse(JSON.stringify(u))), O.getOrElse(internalServerError));
};
const flow = (userDto) => {
    return (0, function_1.pipe)(userDto, validateDto, E.map(userResponse), E.getOrElse(badRequest));
};
exports.flow = flow;
