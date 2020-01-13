package Practica4;

import java.util.Enumeration;
import java.util.Hashtable;
import Practica2.ComponenteLexico;
import Practica2.Lexico;

public class Sintactico {
	private Lexico lexico;
	private ComponenteLexico componenteLexico;
	private Hashtable<String,TipoDato> simbolos;
	private String tipo;
	private int tamaño;

	public Sintactico(Lexico lexico) {
		this.lexico = lexico;
		this.componenteLexico = this.lexico.getComponeneteLexico();
		this.simbolos = new Hashtable<String,TipoDato>();
	}

	public void analisisSintactico() {
		declaraciones();
	}

	public void declaraciones() {
		if(this.componenteLexico.getEtiqueta().equals("int") || this.componenteLexico.getEtiqueta().equals("float")) {
			declaracionVariable();
			declaraciones();
		}
	}

	public void declaracionVariable() {
		tipoPrimitivo();
		if(this.componenteLexico.getEtiqueta().equals("open_square_bracket")) {
			tipoArray();
			simbolos.put(this.componenteLexico.getDato(), new TipoArray(this.tipo, this.tamaño));
			compara("id");
			compara("semicolon");
		}else {
			listaIdentificadores();
			compara("semicolon");
		}
	}

	public void tipoPrimitivo() {
		if(this.componenteLexico.getEtiqueta().equals("int")) {
			this.tipo = "int";
			compara("int");
		}else {
			this.tipo = "float";
			compara("float");
		}
	}

	public void tipoArray() {
		compara("open_square_bracket");
		this.tamaño = Integer.parseInt(this.componenteLexico.getDato());
		compara("integer");
		compara("closed_square_bracket");
	}

	public void listaIdentificadores() {
		if(this.componenteLexico.getEtiqueta().equals("id")) {
			simbolos.put(this.componenteLexico.getDato(), new TipoPrimitivo(this.tipo));
			compara("id");
			masIdentificadores();
		}
	}

	public void masIdentificadores() {
		if(this.componenteLexico.getEtiqueta().equals("comma")) {
			compara("comma");
			simbolos.put(this.componenteLexico.getDato(), new TipoPrimitivo(this.tipo));
			compara("id");
			masIdentificadores();
		}
	}

	public void compara(String token) {
		if(this.componenteLexico.getEtiqueta().equals(token)) {
			this.componenteLexico = this.lexico.getComponeneteLexico();
		}else {
			System.out.println("Expected: " + token);
		}
	}
	
	public void printSimbolos() {
		Enumeration keys = simbolos.keys();
		Enumeration clave = simbolos.elements();
		
		System.out.println("Lexema           Tipo de Dato");
		System.out.println("-----------------------------");
		while(keys.hasMoreElements() && clave.hasMoreElements()) {
			System.out.println(keys.nextElement() + "                " + clave.nextElement().toString());
		}
	}

	public Lexico getLexico() {
		return lexico;
	}

	public void setLexico(Lexico lexico) {
		this.lexico = lexico;
	}

	public ComponenteLexico getComponenteLexico() {
		return componenteLexico;
	}

	public void setComponenteLexico(ComponenteLexico componenteLexico) {
		this.componenteLexico = componenteLexico;
	}

	public Hashtable<String, TipoDato> getPalabras() {
		return this.simbolos;
	}

	public void setPalabras(Hashtable<String, TipoDato> simbolos) {
		this.simbolos = simbolos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
