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
Enter your puzzle input:
()()))())(()))()()(())(())()()(
^D
[-3, 5]
```

where `^D` is `EOF` (`^Z` on Windows).

#### File input

```shell
$ ./run "Advent of Code" "Some Assembly Required" -i "samples/adventofcode/2015/07/input.txt"
[3176, 14710]
```

#### File input with time measurement

```shell
$ ./run "Advent of Code" "All in a Single Night" -i "samples/adventofcode/2015/09/input.txt" --measure
[141, 736]
That took: 127ms
```

If in doubt, `./run --help`.

## License

MIT, of course. See [LICENSE](./LICENSE).

[1]: https://anidb.net/character/2809
[2]: https://www.mangaupdates.com/series.html?id=8861
[3]: https://adoptopenjdk.net/installation.html
