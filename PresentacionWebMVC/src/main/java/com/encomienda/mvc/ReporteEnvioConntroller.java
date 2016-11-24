package com.encomienda.mvc;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Cliente;
import com.entidades.Envio;
import com.negocio.NEGCliente;
import com.negocio.NEGEnvio;

@Controller
public class ReporteEnvioConntroller {
	
	@RequestMapping(value = "/ReporteEnvio", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		return new ModelAndView("reporteEnvio", "command", new Envio());
	}
	
	
	
	
}
