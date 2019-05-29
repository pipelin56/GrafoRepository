package com.felipe.bedoya.prueba.tecnica.services;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;

/**
 * Interfaz para definir el "contrato" de los algoritmos de grafos a implementar. 
 * @author Felipe Bedoya Castaño
 *
 */
public interface AlgoritmosGrafosService {
	
	/**
	 * Calcula los costes de ir de un vertice origen i a otro j, así como el camino.
	 * @param grafo
	 * @param matrizResultadoCaminos 
	 * @return Matriz con los costes de ir de un vertice i a otro j
	 * @throws CustomException
	 */
	public Integer[][] calcularFloyd(GrafoPonderado grafo, Integer[][] matrizResultadoCaminos) throws CustomException;

}
