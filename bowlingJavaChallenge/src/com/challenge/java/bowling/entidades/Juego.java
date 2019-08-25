package com.challenge.java.bowling.entidades;

import java.util.ArrayList;
import java.util.List;

public class Juego {

	
	private int lanzamiento;
	private int[] intentos = new int[21];
	private String[] registro = new String[21]; 
	
	
	public void ingresoValores(List<String> lista) {
		
		for (String pinosCaidos : lista) {
			if ("F".equals(pinosCaidos)) {
				pinosCaidos="0";
			}
			else if ("X".equals(pinosCaidos)) {
				pinosCaidos="10";
			}
			registrarValor(Integer.parseInt(pinosCaidos));
		}
	}
	
	public void registrarValor(int pinosCaidos) {
		this.intentos[lanzamiento++]=pinosCaidos;
	}
	
	
	
	public List<String> puntuacion() {
		int puntaje = 0;
		int posicion = 0;
		List<String> resultado= new ArrayList<String>();
		
		
		for (int frame = 0; frame < 10; frame++) {
			
			if (esChuza(posicion) ) {
				puntaje += 10 + this.intentos[posicion+1] + this.intentos[posicion+2];
				resultado.add(String.valueOf(puntaje));
				posicion++;
			}
			else  if (esSpare(posicion)) {
				puntaje += 10 + this.intentos[posicion+2];
				resultado.add(String.valueOf(puntaje));
				posicion+=2;
				
			} else {
				puntaje += this.intentos[posicion] + this.intentos[posicion+1];
				resultado.add(String.valueOf(puntaje));
				posicion+=2;
			}
			
			
		}
		
		
		return resultado;
	}
	

	private boolean esChuza(int cursor) {
		return this.intentos[cursor]==10;
	}
	
	private boolean esSpare(int cursor) {
		return this.intentos[cursor] + this.intentos[cursor+1] == 10;
	}

	public String[] getRegistro() {
		return registro;
	}

	public void setRegistro(String[] registro) {
		this.registro = registro;
	}
	
}
