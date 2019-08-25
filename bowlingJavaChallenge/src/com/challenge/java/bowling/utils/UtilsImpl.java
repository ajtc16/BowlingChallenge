package com.challenge.java.bowling.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.challenge.java.bowling.entidades.Juego;
import com.challenge.java.bowling.entidades.Jugador;
import com.challenge.java.bowling.resources.ResourcesUtils;

public class UtilsImpl implements Utils{
	
	
	
	public void imprimirJuego(HashMap<String, List<String>> jugadas) {
		Juego juego;
		System.out.println ();
		System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.cabecera"));
		
		for (String jugada : jugadas.keySet()) {
			
			if (validoSumatoriaPorTurno(jugadas, jugada)) {
			
				juego = new Juego();
				
				System.out.println(jugada);
				System.out.print(ResourcesUtils.getString("bowling.challenge.pantalla.pins"));
	
				for (int i = 0; i < jugadas.get(jugada).size(); i++) {
					if (jugadas.get(jugada).get(i).equals("10")) {
						System.out.print("\tX\t");
					}else {
						System.out.print(jugadas.get(jugada).get(i)+"\t");
					}
					
				}
				System.out.println();
				System.out.print(ResourcesUtils.getString("bowling.challenge.pantalla.score"));
				
				juego.ingresoValores(jugadas.get(jugada)); 
				
				for (String valor : juego.puntuacion()) {
					System.out.print(valor+"\t\t");
				
				}
				System.out.println();
			}else {
				System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.error.sumatoria"));
				break;
			}
			
		}
	}

	public Collection<Jugador> cargarArchivoTexto(String ruta) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String linea;
		FileReader fr = new FileReader(ruta);
		BufferedReader reader = new BufferedReader(fr);
		Collection<Jugador> collection = new ArrayList<Jugador>();
		Jugador jugador ;
		
		try {
			while ((linea=reader.readLine())!=null) {
				String[] parts = linea.split(" ",2);
				
				if (parts.length > 2)
		        {
					System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.error.carga"));
		            
		        } else {
		        	jugador = new Jugador();
					jugador.setNombre(parts[0]);
					jugador.setPuntaje(parts[1]);
					collection.add(jugador);
					
		            String key = parts[0];
		            String value = parts[1];
		            map.put(key, value);
		            
		        }
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			reader.close();
		}
	    return collection;
	}
	
	
	public HashMap<String , List<String>> organizarJugadores(Collection<Jugador> jugadas){
		
		HashMap<String , List<String>> tiros = new HashMap<String, List<String>>();
		List<String> scores = new ArrayList<String>();
		
		for (Jugador p : jugadas) {
			if (null!= tiros.get(p.getNombre())) {
				scores.addAll(tiros.get(p.getNombre()));
			}
				scores.add(p.getPuntaje());
			
				tiros.put(p.getNombre(), scores);
			scores= new ArrayList<String>();
			
			
		}
		return tiros;
		
	}

	public boolean validarValoresCargados(HashMap<String, List<String>> jugadas) {
		
		boolean valido=true;
		String numeros =ResourcesUtils.getString("bowling.challenge.numeros.permitidos");
		
		for (String valor : jugadas.keySet()) {
			for (int i = 0; i < jugadas.get(valor).size() ; i++) {
				if (numeros.indexOf(jugadas.get(valor).get(i))<0) {
					System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.error.valores"));
					valido=false;
					break;
				}
			}
		}
		
		return valido;
	}

	public boolean validarJugadas(HashMap<String, List<String>> jugadas) {
		int maximo = Integer.parseInt(ResourcesUtils.getString("bowling.challenge.maximo.tiros"));
		boolean valido=true;
		for (String valor : jugadas.keySet()) {
			
			if (jugadas.get(valor).size() > maximo) {
				valido=false;
				System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.error.intentos"));
				break;
			}
			
		}
		
	return valido;
		
	}
	
	
	public boolean validoSumatoria(String valor1, String valor2) {
		
		int val1= verificaString(valor1);
		int val2= verificaString(valor2);
		boolean valido = true;
		
		if (val1+val2>10) {
			valido=false;
		}
		
		return valido;
	}
	
	public int verificaString(String valor) {
		int valorEntero=0;
		
		if ("X".equals(valor) || "x".equals(valor)) {
			valorEntero=10;
		}else if ("F".equals(valor) || "f".equals(valor)) {
			valorEntero=0;
		} else  {
			valorEntero=Integer.parseInt(valor);
		} 
		
		
		
		return valorEntero;
	}
	
	public boolean validoSumatoriaPorTurno(HashMap<String, List<String>> jugadas, String jugada) {
		boolean valido= false;
		for (int i = 0; i < jugadas.get(jugada).size()-1; i++) {
			
			if ("10".equals(jugadas.get(jugada).get(i))) {
//				System.out.println("sumatoria en posicion: "+i+" es correcta, su valores son: "+jugadas.get(jugada).get(i));
				valido= true;
			}
			else if (validoSumatoria(jugadas.get(jugada).get(i), jugadas.get(jugada).get(i+1))) {
//				System.out.println("sumatoria en posicion: "+i+" es correcta, su valores son: "+jugadas.get(jugada).get(i) +" y "+jugadas.get(jugada).get(i+1));
				valido= true;
				i++;
			}else {
//				System.out.println("sumatoria en posicion: "+i+" es INCORRECTA, su valores son: "+jugadas.get(jugada).get(i) +" y "+jugadas.get(jugada).get(i+1));
				valido=false;
				break;
			}
		}
		
		return valido;
	}

	public String obtenerUrlArchivo(String opcionMenu) {
		String url="";
		
		if ("1".equals(opcionMenu)) {
			url = "src/sampleInput.txt";
		}else if ("2".equals(opcionMenu)) {
			url = "src/perfectScore.txt";
		}else if ("3".equals(opcionMenu)) {
			url =  "src/zeroScore.txt";
		}
		
		return url;
	}

	public void cargarMenuPantalla() {
		
		System.out.println ("**************************************************");
		System.out.println ("***Bienvenido al calculador de puntaje de Bolos***");
		System.out.println ("**************************************************");

        System.out.println ("Por favor introduzca la opcion correspondiente a la Prueba que desea cargar:");
        System.out.println ();
        System.out.println ("(1) Cargar SampleInput.txt");
        System.out.println ("(2) Cargar perfectScore.txt");
        System.out.println ("(3) Cargar zeroScore.txt");
        System.out.println ("(s) Salir de Programa");
        System.out.println ();
	}

}
