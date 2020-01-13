package practica_2;

import practica_2.Lexico;

public class TestLexicoBasico{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComponenteLexico etiquetaLexica;

		String programa ="int k; for (int i=0;i<10;i=i+1)k=k*2;";

		Lexico lexico = new Lexico(programa);

		int c = 0;

		System.out.println("Test lexico basico \t" + programa + "\n");

		do {

			etiquetaLexica = lexico.getComponenteLexico();

			System.out.println(etiquetaLexica.toString());

			c++;	
		} while (!etiquetaLexica.getEtiqueta().equals("end_program"));

		System.out.println("\n Compomentes lexicos: " + c + ", lineas: " + lexico.getLineas());
	}

}