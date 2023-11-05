import * as D from "./domain";
import * as F from "./functions";

const userDtoList: readonly D.UserRegistrationDto[] = [
  {
    firstName: "Test1",
    lastName: "McTestFace",
    sex: "M",
    age: 18,
    country: "Belgium",
  },
  {
    firstName: "Alien",
    lastName: "Young",
    sex: "F",
    age: 30,
  } as D.UserRegistrationDto,
  {
    firstName: "Test2",
    lastName: "Young",
    sex: "F",
    age: 15,
    country: "Belgium",
  },
  {
    firstName: "Test3",
    lastName: "Unknown",
    sex: "Man",
    age: 20,
    country: "Belgium",
  },
  {
    firstName: "Test4",
    lastName: "TotalDisaster",
    sex: "Woman",
    age: 200,
    country: "Belgium",
  },
];

const responseList = userDtoList.map(F.flow);
responseList.forEach((userDto) => console.log(userDto));
