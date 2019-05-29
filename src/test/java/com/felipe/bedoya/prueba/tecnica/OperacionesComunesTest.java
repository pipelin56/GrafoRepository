package com.felipe.bedoya.prueba.tecnica;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;

@RunWith(MockitoJUnitRunner.class)
public class OperacionesComunesTest {
	
	@InjectMocks
	private OperacionesComunes operaciones;
	

	@Before
	public void setUp() {
	  MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void calcularIndiceVerticeMedianteLetraChar_OK() {
		
		Character letra = 'A';
		
		try {
			Integer indice =  operaciones.calcularIndiceVerticeMedianteLetraChar(letra);
			assertNotNull(indice);
			Integer indiceEsperado = Integer.valueOf((int) letra.charValue()) - Constantes.CHAR_INICIAL;
			assertEquals(indice, indiceEsperado);
		} catch (CustomException e) {
		}
	}
	
	@Test
	public void calcularIndiceVerticeMedianteLetraChar_KO() {
		
		Character letra = '&';
		try {
			operaciones.calcularIndiceVerticeMedianteLetraChar(letra);
		} catch (CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_PARAMETRO_NO_ES_LETRA);
		}
	}
	
	@Test
	public void convertirCadenaGrafoAMatriz_OK() {
		String cadenaGrafo = UtilTest.cadenaGrafoMock();
		Integer[][] matrizEsperada = UtilTest.getMockMatrizGrafo();
		try {
			Integer[][] resultado = operaciones.convertirCadenaGrafoAMatriz(cadenaGrafo);
			
			//Comprobacion
			for(int i=0; i<matrizEsperada.length; i++)
				for(int j=0; j<matrizEsperada.length; j++)
					assertEquals(resultado[i][j], matrizEsperada[i][j]);
		} catch (CustomException c) {
		}
		
	}
	
	@Test//Llamamos a la función con un parametro nulo
	public void convertirCadenaGrafoAMatriz_Cadena_Grafo_NULL_KO() {
		try {
			operaciones.convertirCadenaGrafoAMatriz(null);
		} catch (CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_CADENA_GRAFO_NULL_O_VACIA);
		}
		
	}
	
	@Test//Llamamos a la función con un parametro cuyo formato no es el esperado
	public void convertirCadenaGrafoAMatriz_Cadena_Grafo_Error_Formato_KO() {
		try {
			String grafoCadena = ",";
			operaciones.convertirCadenaGrafoAMatriz(grafoCadena);
		} catch (CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_CADENA_GRAFO_FORMATO_NO_VALIDO);
		}
		
	}
	
	@Test//Llamamos a la función con un parametro cuyo formato no es el esperado
	public void convertirCadenaGrafoAMatriz_Cadena_Grafo_Error_Formato_2_KO() {
		try {
			String grafoCadena = "sdfert,dfgfg";
			operaciones.convertirCadenaGrafoAMatriz(grafoCadena);
		} catch (CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_CADENA_GRAFO_FORMATO_NO_VALIDO);
		}
		
	}
	
	@Test //Test suma de un coste valido con infinito
	public void sumarCostes_OK() {
		
		Integer costeTotal = operaciones.sumarCostes(9, Constantes.INFINITO);
		assertNotNull(costeTotal);
		assertEquals(costeTotal, Constantes.INFINITO);
	}
	
	@Test //Test suma de dos coste validos
	public void sumarCostes_OK2() {
		Integer coste1 =5, coste2=8;
		Integer costeEsperado = coste1+coste2;
		Integer costeTotal = operaciones.sumarCostes(coste1, coste2);
		assertNotNull(costeTotal);
		assertEquals(costeTotal, costeEsperado);
	}
	
	@Test//Grafo valido
	public void validarGrafo_OK() {
		GrafoPonderado grafo = UtilTest.crearGrafoPonderadoMock();
		try {
			operaciones.validarGrafo(grafo);
			
		} catch (CustomException e) {
		}
		
	}
	
	@Test//Grafo valido
	public void validarGrafo_NULL_KO() {
		GrafoPonderado grafo = null;
		try {
			operaciones.validarGrafo(grafo);
			
		} catch (CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_OBJETO_GRAFO_NULL);
		}
		
	}
	
	
	
}
