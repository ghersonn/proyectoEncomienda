package com.encomienda.mvc;

import java.util.Locale;

import javax.jws.WebParam.Mode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Cliente;
import com.negocio.NEGCliente;

@Controller
public class ClienteController {

	@RequestMapping(value = "/RegistroCliente", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		return new ModelAndView("registrarCliente", "command", new Cliente());
	}
	
	
	@RequestMapping(value = "/registrarCliente2", method = RequestMethod.POST)
	public String RegistrarUsuario(@ModelAttribute("SpringWeb")Cliente objCliente, 
								ModelMap model){
		try {
						
			NEGCliente.Instancia().insertarCliente(objCliente);
			
			model.addAttribute("command", new Cliente());
			return "principal";
		
		} catch (ArithmeticException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("command", new Cliente());
			return "principal";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "Error";
		}
	}
}
