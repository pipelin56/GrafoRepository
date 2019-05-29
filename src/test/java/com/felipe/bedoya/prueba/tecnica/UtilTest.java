package com.felipe.bedoya.prueba.tecnica;

import java.util.ArrayList;
import java.util.List;

import com.felipe.bedoya.prueba.tecnica.dto.SolicitudRutasDTO;
import com.felipe.bedoya.prueba.tecnica.pojos.GrafoPonderado;
import com.felipe.bedoya.prueba.tecnica.utils.Constantes;

public class UtilTest {
	
	public static Integer[][] getMockMatrizGrafo() {
		Integer m[][] = { 
				{Constantes.INFINITO, 5, Constantes.INFINITO, 5, 7},
				{Constantes.INFINITO, Constantes.INFINITO, 4, Constantes.INFINITO, Constantes.INFINITO},
				{Constantes.INFINITO, Constantes.INFINITO, Constantes.INFINITO, 8, 2},
				{Constantes.INFINITO, Constantes.INFINITO, 8, Constantes.INFINITO, 6},
				{Constantes.INFINITO, 3, Constantes.INFINITO, Constantes.INFINITO, Constantes.INFINITO}};
		return m;
	}
	public static Integer[][] getMockMatrizFloyd() {
		Integer m[][] = { 
				{0, 5, 9, 5, 7},
				{Constantes.INFINITO, 0, 4, 12, 6},
				{Constantes.INFINITO, 5, 0, 8, 2},
				{Constantes.INFINITO, 9, 8, 0, 6},
				{Constantes.INFINITO, 3, 7, 15, 0}};
		return m;
	}
	
	public static Integer[][] getMockMatrizCaminos() {
		Integer m[][] = { 
				{0, 0, 1, 0, 0},
				{1, 1, 1, 2, 2},
				{2, 4, 2, 2, 2},
				{3, 4, 3, 3, 3},
				{4, 4, 1, 2, 4}};
		return m;
	}
	
	public static SolicitudRutasDTO getSolicitudrutasDTOMock() {
		SolicitudRutasDTO solicitud = new SolicitudRutasDTO();
		solicitud.setGrafo("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		List<String> listaRutasGrafos = new ArrayList<>();
		listaRutasGrafos.add("A-B-C");
		listaRutasGrafos.add("A-D");
		listaRutasGrafos.add("A-D-C");
		listaRutasGrafos.add("A-E-B-C-D");
		listaRutasGrafos.add("A-E-D");
		listaRutasGrafos.add("B-A-D");
		solicitud.setListaRutasGrafos(listaRutasGrafos);
		return solicitud;
	}
	
	public static GrafoPonderado crearGrafoPonderadoMock() {
		return new GrafoPonderado(getMockMatrizGrafo());
	}
	
	public static String cadenaGrafoMock() {
		return "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
	}

}
