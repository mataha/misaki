## Runtime

 * Naming conventions for interfaces
 * Input parsing - switch to `readText()` (resource)
   * Strip newlines while we're at it?
 * Create a service/repository of solutions to puzzles
   * That repo will lazily scan classpath for Puzzle annotations (auto-discovery)
   * Repository for access to solutions, Service for processing?
     * Given the above, might as well use DI (Kodein?)
     * What should be the criteria for search? Is a name enough? Maybe more?
 * Some basic parsing of input would be nice
   * Input file, if any (directory based on project root)
   * Output file, if any
   * Time measurement
   * Timeout?
   * Link to a puzzle?
   * Help (man format?)
 * Resource access

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
 * Internalize functions intended for testing (`@VisibleForTesting` not available)
 
 ## Scripts
 
 * Batch script that can execute `installDist` equivalent **from root directory**
   * Or modify the one created with `installDist`
 * Bash script (don't have to run through hoops with this one)
 
 ## README
 
 * How to actually run this
 * Some examples while we're at it
