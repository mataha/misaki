## Runtime

 * Some basic parsing of input would be nice
   * Timeout?
   * Link to a puzzle?
   * Help (man format?)

## Tests

 * Figure out how to test AoC2015.04 (message digests)
   * Some sample test cases (not necessarily the best):
      
     | input   | `first()` | `second()` |
     |---------|-----------|------------|
     | abcdef  | 609043    | 6742839    |
     | pqrstuv | 1048970   | 5714438    |
    
   * Or find a case that executes in a relatively short time

 * Figure out how and where to keep test inputs (code, csv, something else?)
 * Platform setup: is concurrent + same thread fine?
 * Argument providers
 * Dedicated packages
 * Naming
