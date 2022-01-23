# DFS Water Jug
Generating all possible states for the three water jug problem

This is a java program that takes user inputs as capacities for three separate water jugs.

There are 3 jugs with capacities A gallons, B gallons and C gallons, there is also an unlimited water suppyly that is used to fill the jugs. Water can be poured from one jug to another, or into the ground. None of the jugs have measuring lines, so the only way to know how much water is in the jug is if it is full or empty.

In this program the start state is [0,0,0] meaning each jug has a volume of zero, and the capacities have been set by user input. The program uses Depth-First-Search to generate the set of all possible states that can be reached from the start state [0,0,0] in one or more moves.
