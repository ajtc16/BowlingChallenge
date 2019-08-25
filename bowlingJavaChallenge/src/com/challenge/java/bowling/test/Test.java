package com.challenge.java.bowling.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.challenge.java.bowling.entidades.Juego;
import com.challenge.java.bowling.entidades.Jugador;
import com.challenge.java.bowling.resources.ResourcesUtils;


public class Test {

	public static void main(String[] args) {

		Collection<Jugador> partido = new ArrayList<Jugador>();
		HashMap<String , List<String>> jugadas = new HashMap<String, List<String>>();
		boolean numIntentosValidos = false;
		boolean numValoresCorrectos = false;
		
		try {
			partido=cargarArchivoTexto();
			
			jugadas=organizarJugadores(partido);
			
			numIntentosValidos = validarJugadas(jugadas);
			numValoresCorrectos = validarValoresCargados(jugadas);
			
			if (numIntentosValidos && numValoresCorrectos) {
				imprimirJuego(jugadas);
			} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	 
	}
	
	private static boolean validoSumatoria(String valor1, String valor2) {
		
		
		int val1= verificaString(valor1);
		int val2= verificaString(valor2);
		boolean valido = true;
		
		if (val1+val2>10) {
			valido=false;
		}
		
		return valido;
	}
	
	
	private static int verificaString(String valor) {
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
	

	private static boolean validarValoresCargados(HashMap<String, List<String>> jugadas) {
		
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

	private static boolean validarJugadas(HashMap<String, List<String>> jugadas) {
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

	private static void imprimirJuego(HashMap<String, List<String>> jugadas) {
		Juego juego;
		System.out.println("Imprimo en el Test Main");
		
		System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.cabecera"));
//			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		for (String jugada : jugadas.keySet()) {
			juego = new Juego();
			
			if (validoSumatoriaPorTurno(jugadas, jugada)) {
			System.out.println(jugada);
//				System.out.println("\t"+jugadas.get(jugada));
			System.out.print(ResourcesUtils.getString("bowling.challenge.pantalla.pins"));
//				for (String j : jugadas.get(jugada)) {
//					System.out.print(j+"\t");
//				}
			int cont=0;
			
			
				for (int i = 0; i < jugadas.get(jugada).size(); i++) {
					if (jugadas.get(jugada).get(i).equals("10")) {
						System.out.print("\tX\t");
						cont=0;
					}else {
//							if (cont == 0) {
//								System.out.print(jugadas.get(jugada).get(i));
//								cont++;
//							}else {
//								if (Integer.parseInt(jugadas.get(jugada).get(i))+Integer.parseInt(jugadas.get(jugada).get(i-1))!=10) {
								System.out.print(jugadas.get(jugada).get(i)+"\t");
//								}else {
//									System.out.print("/"+"\t");
//								}
							
//								cont=0;
//							}
					}
					
				}
				System.out.println();
//					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print(ResourcesUtils.getString("bowling.challenge.pantalla.score"));

				
				juego.ingresoValores(jugadas.get(jugada)); 
//					System.out.println("total "+game.score2());
//				
				for (String valor : juego.puntuacion()) {
					System.out.print(valor+"\t\t");
				
				}
//				juego.puntuacion();
//				
//				for (String i : juego.getRegistro()) {
//					System.out.print(i+"\t\t");
//				}
				System.out.println();
				
			}else {
				System.out.println(ResourcesUtils.getString("bowling.challenge.pantalla.error.sumatoria"));
				break;
			}
			
			
			
		}
	}

	private static boolean validoSumatoriaPorTurno(HashMap<String, List<String>> jugadas, String jugada) {
		boolean valido= false;
		System.out.println("inicio For de validacion de sumatoria");
		for (int i = 0; i < jugadas.get(jugada).size()-1; i++) {
			
			if ("10".equals(jugadas.get(jugada).get(i))) {
				System.out.println("sumatoria en posicion: "+i+" es correcta, su valores son: "+jugadas.get(jugada).get(i));
				valido= true;
			}
			else if (validoSumatoria(jugadas.get(jugada).get(i), jugadas.get(jugada).get(i+1))) {
				
				System.out.println("sumatoria en posicion: "+i+" es correcta, su valores son: "+jugadas.get(jugada).get(i) +" y "+jugadas.get(jugada).get(i+1));
				valido= true;
				i++;
			}else {
				System.out.println("sumatoria en posicion: "+i+" es INCORRECTA, su valores son: "+jugadas.get(jugada).get(i) +" y "+jugadas.get(jugada).get(i+1));
				valido=false;
				break;
			}
		}
		
		System.out.println("fin For de validacion de sumatoria");
		
		return valido;
	}

	private static Collection<Jugador> cargarArchivoTexto() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String filePath = "/Users/ajteran/Desktop/test/ejemplo.txt";
		
		String linea;
		FileReader fr = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(fr);
		Collection<Jugador> collection = new ArrayList<Jugador>();
		Jugador jugador ;
		
		while ((linea=reader.readLine())!=null) {
			String[] parts = linea.split(" ", 2);
			
			if (parts.length >= 2)
	        {
				jugador = new Jugador();
				jugador.setNombre(parts[0]);
				jugador.setPuntaje(parts[1]);
				collection.add(jugador);
				
	            String key = parts[0];
	            String value = parts[1];
	            map.put(key, value);
	            
	        } else {
	            System.out.println("dato incorrcto ");
	        }
			
			
		}
	    reader.close();
	    
	    return collection;
	}
	
	
	private static HashMap<String , List<String>> organizarJugadores(Collection<Jugador> jugadas){
		
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


}

