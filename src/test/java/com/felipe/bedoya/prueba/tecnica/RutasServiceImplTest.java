package com.felipe.bedoya.prueba.tecnica;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.felipe.bedoya.prueba.tecnica.dto.ResultadoRutasGrafoDTO;
import com.felipe.bedoya.prueba.tecnica.dto.SolicitudRutasDTO;
import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.services.AlgoritmosGrafosService;
import com.felipe.bedoya.prueba.tecnica.services.OperacionesGrafosFloydService;
import com.felipe.bedoya.prueba.tecnica.services.impl.RutasServiceImpl;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;

@RunWith(MockitoJUnitRunner.class)
public class RutasServiceImplTest {
	
	@InjectMocks
	private RutasServiceImpl rutasService;
	
	@Mock
	private AlgoritmosGrafosService algoritmosGrafosService;
	
	@Mock
	private OperacionesGrafosFloydService operacionesGrafosFloydService;
	
	@Mock
	private OperacionesComunes operacionesComunes;
	
	@Before
	public void setUp() {
	  MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void generarResultadoSolicitudRutas_OK() {
		
		//Solicitud
		SolicitudRutasDTO solicitud = UtilTest.getSolicitudrutasDTOMock();
		try {
			
			Integer matrizAdyacenteGrafo[][] = UtilTest.getMockMatrizGrafo(); 
				when(operacionesComunes.convertirCadenaGrafoAMatriz(solicitud.getGrafo())).thenReturn(matrizAdyacenteGrafo);
				
		    // No es necesario mockear la llamada a algoritmosGrafosService.calcularFloyd;
			
		    when(operacionesGrafosFloydService.calcularCosteRuta(any(), any(), any())).thenReturn(9,5,13,22,-1,-1);
			
			ResultadoRutasGrafoDTO resultado = rutasService.generarResultadoSolicitudRutas(solicitud);
			
			assertNotNull(resultado);
			assertNotNull(resultado.getListaRutasCosteSolicitadas());
			assertTrue(!resultado.getListaRutasCosteSolicitadas().isEmpty());
			assertEquals(resultado.getListaRutasCosteSolicitadas().size(), solicitud.getListaRutasGrafos().size());
			
			assertEquals(resultado.getListaRutasCosteSolicitadas().get(0).getRuta(), solicitud.getListaRutasGrafos().get(0));
			assertEquals(resultado.getListaRutasCosteSolicitadas().get(0).getCoste(), "9");
			
			assertEquals(resultado.getListaRutasCosteSolicitadas().get(4).getRuta(), solicitud.getListaRutasGrafos().get(4));
			assertEquals(resultado.getListaRutasCosteSolicitadas().get(4).getCoste(), Constantes.NO_SUCH_ROUTE);
			
			assertNotNull(resultado.getMatrizGrafo());
			assertNull(resultado.getMensaje());
			
		}catch(CustomException c) {
			
		}
	}
	
	@Test//Error al llamar a convertirCadenaGrafoAMatriz
	public void generarResultadoSolicitudRutas_KO() {
		
		//Solicitud
		SolicitudRutasDTO solicitud = UtilTest.getSolicitudrutasDTOMock();
		try {
			when(operacionesComunes.convertirCadenaGrafoAMatriz(solicitud.getGrafo())).thenThrow(new CustomException(Constantes.ERROR_CADENA_GRAFO_NULL_O_VACIA));
			rutasService.generarResultadoSolicitudRutas(solicitud);
			
		}catch(CustomException c) {
			assertThatExceptionOfType(CustomException.class);
			assertEquals(c.getMensaje(), Constantes.ERROR_CREAR_MATRIZ_CON_CADENA_GRAFO+Constantes.ERROR_CADENA_GRAFO_NULL_O_VACIA);
		}
	}

}
