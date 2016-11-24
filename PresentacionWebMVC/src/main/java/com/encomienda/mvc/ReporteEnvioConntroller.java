package com.encomienda.mvc;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Cliente;
import com.entidades.Envio;
import com.entidades.Paquete;
import com.entidades.Usuario;
import com.negocio.NEGCliente;
import com.negocio.NEGEnvio;
import com.negocio.NEGPaquete;

@SessionAttributes("listaReporteEnvio")
@Controller
public class ReporteEnvioConntroller {
	
	
	@RequestMapping(value = "/ReporteEnvio", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		
		model.addAttribute("listaReporteEnvio",new ArrayList<Envio>());
		return new ModelAndView("reporteEnvio", "commandEnvio", new Envio());
		
	}
	
	@RequestMapping(value = "/ReporteEnvio2", method = RequestMethod.POST)
	public String reporteEnvio2(@ModelAttribute("commandEnvio")Envio e,
			/*@ModelAttribute("modelRemitente")Cliente c, */
			ModelMap model){
		try {
			ArrayList<Envio> listEnvio =new ArrayList<Envio>();
			listEnvio=NEGEnvio.Instancia().reporteEnvio(e.getFechaEmisionEnvio());
						
			model.addAttribute("listaReporteEnvio",listEnvio);
						
			return "reporteEnvio";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
		
			return "reporteEnvio";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());		
			return "reporteEnvio";
		}
	}
	
	@RequestMapping(value = "/mostrarDetalleEnvio", method = RequestMethod.POST)
	public String detalleEnvio(@ModelAttribute("commandEnvio")Envio e,
			@ModelAttribute("listaReporteEnvio")ArrayList<Envio> listEnvio , 
			ModelMap model){
		try {
			ArrayList<Paquete> listPaquete =new ArrayList<Paquete>();
			listPaquete=NEGPaquete.Instancia().listaPaqueteEnvio(e.getIdEnvio());
			
			model.addAttribute("listaReporteEnvio",listEnvio);
			model.addAttribute("listaPaqueteEnvio",listPaquete);
			
			return "reporteEnvio";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
		
			return "reporteEnvio";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());		
			return "reporteEnvio";
		}
	}
	
	
}
