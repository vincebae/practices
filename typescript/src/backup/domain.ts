import { iso, Newtype } from "newtype-ts";
import { PositiveInteger } from "newtype-ts/lib/PositiveInteger";
import { NonEmptyArray } from "fp-ts/lib/NonEmptyArray";
import * as E from "fp-ts/lib/Either";
import * as O from "fp-ts/lib/Option";

export type UserRegistrationDto = {
  firstName: string;
  lastName: string;
  age: number;
  sex: string;
  country: string;
};

export type Gender = "M" | "F" | "X";

export type Europe = {
  readonly _type: "Europe";
};
export type NorthAmerica = {
  readonly _type: "NorthAmerica";
};
export type Other = {
  readonly _type: "Other";
};
export type Region = Europe | NorthAmerica | Other;

// export interface FirstName
//     extends Newtype<{ readonly FirstName: unique symbol }, string> {}
// export interface LastName
//     extends Newtype<{ readonly LastName: unique symbol }, string> {}
//
export type FirstName = Newtype<{ readonly FirstName: unique symbol }, string>;
export type LastName = Newtype<{ readonly LastName: unique symbol }, string>;

export const firstNameIso = iso<FirstName>();
export const lastNameIso = iso<LastName>();

export type User = {
  firstName: FirstName;
  lastName: LastName;
  age: PositiveInteger;
  gender: Gender;
  region: Region;
};

export type Validation = (
  e: UserRegistrationDto
) => E.Either<string, UserRegistrationDto>;
export type FieldsNotEmpty = Validation;
export type ValidateAge = Validation;
export type ValidateGender = Validation;

export type ValidationV = (
  e: UserRegistrationDto
) => E.Either<NonEmptyArray<string>, UserRegistrationDto>;
export type FieldsNotEmptyV = ValidationV;
export type ValidateAgeV = ValidationV;
export type ValidateGenderV = ValidationV;

export type CreateUser = (
  f: FirstName,
  l: LastName,
  a: PositiveInteger,
  g: Gender,
  r: Region
) => User;

export type FindRegion = (country: string) => O.Option<Region>;
export type FindGender = (sex: string) => O.Option<Gender>;

export type Response = {
  status: number;
  message: string;
};
