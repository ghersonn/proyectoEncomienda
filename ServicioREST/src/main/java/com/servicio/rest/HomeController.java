package com.servicio.rest;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entidades.Envio;
import com.entidades.Usuario;
import com.negocio.NEGEnvio;
import com.negocio.NEGUsuario;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/ListarEnvioEstadoL", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ArrayList<Envio> ListaEnvioL() {		
		ArrayList<Envio> lista = new ArrayList<Envio>();
		try {
			lista = NEGEnvio.Instancia().listarEnvioEstadoL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	@RequestMapping(value = "/VerificarAcceso", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Usuario VerificarAcceso(String usu, String pass ) {		
		 Usuario u = new Usuario();
		try {
			u = NEGUsuario.Instancia().VerificarAcceso(usu, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	@RequestMapping(value = "/ActualizarEnvioEstadoX", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Envio ActualizarEnvioEstadoX(int codigoEnvio) {
		Envio en = new Envio();
		try {
				if(NEGEnvio.Instancia().actualizarEnvioEstadoX(codigoEnvio)){
					en.setEstado(true);
				}
				else{
					en.setEstado(false);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return en;
	}
	
}
