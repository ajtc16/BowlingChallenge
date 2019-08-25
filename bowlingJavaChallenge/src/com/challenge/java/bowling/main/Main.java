package com.challenge.java.bowling.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.challenge.java.bowling.entidades.Jugador;
import com.challenge.java.bowling.utils.GenericFactory;
import com.challenge.java.bowling.utils.UtilsImpl;

public class Main extends UtilsImpl{

	public static void main(String[] args) {

		Collection<Jugador> partido = new ArrayList<Jugador>();
		HashMap<String , List<String>> jugadas = new HashMap<String, List<String>>();
		boolean numIntentosValidos = false;
		boolean numValoresCorrectos = false;
		String opcionMenu = "";
		String ruta = "";
		
		try {
			
			GenericFactory.getBowlingUtils().cargarMenuPantalla();

			
			do {
				System.out.print ("Por favor introduzca una opcion:");
				opcionMenu = new Scanner (System.in).nextLine (); 
				
				ruta = GenericFactory.getBowlingUtils().obtenerUrlArchivo(opcionMenu);
				
				if ("s".equals(opcionMenu)) {
					System.out.println ("Adios!!");
					System.exit(0);
				}
			}while ("".equals(ruta));
			
			
			
			
			partido=GenericFactory.getBowlingUtils().cargarArchivoTexto(ruta);  //cargarArchivoTexto(ruta);
			
			jugadas=GenericFactory.getBowlingUtils().organizarJugadores(partido);
			
			numIntentosValidos = GenericFactory.getBowlingUtils().validarJugadas(jugadas);
			numValoresCorrectos = GenericFactory.getBowlingUtils().validarValoresCargados(jugadas);
			
			
			if (numIntentosValidos && numValoresCorrectos) {
				GenericFactory.getBowlingUtils().imprimirJuego(jugadas);
			} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public  void pepe() {
		getClass().getClassLoader().getResourceAsStream("main/lol.txt");
	}
	

}
