import { iso, Newtype } from "newtype-ts";
import { PositiveInteger } from "newtype-ts/lib/PositiveInteger";
import * as E from "fp-ts/lib/Either";

export type UserRegistrationDto = {
  firstName: string;
  lastName: string;
  age: number;
  sex: string;
  country: string;
};

export type FirstName = Newtype<{ readonly FirstName: unique symbol }, string>;
export type LastName = Newtype<{ readonly LastName: unique symbol }, string>;
export const firstNameIso = iso<FirstName>();
export const lastNameIso = iso<LastName>();

export type Age = PositiveInteger;

export type Gender = "M" | "F" | "X";

export type Europe = "Europe";
export type NorthAmerica = "NorthAmerica";
export type Other = "Other";
export type Region = Europe | NorthAmerica | Other;


export type User = {
  firstName: FirstName;
  lastName: LastName;
  age: Age;
  gender: Gender;
  region: Region;
};

export type Validation = (
  e: UserRegistrationDto
) => E.Either<string, UserRegistrationDto>;

export type Response = {
  status: number;
  message: string;
};
