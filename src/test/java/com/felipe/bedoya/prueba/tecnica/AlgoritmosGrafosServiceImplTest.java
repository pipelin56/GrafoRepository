package com.felipe.bedoya.prueba.tecnica;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;
import com.felipe.bedoya.prueba.tecnica.services.impl.AlgoritmosGrafosServiceImpl;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;

@RunWith(MockitoJUnitRunner.class)
public class AlgoritmosGrafosServiceImplTest {
	
	@InjectMocks
	private AlgoritmosGrafosServiceImpl algoritmoGrafosServiceImpl;
	
	@Mock
	private OperacionesComunes operacionesComunes;
	
	@Before
	public void setUp() {
	  MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void calcularFloyd_OK() {
		
		try {
			GrafoPonderado grafo = UtilTest.crearGrafoPonderadoMock();
			Integer[][] matrizResultadoCaminos = new Integer[grafo.getNumVertices()][grafo.getNumVertices()];
			
			when(operacionesComunes.sumarCostes(any(),any())).thenReturn(any(Integer.class));
			
			Integer[][] matrizFloyd= algoritmoGrafosServiceImpl.calcularFloyd(grafo, matrizResultadoCaminos );
			assertNotNull(matrizFloyd);
			assertEquals(matrizFloyd.length, grafo.getMatrizAdy().length);
		}catch(CustomException c) {
			
		}
		
	}
	
	@Test//Grafo null
	public void calcularFloyd_KO() {
		
		try {
			GrafoPonderado grafo = null;
			
			doThrow(new CustomException(Constantes.ERROR_OBJETO_GRAFO_NULL)).when(operacionesComunes).validarGrafo(grafo);
			
			algoritmoGrafosServiceImpl.calcularFloyd(grafo, new Integer[0][0] );
		}catch(CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_OBJETO_GRAFO_NULL);
		}
		
	}

}
