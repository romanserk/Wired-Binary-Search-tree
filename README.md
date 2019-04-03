# Wired-Binary-Search-tree

This project was implemented as part of data structures course.

<strong> Please check out run examples folder to see examples of using. </strong>

The project's target is to implement binary search tree that every node holds number, and there is no nodes that points to null.
Every node that have Left[z] = null then Left[z] must have TREE-PREDECESSOR(z) value.
Every node that have Right[z] = null the Right[z] must have TREE-SUCCESSOR(z) value.

The implemantation of the tree must have the functions:

* First group of functions: O(h) running time as the h represents the tree height.<br>
Insert , Delete , Search , Return node successor , return node predecessor , return trees maximum , return trees minimum.
* Second group of functions: O(n) running time as the n represents the tree nodes number.
Tree Traversals: Inorder , Postorder , Preorder.
The traversals function must print the nodes value in related order.
* Third group of functions: in O(1).
Return the median of the tree.

The values can be added to the tree by File or StdIn.

Addition instructions:
* You must not use the fields to distinguish whether the left or right field is a wire or a normal one.
* There may be multiple instances of each key. Each key insertion action You will create a new node. Do not group all instances of a key in a single node.
* The keys are integers (positive and negative).
* The program will receive data from standard input (stdin). The input will come from the keyboard or from a text file (But not from both sources at the same time). The program should not distinguish between the two sources.
* Each line in the input contains one command to execute, a variety of operations on the BST.
* It is safe to assume that the input is correct. There is no need to check input errors.
* An input line is assumed to be a maximum length of 80 characters.
* The program should ignore empty lines in the input (lines with white characters only).
* The program reads from line to line, performs the command, and prints the result to the output.
* Each line of input must be printed out of the program exactly as it was read, before handling it. This way, the input will appear on the output screen even when it comes from a file.
* Note: The pred, succ, delete commands appear in the input with a key operand, not as we learned in the course that the operand is pointing to the node. This is a constraint, since it is meaningless to move a pointer in the input. You may assume that the key is in the tree (no need to check it). If there are several instances of the key, the command refers to one of the occurrences (no matter which).

![EXAMPLE](https://raw.githubusercontent.com/romanserk/Wired-Binary-Search-tree/master/run%20examples/first%20run%20upper%20part.png)
