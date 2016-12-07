package com.encomienda.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.negocio.NEGCliente;
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
		e.setFechaEmisionEnvio(new Date());
		e.setRemitenteEnvio(new Cliente());
		e.setDestinatarioEnvio(new Cliente());
		e.setRutaEnvio(new Ruta());
		e.setViajeEnvio(new Viaje());
		
		return new ModelAndView("enviar", "objEnvio", e);
	}
	
	@RequestMapping(value = "/VerificarRemitente", method = RequestMethod.POST)
	public String VerificarRemitente(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelRemitente")Cliente c, 
			ModelMap model){
		try {
			c = NEGCliente.Instancia().obtenerClienteDNI(c.getDniCliente());
			e.setRemitenteEnvio(c);
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
			
		} catch (Exception ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
		}
	}
	
	@RequestMapping(value = "/VerificarDestinatario", method = RequestMethod.POST)
	public String VerificarDestinatario(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelDestinatario")Cliente c, 
			ModelMap model){
		try {
			c = NEGCliente.Instancia().obtenerClienteDNI(c.getDniCliente());
			e.setDestinatarioEnvio(c);
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
			
		} catch (Exception ex) {
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
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
						
			e.rutaEnvio = NEGRuta.Instancia().obtenerRuta(r.getIdRuta());
			
			e.actualizarPrecioPaquete(); //actalizarPrecioPaquete
			e.actualizarTotal(); //actualizar Total
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
			
		} catch (Exception ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
		}
	}
	
	 public Date sumarDiasFecha(Date fecha, int dias){
		       Calendar calendar = Calendar.getInstance();
		       calendar.setTime(fecha); // Configuramos la fecha que se recibe
		       calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		       return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}
	
	@RequestMapping(value = "/AgregarPaquete", method = RequestMethod.POST)
	public String AgregarPaquete(@ModelAttribute("objEnvio")Envio e,
			@ModelAttribute("modelPaquete")Paquete p, 
			ModelMap model){
		try {
			
			if(p.getPesoPaquete()==null || p.getDescripcionPaquete().equals("") || p.getFragilPaquete()==null){throw new ArithmeticException("Ingrese todos los datos del Paquete");}
			if(e.getRutaEnvio().getIdRuta()<=0){throw new ArithmeticException("Asigne una ruta al envio");}
			e.setAddPaquete(p);
			e.actualizarTotal(); //actualizar Total
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
			
		} catch (Exception ex) {
			
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
		}
	}
	
	@RequestMapping(value = "/Enviar", method = RequestMethod.POST)
	public String Enviar(@ModelAttribute("objEnvio")Envio e, 
			ModelMap model){
		try {
			//Asignar la Fecha de entrega
			e.setFechaLlegadaEnvio(sumarDiasFecha(e.getFechaEmisionEnvio(),e.getRutaEnvio().getDiasDemoraRuta()+1));
			
			//Realizar el envio
			NEGEnvio.Instancia().enviar(e);
			
			//sesion usuario
			//Limpiar campos
			e = new Envio();
			e.setFechaEmisionEnvio(new Date());
			e.setRemitenteEnvio(new Cliente());
			e.setDestinatarioEnvio(new Cliente());
			e.setRutaEnvio(new Ruta());
			e.setViajeEnvio(new Viaje());
			
			model.addAttribute("error", "<div class=\"alert alert-success\" role=\"alert\">Encomienda registrada</div>");
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", new Cliente());
			model.addAttribute("modelDestinatario", new Cliente());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", new Ruta());
			
			return "enviar";
			
		} catch (ArithmeticException ex) {
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			
			model.addAttribute("objEnvio", e);
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
			
		} catch (Exception ex) {
			model.addAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">"+ex.getMessage()+"</div>");
			
			model.addAttribute("modelRemitente", e.getRemitenteEnvio());
			model.addAttribute("modelDestinatario", e.getDestinatarioEnvio());
			model.addAttribute("modelPaquete", new Paquete());
			model.addAttribute("modelRuta", e.getRutaEnvio());			
			return "enviar";
		}
	}
	
}
