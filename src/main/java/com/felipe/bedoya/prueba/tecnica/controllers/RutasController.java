package com.felipe.bedoya.prueba.tecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felipe.bedoya.prueba.tecnica.dto.ResultadoRutasGrafoDTO;
import com.felipe.bedoya.prueba.tecnica.dto.SolicitudRutasDTO;
import com.felipe.bedoya.prueba.tecnica.excepctions.CustomException;
import com.felipe.bedoya.prueba.tecnica.services.RutasService;

@Controller
@RequestMapping("/")
public class RutasController {
	
	@Autowired
	private RutasService rutasService;
	
	@PostMapping("/calcular")
	public @ResponseBody ResultadoRutasGrafoDTO calcularRutas(@RequestBody SolicitudRutasDTO sol) {
		ResultadoRutasGrafoDTO resultado = null;
		
		try {
			resultado = rutasService.generarResultadoSolicitudRutas(sol);
		} catch (CustomException e) {
			resultado = new ResultadoRutasGrafoDTO();
			resultado.setMensaje(e.getMensaje());
			resultado.setGrafo(sol.getGrafo());
		}
		return resultado;
	}
	
	@GetMapping(path= {"/","inicio"})
	public String inicio(Model model) {
		
		return "index";
	}
	
	@GetMapping("/funcionamiento")
	public String funcionamiento(Model model) {
		
		return "funcionamiento";
	}

}
