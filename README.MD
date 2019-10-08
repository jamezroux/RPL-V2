# Rubik's Programming Language

> Programming language based on Rubik's cubes that operates on a 2D plane with 3D storage. Currently based on a 2x2

# RPL Opcodes
> These are 3 character control codes that let you do things

## Stack Functions

 | OPCODE | Definition                     | Usage           |
 | ------ | ------------------------------ | --------------- |
 | PUS    | Push defined value to stack    | PUS 000000000   |
 | POP    | Pop top value from stack       | POP             |
 | POF    | Pop top value, move forward    | POF             |
 | POB    | Pop top value, move backward   | POB             |
 | POL    | Pop top value, move left       | POL             |
 | POR    | Pop top value, move right      | POR             |

## Print Functions

 | OPCODE | Definition                           | Usage |
 | ------ | ------------------------------------ | ----- |
 | PRN    | Print top value of stack as ASCII    | PRN   |
 | PRB    | Print top value of stack as Binary   | PRB   |
 | PRD    | Print top value of stack as Decimal  | PRD   |

## General Functions

 | OPCODE | Definition                                      | Usage       |
 | ------ | ----------------------------------------------- | ----------- |
 | EXT    | Immediately exit the program                    | EXT         |
 | CID    | Check if there's a value on top of the stack    | CID         |
 | JUT    | Jump to defined line if false, continue if true | JUT 000 CID |
 | RET    | Return to last JUT                              | RET         |

## Math
 - Addition = ADD [000] [000]
 - Subtraction = SUB [000] [000]
 - Multiplication = MUL [000] [000]
 - Division = DIV [000] [000]
 - Negate top value of current cell = NEG

## Pointer
 - Pointer Move Forward = OPF
 - Pointer Move Backward = OPB
 - Pointer Move Left = OPL
 - Pointer Move Right = OPR

## Rubik's Notation
 - CW = Clockwise
 - CCW = Counterclockwise

 - Move Left Face CW = MOV L
 - Move Right Face CW = MOV R
 - Move Top CW = MOV U
 - Move Bottom CW = MOV D
 - Move Face CW = MOV F
 - Move Back CW = MOV B

 - Move Left Face CCW = MOV LN
 - Move Right Face CCW = MOV RN
 - Move Top CCW = MOV UN
 - Move Bottom CCW = MOV DN
 - Move Face CCW = MOVE FN
 - Move Back CCW = MOV BN

# Interpreter / Language Rules

[line number] [operator] [operator arguments]

 - Each line to be executed needs a line number
 - Any line without a line number is interpreted as a comment
 - All data values are represented as 9 character long binary strings with the first number reserved as negate
 - All line redirects are 3 digit long integers
 - Each cell contains a stack, each stack can contain 64 values

# Examples

## Math
The four cells are represented by binary numbers 0-3 in a three digit binary number. The first digit is reserved as a negate, 0 for the value as is or 1 to negate the value.

 - ADD 010 100 (this will add cell 1 to the negative of cell 0)
 - SUB 010 100 (this will subtract the negative of cell 0 by cell 1)
 - MUL 010 100 (this will multiply cell 1 by the negative of cell 0)
 - DIV 010 100 (this will divide the negative of cell 0 by cell 1)

# Code Examples

## Hello World
```
VER 1.0
011 PUS 001000100 // D
010 PUS 001001100 // L
009 PUS 001010010 // R
008 PUS 001001111 // O
007 PUS 001010111 // W
006 PUS 000100000 // Space
005 PUS 001001111 // O
004 PUS 001001100 // L
003 PUS 001001100 // L
002 PUS 001000110 // E
001 PUS 001010000 // H

This function checks if theres data on the current stack,
prints the top value as ascii if there is any and skips
the loop if the stack is empty. Easy way to print prestored
variables

012 JUT 015 CID
013 PRN
014 RET
EXT
```
