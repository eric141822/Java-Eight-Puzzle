# Description

This is an 8-puzzle solver written in java. Utilizing A-star or IDDFS to find an optimal solution.

For A-star, the heuristics functions used are **Misplaced Tiles** and **Manhattan Distance**.

## How to input

Enter 9 integers seperated by spaces, from 0 to 8, inclusive and distinct. The 0 represents the empty space of the puzzle.

The input is of row-major order. E.g. an input of 1 0 2 3 4 5 6 7 8 will be interpreted as:

**1 0 2**

**3 4 5**

**6 7 8**
<br/>
The solver will try to find an optimal path to make the puzzle become:

**0 1 2**

**3 4 5**

**6 7 8**
<br/>
Test on any IDE you prefer.
