package com.encomienda.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Cliente;
import com.entidades.Envio;
import com.entidades.Paquete;
import com.entidades.Ruta;
import com.entidades.Usuario;
import com.entidades.Viaje;
import com.negocio.NEGEnvio;
import com.negocio.NEGRuta;

@SessionAttributes("objEnvio")
@Controller
public class EnvioController {
	
	@RequestMapping(value = "/realizarEnvio", method = RequestMethod.GET)
	public ModelAndView realizarEnvio(ModelMap model) {	
		
		model.addAttribute("modelRemitente", new Cliente());
		model.addAttribute("modelDestinatario", new Cliente());
		model.addAttribute("modelPaquete", new Paquete());
		model.addAttribute("modelRuta", new Ruta());
		
		Envio e = new Envio();
		e.remitenteEnvio = new Cliente();
		e.destinatarioEnvio = new Cliente();
		e.rutaEnvio = new Ruta();
		e.viajeEnvio = new Viaje();
		
		return new ModelAndView("enviar", "objEnvio", e);
	}
	
	@RequestMapping(value = "/VerificarRemitente", method = RequestMethod.POST)
	public String VerificarRemitente(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelRemitente")Cliente c, 
			ModelMap model){
		try {
			e.remitenteEnvio = c;
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.remitenteEnvio);
			model.addAttribute("modelDestinatario", e.destinatarioEnvio);
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.rutaEnvio);
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());
			return "login";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());			
			return "login";
		}
	}
	
	@RequestMapping(value = "/VerificarDestinatario", method = RequestMethod.POST)
	public String VerificarDestinatario(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelDestinatario")Cliente c, 
			ModelMap model){
		try {
			e.destinatarioEnvio = c;
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.remitenteEnvio);
			model.addAttribute("modelDestinatario", e.destinatarioEnvio);
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.rutaEnvio);
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());
			return "login";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());			
			return "login";
		}
	}
	
	 @ModelAttribute("listRuta")
	 public Map<String,String> populateCountryList() {
	   
	  //Data referencing for java skills list box
	  Map<String,String> ruta = new LinkedHashMap<String,String>();
	  
	  try {
		  ArrayList<Ruta> listRuta = NEGRuta.Instancia().listarRuta();
		  
		  for (Ruta objRuta : listRuta) {
			  ruta.put(""+objRuta.getIdRuta()+"", ""+objRuta.getCiudadOrigen().getNombreCiudad()+" - "+objRuta.getCiudadDestino().getNombreCiudad()+"");
		  }
		
	  } catch (Exception e) {
		  // TODO: handle exception
		  ruta.put("0", "-");
	  }
	  return ruta;
	 }
	 
	@RequestMapping(value = "/AsignarRuta", method = RequestMethod.POST)
	public String AsignarRuta(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelRuta")Ruta r, 
			ModelMap model){
		try {
			
			//Hacer busqueda de Ruta por Id
			//if(r.getIdRuta()<=2) r.setPrecioRuta(new BigDecimal(2.00)); //Le asigno un precio temporal
			//else r.setPrecioRuta(new BigDecimal(3.00)); //Le asigno un precio temporal
			//e.rutaEnvio = r;
			
			e.rutaEnvio = NEGRuta.Instancia().obtenerRuta(r.getIdRuta());
			
			e.actualizarPrecioPaquete(); //actalizarPrecioPaquete
			e.actualizarTotal(); //actualizar Total
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.remitenteEnvio);
			model.addAttribute("modelDestinatario", e.destinatarioEnvio);
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.rutaEnvio);
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());
			return "login";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());			
			return "login";
		}
	}
	
	@RequestMapping(value = "/AgregarPaquete", method = RequestMethod.POST)
	public String AgregarPaquete(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelPaquete")Paquete p, 
			ModelMap model){
		try {
			e.setAddPaquete(p);
			e.actualizarTotal(); //actualizar Total
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.remitenteEnvio);
			model.addAttribute("modelDestinatario", e.destinatarioEnvio);
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.rutaEnvio);
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());
			return "login";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("cmdUsuario", new Usuario());			
			return "login";
		}
	}
	
	@RequestMapping(value = "/Enviar", method = RequestMethod.POST)
	public String Enviar(@ModelAttribute("objEnvio")Envio e, 
			ModelMap model){
		try {
			NEGEnvio.Instancia().enviar(e);
			//sesion usuario
			return "principal";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", ex.getMessage());
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.remitenteEnvio);
			model.addAttribute("modelDestinatario", e.destinatarioEnvio);
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.rutaEnvio);			
			return "enviar";
			
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.remitenteEnvio);
			model.addAttribute("modelDestinatario", e.destinatarioEnvio);
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.rutaEnvio);			
			return "enviar";
		}
	}
	
}
