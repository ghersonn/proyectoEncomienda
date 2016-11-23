package com.encomienda.mvc;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Cliente;
import com.entidades.Envio;
import com.entidades.Paquete;
import com.entidades.Ruta;
import com.entidades.Viaje;

@Controller
public class ViajeController {
	
	@RequestMapping(value = "/realizarViaje", method = RequestMethod.GET)
	public ModelAndView realizarEnvio(ModelMap model) {	
		
		model.addAttribute("modelRemitente", new Cliente());
		model.addAttribute("modelDestinatario", new Cliente());
		model.addAttribute("modelPaquete", new Paquete());
		model.addAttribute("modelRuta", new Ruta());
		
		Envio e = new Envio();
		e.setFechaEmisionEnvio(new Date());
		e.setRemitenteEnvio(new Cliente());
		e.setDestinatarioEnvio(new Cliente());
		e.setRutaEnvio(new Ruta());
		e.setViajeEnvio(new Viaje());
		
		return new ModelAndView("enviar", "objEnvio", e);
	}
}
