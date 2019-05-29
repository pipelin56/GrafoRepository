package com.felipe.bedoya.prueba.tecnica.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;
import com.felipe.bedoya.prueba.tecnica.services.AlgoritmosGrafosService;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;

/***
 * Servicio para la implementación de los algortimos de grafos establecidos en la interfaz AlgoritmosGrafos.
 * @author Felipe Bedoya Castaño
 *
 */

@Service
public class AlgoritmosGrafosServiceImpl implements AlgoritmosGrafosService{
	
	@Autowired
	private OperacionesComunes operacionesComunes;

	@Override
	public Integer[][] calcularFloyd(GrafoPonderado grafo, Integer[][] matrizResultadoCaminos) throws CustomException {
		
		operacionesComunes.validarGrafo(grafo);
		
		Integer numVertices = grafo.getNumVertices();
		Integer[][] matrizResultadosCoste = new Integer[numVertices][numVertices];
				
		//Inicialización y copia de datos
		for(int i=0; i < numVertices; i++) {
			matrizResultadosCoste[i][i] = 0;			  //diagonal matriz a 0
			for(int j=0; j< numVertices; j++) {
				matrizResultadosCoste[i][j] = grafo.getMatrizAdy()[i][j]; //Copia de costes
				matrizResultadoCaminos[i][j] = i;//Por defecto, para ir de i a j será a través de i
			}
		}
		
		//Algoritmo de Floyd
		for(int k = 0; k < numVertices; k++)
			for(int i = 0; i< numVertices; i++)
				for(int j = 0; j < numVertices; j++) {
					Integer coste = operacionesComunes.sumarCostes(matrizResultadosCoste[i][k], matrizResultadosCoste[k][j]);//En esta suma se comprueba el infinito
					if(coste < matrizResultadosCoste[i][j]) {
						 matrizResultadosCoste[i][j] = coste;
			             matrizResultadoCaminos[i][j] = k; //ir de i a j mediante k
					}
				}
		
		return matrizResultadosCoste;
	}
	
	

}
