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
import com.negocio.NEGCliente;
import com.negocio.NEGEnvio;
import com.negocio.NEGRuta;
import com.negocio.NEGUnidadTransporte;
import com.negocio.NEGViaje;

@SessionAttributes("objEnvio")
@Controller
public class ViajeController {
	
	@RequestMapping(value = "/realizarViaje", method = RequestMethod.GET)
	public ModelAndView realizarViaje(ModelMap model) {	

		Envio e = new Envio();
		
		try {
			
			//model.addAttribute("modelRemitente", new Cliente());
			//model.addAttribute("modelDestinatario", new Cliente());
			model.addAttribute("modelViaje", new Viaje());
			model.addAttribute("modelUnidadTransporte", new UnidadTransporte());
			
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
		  ArrayList<Envio> listEnvio = NEGEnvio.Instancia().listarEnvioEstadoR();
		  
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
			
			Viaje v = e.getViajeEnvio();
			e = NEGEnvio.Instancia().obtenerEnvio(e.getIdEnvio());
			e.setViajeEnvio(v);
			e.setRemitenteEnvio(NEGCliente.Instancia().obtenerClienteID(e.getRemitenteEnvio().getIdCliente()));
			e.setDestinatarioEnvio(NEGCliente.Instancia().obtenerClienteID(e.getDestinatarioEnvio().getIdCliente()));
			e.setRutaEnvio(NEGRuta.Instancia().obtenerRuta(e.getRutaEnvio().getIdRuta()));
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
			model.addAttribute("modelViaje", e.getViajeEnvio());
			
			return "viaje";
			
		} catch (ArithmeticException ex) {

			if(e==null || e.getViajeEnvio()==null){
				e = new Envio();
				Viaje objViaje = new Viaje();
				objViaje.setUnidadTransporteViaje(new UnidadTransporte());
				objViaje.setRutaViaje(new Ruta());
				
				e.setRutaEnvio(new Ruta());
				e.setViajeEnvio(objViaje);
			}
			else if(e.getViajeEnvio().getUnidadTransporteViaje()==null){
				e.getViajeEnvio().setUnidadTransporteViaje(new UnidadTransporte());
			}
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
			model.addAttribute("modelViaje", e.getViajeEnvio());
			
			return "viaje";
			
		} catch (Exception ex) {			
			
			if(e==null || e.getViajeEnvio()==null){
				e = new Envio();
				Viaje objViaje = new Viaje();
				objViaje.setUnidadTransporteViaje(new UnidadTransporte());
				objViaje.setRutaViaje(new Ruta());
				
				e.setRutaEnvio(new Ruta());
				e.setViajeEnvio(objViaje);
			}
			else if(e.getViajeEnvio().getUnidadTransporteViaje()==null){
				e.getViajeEnvio().setUnidadTransporteViaje(new UnidadTransporte());
			}
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
			model.addAttribute("modelViaje", e.getViajeEnvio());
			
			return "viaje";
		}
	}
	
	@ModelAttribute("listUnidadTransporte")
	 public Map<String,String> populateUnidadTransporteList() {
	   
	  //Data referencing for java skills list box
	  Map<String,String> unidadTransporte = new LinkedHashMap<String,String>();
	  
	  try {
		  ArrayList<UnidadTransporte> listUnidadTransporte = NEGUnidadTransporte.Instancia().listarUnidadTransporte();
		  
		  for (UnidadTransporte objUnidadTransporte : listUnidadTransporte) {
			  unidadTransporte.put(""+objUnidadTransporte.getIdUnidadTransporte()+"", ""+objUnidadTransporte.getMatriculaUnidadTransporte()+"");
		  }
		
	  } catch (Exception e) {
		  // TODO: handle exception
		  unidadTransporte.put("0", "-");
	  }
	  return unidadTransporte;
	 }
	
	
	@RequestMapping(value = "/AsignarUnidadTransporte", method = RequestMethod.POST)
	public String AsignarUnidadTransporte(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelUnidadTransporte")UnidadTransporte u,
			ModelMap model){
		try {
			
			u = NEGUnidadTransporte.Instancia().obtenerUnidadTransporte(u.getIdUnidadTransporte());
			e.getViajeEnvio().setUnidadTransporteViaje(u);
			
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
			model.addAttribute("modelViaje", e.getViajeEnvio());
			
			return "viaje";
			
		} catch (ArithmeticException ex) {
			if(e==null || e.getViajeEnvio()==null){
				e = new Envio();
				Viaje objViaje = new Viaje();
				objViaje.setUnidadTransporteViaje(new UnidadTransporte());
				objViaje.setRutaViaje(new Ruta());
				
				e.setRutaEnvio(new Ruta());
				e.setViajeEnvio(objViaje);
			}
			else if(e.getViajeEnvio().getUnidadTransporteViaje()==null){
				e.getViajeEnvio().setUnidadTransporteViaje(new UnidadTransporte());
			}
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
			model.addAttribute("modelViaje", e.getViajeEnvio());
			
			return "viaje";
			
		} catch (Exception ex) {
			if(e==null || e.getViajeEnvio()==null){
				e = new Envio();
				Viaje objViaje = new Viaje();
				objViaje.setUnidadTransporteViaje(new UnidadTransporte());
				objViaje.setRutaViaje(new Ruta());
				
				e.setRutaEnvio(new Ruta());
				e.setViajeEnvio(objViaje);
			}
			else if(e.getViajeEnvio().getUnidadTransporteViaje()==null){
				e.getViajeEnvio().setUnidadTransporteViaje(new UnidadTransporte());
			}
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
			model.addAttribute("modelViaje", e.getViajeEnvio());
			
			return "viaje";
		}
	}
	
	@RequestMapping(value = "/GrabarViaje", method = RequestMethod.POST)
	public String GrabarViaje(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelViaje")Viaje v,
			ModelMap model){
		try {
			
			//Hacer busqueda de Ruta por Id
			//if(r.getIdRuta()<=2) r.setPrecioRuta(new BigDecimal(2.00)); //Le asigno un precio temporal
			//else r.setPrecioRuta(new BigDecimal(3.00)); //Le asigno un precio temporal
			//e.rutaEnvio = r;
			e.getViajeEnvio().setFechaEnvioViaje(v.getFechaEnvioViaje());			
			
			Boolean verificar = NEGViaje.Instancia().insertarViaje(e);
			if(verificar){
				
				Viaje objViaje = new Viaje();
				objViaje.setUnidadTransporteViaje(new UnidadTransporte());
				objViaje.setRutaViaje(new Ruta());
				Envio ee = new Envio();
				ee.setRutaEnvio(new Ruta());
				ee.setViajeEnvio(objViaje);
				
				model.addAttribute("error", "<div class=\"alert alert-success\" role=\"alert\">Se asigno transporte</div>");
				model.addAttribute("objEnvio", ee);
				model.addAttribute("modelViaje", new Viaje());
				model.addAttribute("modelUnidadTransporte", new UnidadTransporte());
				
			}else{
				model.addAttribute("objEnvio", e);
				model.addAttribute("modelUnidadTransporte", e.getViajeEnvio().getUnidadTransporteViaje());
				model.addAttribute("modelViaje", e.getViajeEnvio());
				
				model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">Error al asignar transporte</div>");
			}
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
