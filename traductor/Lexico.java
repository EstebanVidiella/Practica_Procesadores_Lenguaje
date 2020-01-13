package traductor;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;



import java.util.Hashtable;

public class Lexico {

	private PalabrasReservadas palabras;
	private int posicion;
	private int lineas;
	private char caracter;
	private String programa;
	

	public Lexico(String programa) {
		this.posicion = 0;
		this.lineas = 1;
		this.palabras = new PalabrasReservadas("lexemas.txt");
		this.programa = programa + this.palabras.getLexema("end_program"); 	
		//this.programa = programa + "#";
	}

	private static boolean existeFichero(String fichero) {
		File ficheroEntrada = new File (fichero);

		return ficheroEntrada.exists();
	}
	

	private static String contenidoFichero(String fichero, Charset codificacion) {
		String s = null;

		if(existeFichero(fichero)) {
			try {
				byte [] contenido = Files.readAllBytes(Paths.get(fichero));

				s = new String(contenido, codificacion);
			} catch (IOException e) { }
		}
		return s;
	}

	public Lexico(String ficheroEntrada, Charset codificacion) {
		this(contenidoFichero(ficheroEntrada, codificacion));
	}

	private char extraeCaracter() {
		return this.programa.charAt(this.posicion++);

	}

	public ComponenteLexico getComponenteLexico() {
		// el analizador lexico descarta los espacios (codigo 32), tabuladores (codigo 9) y saltos de linea (10 y 13)
		String etiqueta;
		while (true) {
			this.caracter = extraeCaracter();

			if(this.caracter == ' ' || (int) this.caracter == 9 || (int) this.caracter == 13)
				continue;
			else if((int) this.caracter == 10)
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

			if(this.caracter != '.') {
				devuelveCaracter();

				return new ComponenteLexico("integer", Integer.parseInt(numero) + "");
			}

			do {
				numero = numero + this.caracter;

				this.caracter = extraeCaracter();
			} while (Character.isDigit(this.caracter));

			devuelveCaracter();

			return new ComponenteLexico("float", Float.parseFloat(numero) + "");
		}


		// identifcadores y palabras reservadas

		if (Character.isLetter(this.caracter)) {
			String lexema = " ";

			do {

				lexema = lexema + this.caracter;

				this.caracter = extraeCaracter();

			} while(Character.isLetterOrDigit(this.caracter));

			devuelveCaracter();
			
			etiqueta = palabras.getEtiquetaLexica(lexema);

			if (etiqueta == null)
				return new ComponenteLexico("id", lexema);
			else
				return new ComponenteLexico(etiqueta);

		}
		String lexema = "", lexemaAlternativo, etiquetaAlternativa;
		do {
			lexema = lexema + this.caracter;
			etiqueta = palabras.getEtiquetaLexica(lexema);
			
			if(etiqueta.equals("end_program"))
				return new ComponenteLexico(etiqueta);
			lexemaAlternativo = lexema;
			this.caracter = extraeCaracter();
			
			lexemaAlternativo = lexemaAlternativo + this.caracter;
			
			etiquetaAlternativa = palabras.getEtiquetaLexica(lexemaAlternativo);
			
			if(etiquetaAlternativa != null)
				etiqueta = etiquetaAlternativa;
		}while(etiquetaAlternativa != null);
		devuelveCaracter();
		return new ComponenteLexico(etiqueta);
		// operadores aritmeticos, relacionales, logicos y caracteres delimitadores
		/*
		switch (this.caracter) {
		case '&':

			if (this.extraeCaracter('&'))
				return new ComponenteLexico("and");
			else
				return new ComponenteLexico("bitwise_and");

		case '|':

			if (this.extraeCaracter('|'))
				return new ComponenteLexico("or");
			else
				return new ComponenteLexico("bitwise_or");

		case '=':

			if(this.extraeCaracter('='))
				return new ComponenteLexico("equals");
			else
				return new ComponenteLexico("assigment");

		case '!' :

			if(this.extraeCaracter('='))
				return new ComponenteLexico("not_equals"); 
			else
				return new ComponenteLexico("not"); 

		case '>':

			if(this.extraeCaracter('='))
				return new ComponenteLexico("great_equals");
			else
				return new ComponenteLexico("great_than");

		case '<':
			if(this.extraeCaracter('='))
				return new ComponenteLexico("less_equals");
			else
				return new ComponenteLexico("less_than");

		case '+' :return new ComponenteLexico("add");

		case '-' :return new ComponenteLexico("subtract");

		case '*' :return new ComponenteLexico("multiply");

		case '/' :return new ComponenteLexico("divide");

		case '%' :return new ComponenteLexico("remainder");

		case ';' :return new ComponenteLexico("semicolon"); 

		case '(' :return new ComponenteLexico("open_parenthesis");

		case ')' :return new ComponenteLexico("closed_parenthesis");

		case '[' :return new ComponenteLexico("open_square_bracket");

		case ']' :return new ComponenteLexico("closed_square_bracket");

		case '{' :return new ComponenteLexico("open_bracket");

		case '}' :return new ComponenteLexico("closed_bracked");

		case '#' :return new ComponenteLexico("end_program");

		default: return new ComponenteLexico(this.caracter + "");

		}
		*/

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

