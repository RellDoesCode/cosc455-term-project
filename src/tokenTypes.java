/*This class is used to represent each token along with the line it appears on, the column
 *in which the token starts, and the value of the token.*/
public class tokenTypes {
	
	public int line;
	public int col;
	public Token type;
	public String value;
	
	/*Token constructor*/
	public tokenTypes(int line, int col, Token type, String value) {
		this.line = line;
		this.col = col;
		this.type = type;
		this.value = value;
	}
	
	/*Prints each token represented and its associated information.*/
	public String toString() {
		return switch (type) {
			case Token.IDENTIFIER -> line + ":" + col + ":'ID' " + value;
			case Token.INTEGER_LITERAL -> line + ":" + col + ":'NUM' " + value;
			case Token.EOT -> line + ":" + col + ": 'end-of-text'";
			default -> line + ":" + col + ":'" + value + "'";
		};
	}
}
