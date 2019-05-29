package com.felipe.bedoya.prueba.tecnica.services;

import com.felipe.bedoya.prueba.tecnica.dto.ResultadoRutasGrafoDTO;
import com.felipe.bedoya.prueba.tecnica.dto.SolicitudRutasDTO;
import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;

/**
 * Interfaz para definir el "contrato" de las operaciones con rutas a implementar. 
 * @author Felipe Bedoya Castaño
 *
 */
public interface RutasService {
	
	/**
	 * Devuelve un listado de costes asociados a las rutas que se solicita mediante un grafo definidido como string
	 * @param solicitud
	 * @return resultado de los costes asociados a las rutas
	 * @throws CustomException
	 */
	public ResultadoRutasGrafoDTO generarResultadoSolicitudRutas(SolicitudRutasDTO solicitud) throws CustomException; 

}
