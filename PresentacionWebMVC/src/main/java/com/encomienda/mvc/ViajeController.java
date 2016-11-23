package com.encomienda.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.entidades.Cliente;
import com.entidades.Envio;
import com.entidades.Paquete;
import com.entidades.Ruta;
import com.entidades.UnidadTransporte;
import com.entidades.Usuario;
import com.entidades.Viaje;
import com.negocio.NEGEnvio;
import com.negocio.NEGRuta;

@SessionAttributes("objEnvio")
@Controller
public class ViajeController {
	
	@RequestMapping(value = "/realizarViaje", method = RequestMethod.GET)
	public ModelAndView realizarViaje(ModelMap model) {	

		Envio e = new Envio();
		
		try {
			
			//model.addAttribute("modelRemitente", new Cliente());
			//model.addAttribute("modelDestinatario", new Cliente());
			//model.addAttribute("modelPaquete", new Paquete());
			//model.addAttribute("modelRuta", new Ruta());
			
			Viaje objViaje = new Viaje();
			objViaje.setUnidadTransporteViaje(new UnidadTransporte());
			objViaje.setRutaViaje(new Ruta());
			
			e.setRutaEnvio(new Ruta());
			e.setViajeEnvio(objViaje);
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		return new ModelAndView("viaje", "objEnvio", e);
	}
	
	@ModelAttribute("listEnvio")
	 public Map<String,String> populateEnvioList() {
	   
	  //Data referencing for java skills list box
	  Map<String,String> envio = new LinkedHashMap<String,String>();
	  
	  try {
		  ArrayList<Envio> listEnvio = NEGEnvio.Instancia().listarEnvioEstadoR();;
		  
		  for (Envio objEnvio : listEnvio) {
			  envio.put(""+objEnvio.getIdEnvio()+"", "Codigo - "+objEnvio.getCodigoGeneradoEnvio()+"");
		  }
		
	  } catch (Exception e) {
		  // TODO: handle exception
		  envio.put("0", "-");
	  }
	  return envio;
	 }
	
	@RequestMapping(value = "/AsignarEnvioViaje", method = RequestMethod.POST)
	public String AsignarRuta(@ModelAttribute("objEnvio")Envio e,
			/*@ModelAttribute("modelRuta")Ruta r, */
			ModelMap model){
		try {
			
			//Hacer busqueda de Ruta por Id
			//if(r.getIdRuta()<=2) r.setPrecioRuta(new BigDecimal(2.00)); //Le asigno un precio temporal
			//else r.setPrecioRuta(new BigDecimal(3.00)); //Le asigno un precio temporal
			//e.rutaEnvio = r;
			
			e = NEGEnvio.Instancia().obtenerEnvio(e.getIdEnvio());
			
			model.addAttribute("objEnvio", e);
			//model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			//model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			//model.addAttribute("modelPaquete", new Paquete());
			//model.addAttribute("modelRuta", e.getRutaEnvio());
			
			return "viaje";
			
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
	
	
}
