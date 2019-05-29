package com.felipe.bedoya.prueba.tecnica.services;

import java.util.List;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;

/**
 * 
 * @author Felipe Bedoya Castaño
 *
 */
public interface OperacionesGrafosFloydService {

	/**
	 * Permite construir un camino entre un vertice origen i y otro vertice destino j
	 * @param verticeOrigen
	 * @param verticeDestino
	 * @param matrizResultadoCaminos
	 * @return lista de los vertices entre un vertice origen i y otro vertice destino j
	 */
	public List<Integer> construirCaminoIntermedio(Integer verticeOrigen, Integer verticeDestino, Integer[][] matrizResultadoCaminos);

	/**
	 * Permite construir un camino entre un vertice origen i y otro vertice destino j, incluidos ambos.
	 * @param verticeOrigen
	 * @param verticeDestino
	 * @param matrizResultadoCaminos
	 * @return lista de los vertices desde un vertice origen i hasta otro vertice destino j, ambos incluidos.
	 */
	public List<Integer> construirCamino(Integer verticeOrigen, Integer verticeDestino, Integer[][] matrizResultadoCaminos);

	/**
	 * Calcula el coste de ir de por una ruta concreta. En caso de que no se pueda realizar la ruta, devuelve -1
	 * @param ruta
	 * @param matrizResultadoCaminos
	 * @param matrizResultadosCoste
	 * @return coste de la ruta
	 * @throws CustomException 
	 */
	public Integer calcularCosteRuta(String ruta, Integer[][] matrizResultadoCaminos, Integer[][] matrizResultadosCoste) throws CustomException;

}
