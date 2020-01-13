package practica_1;
import java.nio.charset.Charset;
import java.util.Hashtable;



public class LexicoBasico {

	private Hashtable<String, String> palabrasReservadas;
	private int posicion;
	private int lineas;
	private char caracter;
	private String programa;

	public LexicoBasico(String programa) {
		this.posicion = 0;
		this.lineas = 1;
		this.palabrasReservadas = new Hashtable<String, String>();
		this.palabrasReservadas.put("break", "break");
		this.palabrasReservadas.put("do", "do");
		this.palabrasReservadas.put("else", "else");
		this.palabrasReservadas.put("float", "float");
		this.palabrasReservadas.put("for", "for");
		this.palabrasReservadas.put("if", "if");
		this.palabrasReservadas.put("int", "int");
		this.palabrasReservadas.put("while", "while");
		this.programa = programa + "#";
	}

	private char extraeCaracter() {
		return this.programa.charAt(this.posicion++);

	}

	public ComponenteLexicoBasico getComponenteLexico() {
		// el analizador lexico descarta los espacios (codigo 32), tabuladores (codigo 9) y saltos de linea (10 y 13)

		while (true) {
			this.caracter = extraeCaracter();

			if(this.caracter == ' ' || (int) this.caracter == 9 || (int) this.caracter == 10)
				continue;
			else if((int) this.caracter == 13)
				this.lineas++;
			else
				break;
		}

		// secuencias de digitos de numeros enteros o reales 

		if (Character.isDigit(this.caracter)) {	
			String numero = "" ;

			do { 
				numero = numero + this.caracter;

				this.caracter = extraeCaracter();
			} while (Character.isDigit(this.caracter));
			devuelveCaracter();

			return new ComponenteLexicoBasico("integer", Integer.parseInt(numero) + "");
		}


		// identifcadores y palabras reservadas

		if (Character.isLetter(this.caracter)) {
			String lexema = " ";

			do {

				lexema = lexema + this.caracter;

				this.caracter = extraeCaracter();

			} while(Character.isLetterOrDigit(this.caracter));

			devuelveCaracter();

			if (this.palabrasReservadas.containsKey(lexema))
				return new ComponenteLexicoBasico((String) this.palabrasReservadas.get(lexema));
			else
				return new ComponenteLexicoBasico("id", lexema);

		}

		// operadores aritmeticos, relacionales, logicos y caracteres delimitadores

		switch (this.caracter) {
		case '&':

			if (this.extraeCaracter('&'))
				return new ComponenteLexicoBasico("and");
			else
				return new ComponenteLexicoBasico("bitwise_and");

		case '|':

			if (this.extraeCaracter('|'))
				return new ComponenteLexicoBasico("or");
			else
				return new ComponenteLexicoBasico("bitwise_or");

		case '=':

			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("equals");
			else
				return new ComponenteLexicoBasico("assigment");

		case '!' :

			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("not_equals"); 
			else
				return new ComponenteLexicoBasico("not"); 

		case '>':

			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("great_equals");
			else
				return new ComponenteLexicoBasico("great_than");

		case '<':
			if(this.extraeCaracter('='))
				return new ComponenteLexicoBasico("less_equals");
			else
				return new ComponenteLexicoBasico("less_than");

		case '+' :return new ComponenteLexicoBasico("add");

		case '-' :return new ComponenteLexicoBasico("subtract");

		case '*' :return new ComponenteLexicoBasico("multiply");

		case '/' :return new ComponenteLexicoBasico("divide");

		case '%' :return new ComponenteLexicoBasico("remainder");

		case ';' :return new ComponenteLexicoBasico("semicolon"); 

		case '(' :return new ComponenteLexicoBasico("open_parenthesis");

		case ')' :return new ComponenteLexicoBasico("closed_parenthesis");

		case '[' :return new ComponenteLexicoBasico("open_square_bracket");

		case ']' :return new ComponenteLexicoBasico("closed_square_bracket");

		case '{' :return new ComponenteLexicoBasico("open_bracket");

		case '}' :return new ComponenteLexicoBasico("closed_bracked");

		case '#' :return new ComponenteLexicoBasico("end_program");

		default: return new ComponenteLexicoBasico(this.caracter + "");

		}

	}

	private boolean extraeCaracter(char c) {
		// TODO Auto-generated method stub
		if (this.posicion < this.programa.length() - 1) {
			this.caracter = extraeCaracter();

			if (c == this.caracter)
				return true;
			else {
				devuelveCaracter();

				return false;
			}
		}
		else
			return false;
	}


	private void devuelveCaracter() {
		// TODO Auto-generated method stub
		this.posicion--;
	}

	public int getLineas() {
		return this.lineas;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

