package com.felipe.bedoya.prueba.tecnica;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.services.impl.OperacionesGrafosFloydServiceImpl;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;

@RunWith(MockitoJUnitRunner.class)
public class OperacionesGrafosFloydServiceImplTest {
	
	@InjectMocks
	private OperacionesGrafosFloydServiceImpl operacionesGrafosFloydServiceImpl;
	
	@Mock
	private OperacionesComunes operacionesComunes;
	
	@Before
	public void setUp() {
	  MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void calcularCosteRuta_Posible_Realizar_OK() {
		
		try {
			String ruta = "A-B-C";
			Integer[][] matrizResultadoCaminos = UtilTest.getMockMatrizCaminos();
			Integer[][] matrizResultadosCoste = UtilTest.getMockMatrizFloyd();
																				//A=0, B=1 y C=2
			when(operacionesComunes.calcularIndiceVerticeMedianteLetraChar(any())).thenReturn(0,1,2);
			
			Integer coste = operacionesGrafosFloydServiceImpl.calcularCosteRuta(ruta , matrizResultadoCaminos, matrizResultadosCoste );
			//El coste de la ruta A-B-C según el grafo mock sería 9
			assertNotNull(coste);
			assertEquals(coste, Integer.valueOf(9));
		}catch(CustomException c) {
			
		}
	}
	@Test
	public void calcularCosteRuta_No_Posible_Realizar_OK() {
		
		try {
			String ruta = "A-E-D";
			Integer[][] matrizResultadoCaminos = UtilTest.getMockMatrizCaminos();
			Integer[][] matrizResultadosCoste = UtilTest.getMockMatrizFloyd();
			//A=0, E=3 y D=4
			when(operacionesComunes.calcularIndiceVerticeMedianteLetraChar(any())).thenReturn(0,2,4);
			
			Integer coste = operacionesGrafosFloydServiceImpl.calcularCosteRuta(ruta , matrizResultadoCaminos, matrizResultadosCoste );
			//El coste de la ruta A-E-D según el grafo mock sería -1(no es posible realziar la ruta) 
			assertNotNull(coste);
			assertEquals(coste, Integer.valueOf(Constantes.NO_RUTA));
		}catch(CustomException c) {
			
		}
	}
	
	
	@Test//Ruta sin el formato esperado
	public void calcularCosteRuta_Ruta_Erronea_OK() {
		
		try {
			String ruta = "A-BC";
			Integer[][] matrizResultadoCaminos = UtilTest.getMockMatrizCaminos();
			Integer[][] matrizResultadosCoste = UtilTest.getMockMatrizFloyd();
			
			Integer coste = operacionesGrafosFloydServiceImpl.calcularCosteRuta(ruta , matrizResultadoCaminos, matrizResultadosCoste );
			//El coste de la ruta A-BC erronea genera un -2
			assertNotNull(coste);
			assertEquals(coste, Integer.valueOf(Constantes.NO_RUTA_VALIDA));
		}catch(CustomException c) {
			
		}
	}
	
	@Test
	public void calcularCosteRuta_Matriz_Costes_NULL_KO() {
		
		try {
			String ruta = "A-B-C";
			Integer[][] matrizResultadoCaminos = null;
			Integer[][] matrizResultadosCoste = UtilTest.getMockMatrizFloyd();
			
			operacionesGrafosFloydServiceImpl.calcularCosteRuta(ruta , matrizResultadoCaminos, matrizResultadosCoste );
		}catch(CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_MATRIZ_CAMINOS_NULL);
		}
	}
	
	@Test
	public void calcularCosteRuta_Matriz_Caminos_NULL_KO() {
		
		try {
			String ruta = "A-B-C";
			Integer[][] matrizResultadoCaminos = UtilTest.getMockMatrizCaminos();
			Integer[][] matrizResultadosCoste = null;
			
			operacionesGrafosFloydServiceImpl.calcularCosteRuta(ruta , matrizResultadoCaminos, matrizResultadosCoste );
		}catch(CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_MATRIZ_DISCANCIAS_NULL);
		}
	}
	
	@Test
	public void construirCaminoIntermedio_OK() {
		
		Integer verticeOrigen = 0;//Vertice A
		Integer verticeDestino = 2; //Vertice C
		Integer[][] matrizResultadoCaminos = UtilTest.getMockMatrizCaminos();
		List<Integer> listaRuta = operacionesGrafosFloydServiceImpl.construirCaminoIntermedio(verticeOrigen, verticeDestino, matrizResultadoCaminos);
		
		//Buscamos el camino intermedio entre el vertice A y el Vertice C, el cual seria el vertice B, por tanto:
		assertNotNull(listaRuta);
		assertEquals(listaRuta.size(), 1);//Solo hay un vertice en el camino intermedio, por tanto solo estara el vertice 1 que es B.
		assertEquals(listaRuta.get(0), Integer.valueOf(1));
		
	}
	
	@Test
	public void construirCamino_OK() {
		
		Integer verticeOrigen = 0;//Vertice A
		Integer verticeDestino = 2; //Vertice C
		Integer[][] matrizResultadoCaminos = UtilTest.getMockMatrizCaminos();
		List<Integer> listaRuta = operacionesGrafosFloydServiceImpl.construirCamino(verticeOrigen, verticeDestino, matrizResultadoCaminos);
		
		//Buscamos el camino intermedio entre el vertice A y el Vertice C, el cual seria el vertice B, por tanto:
		assertNotNull(listaRuta);
		assertEquals(listaRuta.size(), 3);//Solo hay un vertice en el camino intermedio(B), por tanto, 
		//la lista debe tener los indices de los vertices A,B y C en el mismo orden
		assertEquals(listaRuta.get(0), Integer.valueOf(0));
		assertEquals(listaRuta.get(1), Integer.valueOf(1));
		assertEquals(listaRuta.get(2), Integer.valueOf(2));
		
	}
	

}
