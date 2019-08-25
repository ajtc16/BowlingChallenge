package com.challenge.java.bowling.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.challenge.java.bowling.entidades.Jugador;

public interface Utils  {
	
	 void imprimirJuego(HashMap<String, List<String>> jugadas);

	 Collection<Jugador> cargarArchivoTexto(String ruta) throws Exception;
	
	 HashMap<String , List<String>> organizarJugadores(Collection<Jugador> jugadas);

	 boolean validarValoresCargados(HashMap<String, List<String>> jugadas);

	 boolean validarJugadas(HashMap<String, List<String>> jugadas);
	
	 boolean validoSumatoria(String valor1, String valor2);
	
	 int verificaString(String valor);
	
	 boolean validoSumatoriaPorTurno(HashMap<String, List<String>> jugadas, String jugada);

	 String obtenerUrlArchivo(String opcionMenu);

	 void cargarMenuPantalla();
}
