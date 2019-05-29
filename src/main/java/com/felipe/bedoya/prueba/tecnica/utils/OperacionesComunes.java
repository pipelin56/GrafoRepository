package com.felipe.bedoya.prueba.tecnica.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;
import com.felipe.bedoya.prueba.tecnica.pojos.TrozoRuta;

@Component
public class OperacionesComunes {
	
		/**
	 * Recibe una letra A, B, C ... Z y devuelve su valor casteado a entero y dividido por 65
	 * @param letra
	 * @return Integer valor de la letra dividido por 65
	 */
	public Integer calcularIndiceVerticeMedianteLetraChar(Character letra) throws CustomException{
		if(!Character.isLetter(letra.charValue()))
			throw new CustomException(Constantes.ERROR_PARAMETRO_NO_ES_LETRA);
		return Integer.valueOf((int) letra.charValue()) - Constantes.CHAR_INICIAL;
	}
	
	/**
	 * Imprime por la consola una matriz
	 * @param matriz
	 */
	public void imprimirMatriz(Integer[][] matriz) {
		if(matriz != null) {			
			for(Integer[] array : matriz) {
				for(Integer elto : array) {
					if(elto != null && !elto.equals(Constantes.INFINITO))
						System.out.print(elto+"\t");
					else
						System.out.print("~\t");
				}
				System.out.println();
			}
		}
	}
	
	/**
	 * Convierte un grafo representado en una cadena a una matriz de entero
	 * @param cadenaGrafo
	 * @return Matriz del grafo
	 */
	public Integer[][] convertirCadenaGrafoAMatriz(String cadenaGrafo) throws CustomException{
		//Si cadena nulla o vacia, error
		if(cadenaGrafo == null || cadenaGrafo.trim().length() == 0)
			throw new CustomException(Constantes.ERROR_CADENA_GRAFO_NULL_O_VACIA);
		
		Integer[][] matrizGrafo = null;
		
		//Split por coma
		List<String> listCadenaGrafo = Arrays.asList( cadenaGrafo.split(",") );
		if(listCadenaGrafo.isEmpty())
			throw new CustomException(Constantes.ERROR_CADENA_GRAFO_FORMATO_NO_VALIDO);
	
		//Creamos una lista de TrozoRuta a partir de listCadenaGrafo
		List<TrozoRuta> listaTrozosRuta = new ArrayList<>();
		for(String verticesCoste : listCadenaGrafo) {
			if(esFormatoRutaCosteValida(verticesCoste.trim()))
				listaTrozosRuta.add(new TrozoRuta(verticesCoste.trim()));
			else
				throw new CustomException(Constantes.ERROR_CADENA_GRAFO_FORMATO_NO_VALIDO);
		}
		
		//Recogemos los distintos vertices que tenemos en una lista, sin repetirlos
		List<Character> listaVertices = new ArrayList<>();
		listaTrozosRuta.stream()
						.forEach( trozo -> {
											 if(!listaVertices.contains(trozo.getVerticeOrigen()))
												 listaVertices.add(trozo.getVerticeOrigen());
											 if(!listaVertices.contains(trozo.getVerticeDestino()))
												 listaVertices.add(trozo.getVerticeDestino());
		});
		
		//Inicializamos la matriz de adyacencia del grafo
		Integer numVertices = listaVertices.size();
		matrizGrafo = new Integer[numVertices][numVertices];
		//Rellenamos todos sus huecos con infinito
		for(int i=0; i<numVertices; i++ )
			for(int j=0; j<numVertices; j++ )
				matrizGrafo[i][j] = Constantes.INFINITO; 
		
		//Rellenamos los pesos que se corresponden con los de las aristas entre vertices origen y destino
		for(TrozoRuta trozo : listaTrozosRuta){
			int indiceVerticeOrigen = calcularIndiceVerticeMedianteLetraChar(trozo.getVerticeOrigen());
			int indiceVerticeDestino = calcularIndiceVerticeMedianteLetraChar(trozo.getVerticeDestino());
			try {				
				matrizGrafo[indiceVerticeOrigen][indiceVerticeDestino] = trozo.getPeso();
			}catch(Exception e) {
				throw new CustomException(Constantes.ERROR_CADENA_GRAFO_FORMATO_NO_VALIDO);
			}
		}
		
		return matrizGrafo;
	}
	
	/**
	 * Comprueba que la cadena es una conexion entre dos nodos y coste válida, los dos primeros caracteres son una letra y el tercero un número. Ejemplo(AD5)
	 * @param rutaCoste
	 * @return
	 */
	private Boolean esFormatoRutaCosteValida(String rutaCoste) {
		if(rutaCoste == null || !rutaCoste.matches(Constantes.REG_EXP_CADENA_GRAFO_VALIDA))
			return Boolean.FALSE;
		
		return Boolean.TRUE;
	}
	
	/**
	 * Suma dos costes
	 * @param costeA
	 * @param costeB
	 * @return la suma de los costes A y B o INFITO si alguno de los costes es INFINITO
	 */
	public Integer sumarCostes(Integer costeA, Integer costeB) {
		if(costeA == Constantes.INFINITO || costeB == Constantes.INFINITO)
			return Constantes.INFINITO;
		else
			return costeA + costeB;
	}
	
	/**
	 * Comprueba que el grago no sea nulo, al igual que su matriz de adyacencia
	 * @param grafo
	 * @throws CustomException
	 */
	public void validarGrafo(GrafoPonderado grafo) throws CustomException{
		if(grafo == null)
			throw new CustomException(Constantes.ERROR_OBJETO_GRAFO_NULL);
		
		if(grafo.getMatrizAdy() == null || grafo.getNumVertices() == 0)
			throw new CustomException(Constantes.ERROR_OBJETO_GRAFO_MATRIZ_NULL);
	}
	
	

}
