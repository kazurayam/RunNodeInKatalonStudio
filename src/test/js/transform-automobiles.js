"use strict";

const fs = require("fs");
const jsont = require("json-transforms");

//const json = { ... };
// print process.argv
process.argv.forEach(function (val, index, array) {
  console.log(index + ": " + val);
});
const inputFile = process.argv[2];
const outputFile = process.argv[3];

// read the input JSON
const json =
  JSON.parse(fs.readFileSync(inputFile, { encoding: "utf8", flag: "r" }));

const rules = [
  jsont.pathRule('.automobiles{.maker === "Honda"}', (d) => ({
    Honda: d.runner(),
  })),
  jsont.pathRule(".{.maker}", (d) => ({
    model: d.match.model,
    year: d.match.year,
  })),
  jsont.identity,
];

const transformed = jsont.transform(json, rules);

// write the transformatio result into file
fs.writeFileSync(outputFile, JSON.stringify(transformed, undefined, 2));
