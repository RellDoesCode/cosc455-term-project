/*This enum contains all of the valid tokens that belong to our language*/
public enum Token {
	
	/*Keywords and Other Terminal Symbols*/
	PROGRAM, COLON, DOT, BOOL, INT, COMMA, SEMICOLON, IF, THEN, ELSE, END, WHILE, DO,
	LPAREN, RPAREN, UNDERSCORE,
	/*Identifiers and Literals*/
	IDENTIFIER, FALSE, TRUE, INTEGER_LITERAL,
	/*Additive Operators*/
	PLUS, MINUS, OR,
	/*Multiplicative Operators*/
	MULTIPLY, DIVIDE, MOD, AND,
	/*Unary Operators (includes MINUS)*/
	NOT, 
	/*Assignment Operator :=*/
	ASSIGN,
	/*Relational Operators*/
	LT, LE, EQ, NE, GE, GT,
	/*End-of-Text*/
	EOT
}
