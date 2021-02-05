#!/usr/bin/env gosu

uses gw.lang.Gosu

function require(value: boolean, message: String = "Failed requirement.") {
    if (!value) {
        throw new IllegalArgumentException(message)
    }
}

function usage() {
    print("Usage: ${Gosu.CurrentProgram} YEAR DAY")
    System.exit(1)
}

function main(args: List<String>) {
    var year: int
    var day:  int

    try {
        var parsed = args.map(\arg -> arg.toInt())
        year = parsed[0]; day = parsed[1]
        require(year >= 2015 and day >= 1 and day <= 25)
    } catch (_: Exception) {
        usage()
    }

    print("/** See [https://adventofcode.com/${year}/day/${day}]. */")
}

main(Gosu.RawArgs)
