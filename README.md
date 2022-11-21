# LabSAT-Solver

LabSAT is a solver for reasoning tasks in abstract argumentation frameworks<sup>1</sup>. It utilizes the labelling approach by Caminada as a satisfiability problem (SAT)<sup>2</sup>.

As participant of the ICCMA'15<sup>3</sup> LabSAT solves the following computational tasks:

 * Given an abstract argumentation framework, determine some extension
 * Given an abstract argumentation framework, determine all extensions
 * Given an abstract argumentation framework and some argument, decide whether the given argument is credulously inferred
 * Given an abstract argumentation framework and some argument, decide whether the given argument is skeptically inferred

The above computational tasks are solved with respect to the following standard semantics:

* Complete Semantics
* Preferred Semantics
* Grounded Semantics
* Stable Semantics

The [LabSAT-Solver Paper](LabSATSolverPaper.pdf) contains a detailed description of the technique used.

# Installation

At least java 7, maven, gcc and make are required. The environment variable $JAVA_HOME has to be set accordingly.

    chmod 755 build
    ./build

# Usage
	solver --formats
Prints the supported formats of the solver.<br/>

	solver --problems
Prints the supported computational problems.<br/>

	solver -p <problem> -f <file> -fo <fileformat> [-a <argument of search>]
Starts the computation.<br/>

# Footnotes

<sup>1</sup> Dung, P. M., On the acceptability of arguments and its fundamental role in nonmonotonic reasoning, logic programming and n-person games, in: Artificial Intelligence, vol. 77, pp. 321–357 (1995) <br/>
<sup>2</sup> Cerutti, F., Dunne P., Giacomin, M., Vallati, M.: A SAT-based Approach for Computing Extensions in Abstract Argumentation, in: Black, E., Modgil, S., Oren, N.(eds.), Theory and Applications of Formal Argumentation – Second International Workshop (TAFA 2013), Lecture Notes in Computer Science, vol. 8306, pp. 176–193. Springer, Berlin (2014)<br/>
<sup>3</sup> http://argumentationcompetition.org/2015<br/>
