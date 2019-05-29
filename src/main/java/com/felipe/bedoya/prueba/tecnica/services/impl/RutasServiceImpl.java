package com.felipe.bedoya.prueba.tecnica.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.bedoya.prueba.tecnica.dto.ResultadoRutasGrafoDTO;
import com.felipe.bedoya.prueba.tecnica.dto.RutaCosteDTO;
import com.felipe.bedoya.prueba.tecnica.dto.SolicitudRutasDTO;
import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;
import com.felipe.bedoya.prueba.tecnica.services.AlgoritmosGrafosService;
import com.felipe.bedoya.prueba.tecnica.services.OperacionesGrafosFloydService;
import com.felipe.bedoya.prueba.tecnica.services.RutasService;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;
import com.felipe.bedoya.prueba.tecnica.utils.OperacionesComunes;


/***
 * Servicio para la implementación de las operaciones con rutas establecidas en la interfaz RutasService.
 * @author Felipe Bedoya Castaño
 *
 */
@Service
public class RutasServiceImpl implements RutasService{
	
	@Autowired
	private AlgoritmosGrafosService algoritmosGrafosService;
	
	@Autowired
	private OperacionesGrafosFloydService operacionesFlody;
	
	@Autowired
	private OperacionesComunes operacionesComunes;

	@Override
	public ResultadoRutasGrafoDTO generarResultadoSolicitudRutas(SolicitudRutasDTO solicitud) throws CustomException {
		
		//Creación del objeto resultado
		ResultadoRutasGrafoDTO resultado = new ResultadoRutasGrafoDTO();
		resultado.setGrafo(solicitud.getGrafo());
		
		//Generación de la matriz de adyacencia a partir de la cadena indicada por el usuario
		Integer matrizAdyacenteGrafo[][] = null;
		try {
			matrizAdyacenteGrafo = operacionesComunes.convertirCadenaGrafoAMatriz(solicitud.getGrafo()); 			
		}catch(CustomException c) {
			throw new CustomException(Constantes.ERROR_CREAR_MATRIZ_CON_CADENA_GRAFO + c.getMensaje());
		}
		
		GrafoPonderado grafo = new GrafoPonderado(matrizAdyacenteGrafo);
		
		Integer[][] matrizResultadoCaminos = new Integer[grafo.getNumVertices()][grafo.getNumVertices()];
		try {
			algoritmosGrafosService.calcularFloyd(grafo , matrizResultadoCaminos);
		}catch(CustomException c) {
			throw new CustomException(Constantes.ERROR_ALGORITMO_FLOYD + c.getMensaje());
		}
		
		List<RutaCosteDTO> listaRutasCosteSolicitadas = new ArrayList<>();
		//Se recorre la lista de rutas que indicó el usuario para calcular su coste y, tras calcularo 
		//se añade al lisado  listaRutasCosteSolicitadas que irá en el objeto de salida.
		for(String ruta : solicitud.getListaRutasGrafos()) {
			if(ruta.trim().equals(""))
				continue;
				
			RutaCosteDTO rutaCoste = new RutaCosteDTO();
			Integer coste = null;
			try {
				coste = operacionesFlody.calcularCosteRuta(ruta.toUpperCase(), matrizResultadoCaminos, matrizAdyacenteGrafo);
			}catch(CustomException c) {
				throw new CustomException(Constantes.ERROR_CALCULANDO_RUTAS + c.getMensaje());
			}
			//Coste de la ruta
			if(coste.equals(Constantes.NO_RUTA))
				rutaCoste.setCoste(Constantes.NO_SUCH_ROUTE);
			else if(coste.equals(Constantes.NO_RUTA_VALIDA))
				rutaCoste.setCoste(Constantes.INVALID_ROUTE);
			else
				rutaCoste.setCoste(coste.toString());
			//Ruta
			rutaCoste.setRuta(ruta.toUpperCase());
			listaRutasCosteSolicitadas.add(rutaCoste);
		}
		
		//Completamos objeto de salida
		resultado.setMatrizGrafo(matrizAdyacenteGrafo);
		resultado.setListaRutasCosteSolicitadas(listaRutasCosteSolicitadas);
		
		return resultado;
	}

}
