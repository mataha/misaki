# ``misaki``

[![Project Status: WIP – Initial development is in progress, but there has not yet been a stable, usable release suitable for the public.](https://www.repostatus.org/badges/latest/wip.svg)](https://www.repostatus.org/#wip)

Solutions to various programming koans bundled with a mini-framework
capable of executing them against input of user's choice - whether
from a command line, a file, or a URL.

Totally ~~not~~ named after [Nakahara Misaki][1]
from [Welcome to the N.H.K.][2]

## Installation

Assumes `git` and JDK >= 8 (possibly from [here][3]):

```shell
git clone https://gitgud.io/mataha/misaki  # or wherever it's hosted right now
cd misaki
./gradlew build
```

## Manual

Command-line help page is available under `./run --help`.

## Sample usage

#### Command-line input

By default, `misaki` reads data from standard input:

```shell
$ ./run "Advent of Code" "Not Quite Lisp"
```

```
Enter your puzzle input:
()()))())(()))()()(())(())()()(
^D
[-3, 5]
```

where `^D` is `EOF` (`^Z` on Windows)

#### File input

```shell
$ ./run "Advent of Code" "Some Assembly Required" \
> -i "samples/adventofcode/2015/07/input.txt"
```

```
[3176, 14710]
```

#### File input with time measurement

```shell
$ ./run "Advent of Code" "All in a Single Night" \
> -i "samples/adventofcode/2015/09/input.txt" --measure
```

```
[141, 736]
That took: 127ms
```

#### URL input

Input can also be fetched from a URL:

```shell
$ ./run "Advent of Code" "Aunt Sue" \
> --url "https://adventofcode.com/2015/day/16/input"
```

However, this won't work when obtaining input directly
from [Advent of Code][4]:

```
Error: ClientRequestException: Client request(https://adventofcode.com/2015/day/16/input) invalid: 400 Bad Request
Aborted!
```

In that case, a session token is required. `misaki` allows multiple ways
of setting it:

 * As a command-line option:
    ```shell
    $ ./run "Advent of Code" "Aunt Sue" \
    > --url "https://adventofcode.com/2015/day/16/input" \
    > --token 57e8444ca2478daf7c97a1ffaba582ab224...
    ```

 * As an environment variable:
    ```shell
    $ export MISAKI_APP_TOKEN=57e8444ca2478daf7c97a1ffaba582ab224...
    $ ./run "Advent of Code" "Aunt Sue" \
    > --url "https://adventofcode.com/2015/day/16/input"
    ```

 * From an [environment variable file][5]:
    ```shell
    $ echo MISAKI_APP_TOKEN=57e8444ca2478daf7c97a1ffaba582ab224... > .env
    $ ./run "Advent of Code" "Aunt Sue" \
    > --url "https://adventofcode.com/2015/day/16/input"
    ```

Sample output:

```
[373, 260]
```

#### Usage with argument files

`misaki`, much like `javac`, supports [argument files][6]:

```shell
$ ./run @"samples/adventofcode/2015/05/argfile"
```

```
[258, 53]
```

Where `argfile` contains:

```
"Advent of Code" "Doesn't He Have Intern-Elves For This?"
-i "samples/adventofcode/2015/05/input.txt"
```

## License

MIT. See [LICENSE](./LICENSE).

[1]: https://anidb.net/character/2809
[2]: https://en.wikipedia.org/wiki/Welcome_to_the_N.H.K.
[3]: https://adoptopenjdk.net/installation.html
[4]: https://adventofcode.com/
[5]: https://docs.docker.com/compose/env-file/
[6]: https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html#BHCCFGCD
