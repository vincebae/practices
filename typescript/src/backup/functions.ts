import { getSemigroup } from "fp-ts/lib/NonEmptyArray";
import { pipe } from "fp-ts/lib/function";
import { prismPositiveInteger } from "newtype-ts/lib/PositiveInteger";
import { sequenceT } from "fp-ts/lib/Apply";
import * as E from "fp-ts/lib/Either";
import * as O from "fp-ts/lib/Option";
import * as D from "./domain";

const applicativeValidation = E.getValidation(getSemigroup<string>());

const fieldsNotEmpty: D.FieldsNotEmpty = (e) => {
  return e.firstName && e.lastName && e.age && e.sex && e.country
    ? E.right(e)
    : E.left("Not all required fields were filled in.");
};

const validateAge: D.ValidateAge = (e) => {
  return e.age >= 18 && e.age < 150
    ? E.right(e)
    : E.left(`Received an invalid age of ${e.age}`);
};

const validateGender: D.ValidateGender = (e) => {
  return e.sex === "M" || e.sex === "F" || e.sex === "X"
    ? E.right(e)
    : E.left(`Received an invalid sex ${e.sex}`);
};

const fieldsNotEmptyV: D.FieldsNotEmptyV = (e) => {
  return e.firstName && e.lastName && e.age && e.sex && e.country
    ? E.right(e)
    : E.left(["Not all required fields were filled in."]);
};

const validateAgeV: D.ValidateAgeV = (e) => {
  return e.age >= 18 && e.age < 150
    ? E.right(e)
    : E.left([`Received an invalid age of ${e.age}`]);
};

const validateGenderV: D.ValidateGenderV = (e) => {
  return e.sex === "M" || e.sex === "F" || e.sex === "X"
    ? E.right(e)
    : E.left([`Received an invalid sex ${e.sex}`]);
};

const validateDto = (dto: D.UserRegistrationDto) => {
  return pipe(
    E.right(dto),
    E.chain(fieldsNotEmpty),
    E.chain(validateAge),
    E.chain(validateGender)
  );
};

const validateDtoV = (dto: D.UserRegistrationDto) => {
  return pipe(
    dto,
    (e) =>
      sequenceT(applicativeValidation)(
        fieldsNotEmptyV(e),
        validateAgeV(e),
        validateGenderV(e)
      ),
    E.map(([first]) => first)
  );
};

const america: D.NorthAmerica = {
  _type: "NorthAmerica",
};

const europe: D.Europe = {
  _type: "Europe",
};

const other: D.Other = {
  _type: "Other",
};

const countryMappings: Record<string, D.Region> = {
  Belgium: europe,
  USA: america,
  Germany: europe,
  China: other,
};

const createUser: D.CreateUser = (
  firstName,
  lastName,
  age,
  gender,
  region
) => ({
  firstName,
  lastName,
  age,
  gender,
  region,
});

const findRegion: D.FindRegion = (country) => {
  return countryMappings[country] ? O.some(countryMappings[country]) : O.none;
};

const findGender: D.FindGender = (sex) => {
  return sex === "M" || sex === "F" || sex === "X" ? O.some(sex) : O.none;
};

const createdResponse = (message: string): D.Response => ({
  status: 201,
  message,
});

const badRequest = (exception: string): D.Response => ({
  status: 400,
  message: exception,
});

const internalServerError = (): D.Response => ({
  status: 500,
  message: "failed to create",
});

const sequenceForOption = sequenceT(O.option);

const userResponse = (userDto: D.UserRegistrationDto) => {
  return pipe(
    userDto,
    (e) =>
      sequenceForOption(
        O.some(D.firstNameIso.wrap(e.firstName)),
        O.some(D.lastNameIso.wrap(e.lastName)),
        prismPositiveInteger.getOption(e.age),
        findGender(e.sex),
        findRegion(e.country)
      ),
    O.map(([f, l, a, g, c]) => createUser(f, l, a, g, c)),
    O.map((u) => createdResponse(JSON.stringify(u))),
    O.getOrElse(internalServerError)
  );
};

export const flow = (userDto: D.UserRegistrationDto) => {
  return pipe(
    userDto,
    validateDto,
    E.map(userResponse),
    E.getOrElse(badRequest)
  );
};
