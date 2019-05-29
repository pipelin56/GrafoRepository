package com.felipe.bedoya.prueba.tecnica.utils;

/**
 * Clase destinada a albergar todas las constantes de la aplicación
 * @author Felipe Bedoya Castaño
 *
 */
public class Constantes {
	
	private Constantes() {super();}
	
	
	//A = 65 a = 97
	public static final Integer CHAR_INICIAL = 65; //Es la A
	
	//Constantes para la matriz 
	public static final String SIMBOLO_INFINITO = "~";
	public static final Integer INFINITO = Integer.MAX_VALUE;

	public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
	public static final String INVALID_ROUTE = "INVALID ROUTE";

	public static final Integer NO_RUTA = -1;
	public static final Integer NO_RUTA_VALIDA = -2;

	//Errores
	public static final String ERROR_MATRIZ_CAMINOS_NULL = "Ways matrix can not be null.";

	public static final String ERROR_MATRIZ_DISCANCIAS_NULL = "Distances matrix can not be null.";

	public static final String ERROR_PARAMETRO_NO_ES_LETRA = "It was expected a letter.";

	public static final String ERROR_CADENA_GRAFO_NULL_O_VACIA = "String graph can not be null o empty.";
	
	public static final String ERROR_CADENA_GRAFO_FORMATO_NO_VALIDO = "String graph has not a valid format.";
	
	public static final String ERROR_CREAR_MATRIZ_CON_CADENA_GRAFO = "Error. Can not be created graph: ";
	
	public static final String ERROR_OBJETO_GRAFO_NULL = "Object graph can not be null.";
	
	public static final String ERROR_OBJETO_GRAFO_MATRIZ_NULL = "Matrix of Object graph can not be empty or null.";
	
	public static final String ERROR_ALGORITMO_FLOYD = "Error while Floyd's algorithm was executing: ";
	
	public static final String ERROR_CALCULANDO_RUTAS = "Error calculating specific routes: ";
	
	//Validacion con RegExp
	public static final String REG_EXP_CADENA_GRAFO_VALIDA = "[A-Z]{2}[1-9]{1}";

	
	
	//Cosntantes para mensajes de salida

}
