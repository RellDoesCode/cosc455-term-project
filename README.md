# COSC455 Project 1: Lexical Analyzer



## Name: Terrell Beasley



## Programming Language



The language that I decided on using for this project is Java version "22.0.2".


## IDE \& Compiler



* IDE Used: Java Eclipse IDE

* Java Compiler: `javac`

* Java Runtime: `java`



## Instructions



### Compiling the Program



Open a command prompt or terminal and navigate to the project directory containing the `.java` files.



Run:



```

javac textParser.java

```



### Execute the Program



```

java textParser <inputfile>.txt

```



Please keep in mind that the input files must exist in the same directory if the above method is used; otherwise, you may copy the path to the input file like so:



```

java textParser path\to\file\<inputfile>.txt

```



---



## Program Usage



### Overview



This program is a lexical analyzer that reads an input file and converts it into a sequence of tokens based on the grammar specified for this project.



---



### Input



The program accepts one command line argument: The name of a text file containing source code OR the path to the text file.



---



### Output



The program outputs each detected token from a specified file. When a token is detected, the lexical analyzer will output the line and column numbers on which the token is found, the lexeme, and the value contained in the token (if applicable).



---



### Special Behavior



* Whitespace is ignored by the program and is used to separate tokens.



* Comments begin with `//` and continue until the end of the line. Comments are completely ignored by the program.



* The program always reads the longest possible valid token as per the maximum munch rule.



---



### Error Handling



If an illegal character is encountered, the program will print an error message containing the illegal character and immediately terminate the program.

