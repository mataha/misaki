# ``misaki``

[![Project Status: WIP – Initial development is in progress, but there has not yet been a stable, usable release suitable for the public.](https://www.repostatus.org/badges/latest/wip.svg)](https://www.repostatus.org/#wip)

Solutions to various programming koans bundled with a mini-framework
capable of executing them against input of user's choice, whether
from a file or a command line.

Totally ~~not~~ named after [Nakahara Misaki][1]
from [Welcome to the N.H.K.][2]

## Installation

Assumes `git` and JDK >= 8 (possibly from [here][3]):

```shell
git clone https://gitgud.io/mataha/misaki
cd misaki
./gradlew build
```

## Sample usage

#### Command-line input

```shell
$ ./run "Advent of Code" "Not Quite Lisp"
```

Input and output:

```
Enter your puzzle input:
()()))())(()))()()(())(())()()(
^D
[-3, 5]
```

where `^D` is `EOF` (`^Z` on Windows)

#### File input

```shell
$ ./run "Advent of Code" "Some Assembly Required" -i "samples/adventofcode/2015/07/input.txt"
```

Output:
```
[3176, 14710]
```

#### File input with time measurement

```shell
$ ./run "Advent of Code" "All in a Single Night" -i "samples/adventofcode/2015/09/input.txt" --measure
```

Output:

```
[141, 736]
That took: 127ms
```

#### Usage with argument files

`misaki`, much like `javac`, supports [argument files][4]:

```shell
$ ./run @"samples/adventofcode/2015/05/argfile"
```

Where `argfile` contains:

```
"Advent of Code" "Doesn't He Have Intern-Elves For This?"
-i "samples/adventofcode/2015/05/input.txt"
```

Output:

```
[258, 53]
```

When in doubt, `./run --help`.

## License

MIT, of course. See [LICENSE](./LICENSE).

[1]: https://anidb.net/character/2809
[2]: https://en.wikipedia.org/wiki/Welcome_to_the_N.H.K.
[3]: https://adoptopenjdk.net/installation.html
[4]: https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html#BHCCFGCD
