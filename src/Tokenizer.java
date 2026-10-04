import java.util.HashMap;
import java.util.Map;

public class Tokenizer {

	private String input;
	private char current;
	private int pos = 0;
	private int line = 1;
	private int col = 1;
	private tokenTypes currentToken;
	
	/*This Hash Table will be used to lookup the keyword terminal symbols in our language*/
	private static final Map<String, Token> keywords = new HashMap<>();
	
	static {
		keywords.put("program", Token.PROGRAM);
		keywords.put("if", Token.IF);
		keywords.put("then", Token.THEN);
		keywords.put("else", Token.ELSE);
		keywords.put("end", Token.END);
		keywords.put("while", Token.WHILE);
		keywords.put("do", Token.DO);
		keywords.put("print", Token.PRINT);
		keywords.put("int", Token.INT);
		keywords.put("bool", Token.BOOL);
		keywords.put("true", Token.TRUE);
		keywords.put("false", Token.FALSE);
		keywords.put("or", Token.OR);
		keywords.put("and", Token.AND);
		keywords.put("not", Token.NOT);
		keywords.put("mod", Token.MOD);
	}
	
	/*Constructor method for the tokenizer*/
	public Tokenizer (String input) {
		this.input = input;
		this.current = input.length() > 0 ? input.charAt(0) : '\0';
	}
	
	/*The advance method is used to track the current line and col that each token is found on.
	 *When a new line is reached, the col count is reset to 1.*/
	private void advance () {
		if (current == '\n') {
			line++;
			col = 1;
		} else {
			col++;
		}
		
		pos++;
		current = pos < input.length() ? input.charAt(pos) : '\0';
	}
	
	/*The lookahead method is used to look at the next symbol without consuming it.*/
	private char lookahead() {
		return (pos + 1 < input.length()) ? input.charAt(pos + 1) : '\0';
	}
	
	/*This method skips over any white space within the input file.*/
	private void skipWhitespace() {
        while (Character.isWhitespace(current)) {
            advance();
        }
    }
	
	/*This method skips over the line starting from where a double forward slash (//) is used.*/
	private void skipComment() {
        while (current != '\n' && current != '\0') {
            advance();
        }
    }
	
	/*This method processes identifiers and keywords.*/
	private tokenTypes identifier () {
		int startLine = line;
		int startCol = col;
		
		StringBuilder sb = new StringBuilder();
		
		/*While no terminal symbol has been detected, the next character, digit, or _  is appended
		 *to the identifier.*/
		while (Character.isLetterOrDigit(current) || current == '_') {
			sb.append(current);
			advance();
		}
		
		String detectedWord = sb.toString();
		
		/*If a keyword from the hash table is detected, then it returns that keyword.*/
		if(keywords.containsKey(detectedWord)) {
			return new tokenTypes(startLine, startCol, keywords.get(detectedWord), detectedWord);
		}
		
		return new tokenTypes(startLine, startCol, Token.IDENTIFIER, detectedWord);
	}
	
	/*This method processes integer literals.*/
	private tokenTypes integerLit() {
		int startLine = line;
		int startCol = col;
		
		StringBuilder sb = new StringBuilder();
		
		/*While no terminal symbol is detected, the next digit is appended to the integer literal.*/
		while(Character.isDigit(current)) {
			sb.append(current);
			advance();
		}
		
		return new tokenTypes(startLine, startCol, Token.INTEGER_LITERAL, sb.toString());
	}
	
	/*This method scans the next token from the given input.*/
	public tokenTypes scanNext() {
		while (current != '\0') {

            /*If whitespace is detected, the program skips over it.*/
            if (Character.isWhitespace(current)) {
                skipWhitespace();
                continue;
            }

            /*If the program detects a '/', it looks ahead for another '/'. If it is found, then it
             *is detected as a comment and the commented line of code is skipped.*/
            if (current == '/' && lookahead() == '/') {
                advance();
                advance();
                skipComment();
                continue;
            }

            /*If a character is detected, the program registers it as a possible identifier or 
             *keyword.*/
            if (Character.isLetter(current)) {
                return identifier();
            }

            /*If a number is detected with no prior characters, the program registers it as an 
             *integer literal.*/
            if (Character.isDigit(current)) {
                return integerLit();
            }

            /*The following set of if statements look ahead of the current token to ensure
              that all legal operators with multiple characters can be read and accounted for.*/
            if (current == ':' && lookahead() == '=') {
                int startLine = line;
                int startcol = col;
                advance(); 
                advance();
                return new tokenTypes(startLine, startcol, Token.ASSIGN, ":=");
            }

            if (current == '=' && lookahead() == '<') {
                int startLine = line;
                int startcol = col;
                advance(); 
                advance();
                return new tokenTypes(startLine, startcol, Token.LE, "=<");
            }

            if (current == '!' && lookahead() == '=') {
                int startLine = line;
                int startcol = col;
                advance(); 
                advance();
                return new tokenTypes(startLine, startcol, Token.NE, "!=");
            }

            if (current == '>' && lookahead() == '=') {
                int startLine = line;
                int startcol = col;
                advance(); 
                advance();
                return new tokenTypes(startLine, startcol, Token.GE, ">=");
            }

            /*This switch block is used to detect any legal single character Token in the input file.*/
            switch (current) {
                case '+': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.PLUS, "+");
                }
                case '-': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.MINUS, "-");
                }
                case '*': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.MULTIPLY, "*");
                }
                case '/': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.DIVIDE, "/");
                }
                case '<': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.LT, "<");
                }
                case '>': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.GT, ">");
                }
                case '=': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.EQ, "=");
                }
                case '(': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.LPAREN, "(");
                }
                case ')': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.RPAREN, ")");
                }
                case ',': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.COMMA, ",");
                }
                case ';': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.SEMICOLON, ";");
                }
                case ':': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.COLON, ":");
                }
                case '.': {
                    int startLine = line, startcol = col;
                    advance();
                    return new tokenTypes(startLine, startcol, Token.DOT, ".");
                }
            }

            /*If an illegal character is read, print out the illegal character to the console
             *and terminate the program immediately.*/
            System.out.println(line + ":" + col + ":>>>>> Illegal character '" + current + "'");
            System.exit(1);
            return null;
        }

        return new tokenTypes(line, col, Token.EOT, "");
    }
	
	/*This method reads the next token.*/
    public void next() {
        currentToken = scanNext();
    }

    /*This method returns the type of the token.*/
    public Token kind() {
        return currentToken.type;
    }

    /*This method returns the value of the token if it has one.*/
    public String value() {
        return currentToken.value;
    }

    /*This method returns the line and starting column position of the token.*/
    public String position() {
        return currentToken.line + ":" + currentToken.col;
    }
}
