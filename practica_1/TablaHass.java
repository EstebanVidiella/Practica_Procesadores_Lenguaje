package practica_1;

import java.util.Hashtable;

public class TablaHass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 */
		Hashtable<Integer, String> alumnos = new Hashtable<Integer,String>();
		
		alumnos.put(10, "Carlos");
		alumnos.put(20, "Maria");
		alumnos.put(30, "Luis");
		alumnos.put(40, "Carla");
		alumnos.put(50, "Pedro");
		alumnos.put(60, "Paula");
		alumnos.put(70, "Javier");
		alumnos.put(80, "Fernando");
		alumnos.put(90, "Alicia");
		alumnos.put(100, "Cristina");
		
		
		System.out.println("La tabla alumnos " + alumnos + "\n");
		System.out.println("<50>           " + alumnos.get(50));
		String x = alumnos.get(80);
		System.out.println("<80>           " + x);
		System.out.println("<30>           " + alumnos.get(30));
		System.out.println("<75>           " + alumnos.get(75));  //como no esta devuelve null
		
		System.out.println("");
		
		Hashtable<String, Float> notas = new Hashtable<String, Float>();
		
		notas.put("Carlos", 7.0f);
		notas.put("Luis", 9.0f);
		notas.put("Maria", 8.5f);
		notas.put("Pedro", 7.5f);
		notas.put("Paula", 8.0f);
		
		System.out.println(" ");
		System.out.println("La tabla de notas " + notas + "\n");
		System.out.println("<Carlos>           " + notas.get("Carlos"));
		float y = notas.get("Paula");
		System.out.println("<Paula>           " + y);
		System.out.println("<Cristina>           " + notas.get("Cristina"));
		System.out.println("<Fernando>           " + notas.get("Fernando"));
		
		System.out.println(" ");
		
		if(notas.containsKey("Paula"))
			System.out.println("La tabla de notas contiene la clave Paula");
		else {
			System.out.println("La tabla de notas no contiene la clave Paula");
		}

	}

}
