package com.felipe.bedoya.prueba.tecnica.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.services.OperacionesGrafosFloydService;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;

@Service
public class OperacionesGrafosFloydServiceImpl implements OperacionesGrafosFloydService{
	
	@Autowired
	private OperacionesComunes operaciones;
	
	/**
	 *  Reconstruye el camino entre i y j, excluidos estos, a partir de una matriz "matrizResultadosCoste" obtenida mediante la función Floyd()
	 * @param verticeOrigen vertice origen
	 * @param verticeDestino vertice destino
	 * @param matrizResultadoCaminos matriz de vertice intermedio entre dos verices i, j
	 * @return Lista Enteros con el camino entre i y j
	 */
	@Override
	public List<Integer> construirCaminoIntermedio(Integer verticeOrigen, Integer verticeDestino, Integer[][] matrizResultadoCaminos){
		List<Integer> camino1 = new ArrayList<>();
		List<Integer> camino2 = new ArrayList<>();
		Integer k = matrizResultadoCaminos[verticeOrigen][verticeDestino];
		if(k != verticeOrigen) {
			camino1 = construirCaminoIntermedio(verticeOrigen, k, matrizResultadoCaminos);
			camino1.add(k);
			camino2 = construirCaminoIntermedio(k, verticeDestino, matrizResultadoCaminos);
			camino2.stream().forEach(camino1::add);
		}
		return camino1;
	}
	
	/**
	 *  Reconstruye el camino entre i y j, excluidos estos, a partir de una matriz "matrizResultadoCaminos" obtenida mediante Floyd
	 * @param verticeOrigen vertice origen
	 * @param verticeDestino vertice destino
	 * @param matrizResultadoCaminos matriz de matriz de vertice intermedio entre dos verices i, j
	 * @return Lista Enteros con el camino entre i y j
	 */
	@Override
	public List<Integer> construirCamino(Integer verticeOrigen, Integer verticeDestino, Integer[][] matrizResultadoCaminos){
		List<Integer> caminoCompleto = new ArrayList<>();
		caminoCompleto.add(verticeOrigen);//Vertice origen
		caminoCompleto.addAll(construirCaminoIntermedio(verticeOrigen,verticeDestino,matrizResultadoCaminos));
		caminoCompleto.add(verticeDestino);//Vertice final
		return caminoCompleto;
	}
	
	/***
	 * Calcula el coste de pasar por los vertices de la ruta que recibe como parametro, sin hacer paradas extras.
	 * @param ruta
	 * @param matrizResultadoCaminos
	 * @param matrizResultadosCoste
	 * @return coste total de la ruta. si el coste total es -1, significa que la ruta no existe
	 */
	@Override
	public Integer calcularCosteRuta(String ruta, Integer[][]matrizResultadoCaminos, Integer[][]matrizResultadosCoste) throws CustomException{
		
		if(matrizResultadoCaminos == null)
			throw new CustomException(Constantes.ERROR_MATRIZ_CAMINOS_NULL);
		
		if(matrizResultadosCoste == null)
			throw new CustomException(Constantes.ERROR_MATRIZ_DISCANCIAS_NULL);
		
		Integer coste = 0;
		List<String> listaVerticesLetras = Arrays.asList(ruta.split("-"));
		
		if(listaVerticesLetras.size() == 1)
			return Constantes.NO_RUTA_VALIDA;
		
		//Validamos que sea una ruta con el formato que queremos
		for(String letra : listaVerticesLetras) {
			if(letra.length() != 1 || !Character.isLetter(letra.charAt(0)))
				return Constantes.NO_RUTA_VALIDA;
		}
		
		
		int numVertices = listaVerticesLetras.size();
		Integer arrVertices[] = new Integer[numVertices];
		
		int ind=0;
		for(String letra: listaVerticesLetras) {
			arrVertices[ind] = operaciones.calcularIndiceVerticeMedianteLetraChar(letra.charAt(0));
			ind++;
		}
		
		Boolean stop = Boolean.FALSE;
		int i=0;
		//Recorreremos arrVertices de dos en dos en busca de los costes de la ruta
		while(!stop) {
			int indiceOrigen = arrVertices[i];
			int indiceDestino = arrVertices[i+1];
			if(matrizResultadosCoste[indiceOrigen][indiceDestino] != Constantes.INFINITO && matrizResultadoCaminos[indiceOrigen][indiceDestino] == indiceOrigen) {
				coste += matrizResultadosCoste[indiceOrigen][indiceDestino];
			}else {
				stop = Boolean.TRUE;
				coste = Constantes.NO_RUTA;
			}
			
			//i+1 para comprobar que tenemos un vertice siguiente.
			if(i+1 == numVertices-1)//i empieza en 0, por tanto numVertices -1 
				stop = Boolean.TRUE;
			
			i++;//Avanzamos al siguiente par de vertices
		}
				
		return coste;
	}

}
