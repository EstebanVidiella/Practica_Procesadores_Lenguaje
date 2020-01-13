package practica_2;

import java.util.Hashtable;

public class TestParEtiquetaLexema {
	private static String etiqueta(String s) {
		return s.substring(0, s.indexOf("\t")).trim();
	}
	
	private static String lexema(String s) {
		return s.substring(s.lastIndexOf("\t")+1).trim();
	}
	
	public static void main(String[] args) {
		Hashtable<String, String> lexemas = new Hashtable<String, String>();
		String[] parEtiquetaLexema = { "and\t\t\t&&", "not\t\t\t\t!", "add\t\t+", "or\t\t\t\t||"};
		for(String s : parEtiquetaLexema) {
			System.out.println(etiqueta(s) + "\t\t" + lexema(s) + "'");
			
			lexemas.put(lexema(s), etiqueta(s));
		}
		System.out.println("");
		System.out.println("El token del lexema +  es " + lexemas.get("+"));
		System.out.println("El token del lexema || es " + lexemas.get("||"));
		System.out.println("El token del lexema && es " + lexemas.get("&&"));
		
	}

}
