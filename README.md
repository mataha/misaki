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
git clone https://github.com/mataha/misaki.git
cd misaki
./gradlew build
```

## Sample usage

```shell
$ ./run "Advent of Code" "Some Assembly Required" -i "samples/adventofcode/2015/07/input.txt"
[3176, 14710]
```

[1]: https://anidb.net/character/2809
[2]: https://www.mangaupdates.com/series.html?id=8861
[3]: https://adoptopenjdk.net/installation.html
