package com.encomienda.mvc;

import java.lang.ProcessBuilder.Redirect;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Usuario;
import com.negocio.NEGUsuario;

@Controller
public class UsuarioController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {				
		return new ModelAndView("login", "cmdUsuario", new Usuario());
	}
	
	@RequestMapping(value = "/VerificarAcceso", method = RequestMethod.POST)
	public String VerificarAcceso(@ModelAttribute("cmdUsuario")Usuario u,ModelMap model){
		try {
			String _Usuario = u.getUserNameUsuario();
			String _Pass = u.getContraseniaUsuario();
			
			NEGUsuario.Instancia().VerificarAcceso(_Usuario,_Pass);
			//sesion usuario
			return "principal";
			
		} catch (ArithmeticException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());
			return "login";
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());			
			return "login";
		}
	}
}
