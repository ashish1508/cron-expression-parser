# Cron Expression Parser
## Description
 This is a command line utility that takes cron string as input and then parses and expands each field within the cron string
 The cron string should adhere to the standard cron format, involving five time-related fields (minute, hour, day of the month, month, and day of the week), alongside the associated command
#### Limitation : This utility does not handle timestrings such as @yearly 
## How to build and run ?
#### Build the utility using following command 
```
./gradlew build
```
#### Run the utility using following command 
```
java -jar ./build/libs/cron-expression-parser-1.0.0.jar "*/15 0 1,15 * 1-5 /usr/bin/find"  
```
 In the above command, input cron string is  ```"*/15 0 1,15 * 1-5 /usr/bin/find"```, replace this with the cron string you want to run this utility with

## Sample Input / Output
#### Input : ```"*/15 0 1,15 * 1-5 /usr/bin/find"```
#### Output : 
```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```
## Tests
 The test/ directory contains a collection of tests. Although these tests do not cover every possible scenario, they encompass different parser scenarios and guarantee the expected output for the provided expressions.
