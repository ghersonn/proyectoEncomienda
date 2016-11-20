package com.entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Envio {
	public int idEnvio;
	public int codigoGeneradoEnvio;
	public Date fechaEmisionEnvio;
	public Date fechaLlegadaEnvio;
	public Date fechaEntregaEnvio;
	public BigDecimal montoTotalEnvio = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
	public BigDecimal montoDescuento = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
	public Boolean estadoPagoEnvio;
	public String estadoEnvio;
	public Boolean estado;
	public Cliente remitenteEnvio;
	public Cliente destinatarioEnvio;
	public Ruta rutaEnvio;
	public Viaje viajeEnvio;
	public Usuario usuarioEnvio;
	
	ArrayList<Paquete> listPaquete = new ArrayList<Paquete>();

	public int getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}

	public int getCodigoGeneradoEnvio() {
		return codigoGeneradoEnvio;
	}

	public void setCodigoGeneradoEnvio(int codigoGeneradoEnvio) {
		this.codigoGeneradoEnvio = codigoGeneradoEnvio;
	}

	public Date getFechaEmisionEnvio() {
		return fechaEmisionEnvio;
	}

	public void setFechaEmisionEnvio(Date fechaEmisionEnvio) {
		this.fechaEmisionEnvio = fechaEmisionEnvio;
	}

	public Date getFechaLlegadaEnvio() {
		return fechaLlegadaEnvio;
	}

	public void setFechaLlegadaEnvio(Date fechaLlegadaEnvio) {
		this.fechaLlegadaEnvio = fechaLlegadaEnvio;
	}

	public Date getFechaEntregaEnvio() {
		return fechaEntregaEnvio;
	}

	public void setFechaEntregaEnvio(Date fechaEntregaEnvio) {
		this.fechaEntregaEnvio = fechaEntregaEnvio;
	}

	public BigDecimal getMontoTotalEnvio() {
		return montoTotalEnvio.setScale(2, RoundingMode.HALF_UP);
	}

	public void setMontoTotalEnvio(BigDecimal montoTotalEnvio) {
		this.montoTotalEnvio = montoTotalEnvio.setScale(2, RoundingMode.HALF_UP);
	}

	public Boolean getEstadoPagoEnvio() {
		return estadoPagoEnvio;
	}

	public void setEstadoPagoEnvio(Boolean estadoPagoEnvio) {
		this.estadoPagoEnvio = estadoPagoEnvio;
	}

	public String getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Cliente getRemitenteEnvio() {
		return remitenteEnvio;
	}

	public void setRemitenteEnvio(Cliente remitenteEnvio) {
		this.remitenteEnvio = remitenteEnvio;
	}

	public Cliente getDestinatarioEnvio() {
		return destinatarioEnvio;
	}

	public void setDestinatarioEnvio(Cliente destinatarioEnvio) {
		this.destinatarioEnvio = destinatarioEnvio;
	}

	public Ruta getRutaEnvio() {
		return rutaEnvio;
	}

	public void setRutaEnvio(Ruta rutaEnvio) {
		this.rutaEnvio = rutaEnvio;
	}

	public Viaje getViajeEnvio() {
		return viajeEnvio;
	}

	public void setViajeEnvio(Viaje viajeEnvio) {
		this.viajeEnvio = viajeEnvio;
	}

	public Usuario getUsuarioEnvio() {
		return usuarioEnvio;
	}

	public void setUsuarioEnvio(Usuario usuarioEnvio) {
		this.usuarioEnvio = usuarioEnvio;
	}

	public ArrayList<Paquete> getListPaquete() {
		return listPaquete;
	}

	public void setAddPaquete(Paquete p) {
		
		BigDecimal precio = p.pesoPaquete.multiply(rutaEnvio.getPrecioRuta());
		if(p.fragilPaquete) p.precioPaquete = precio.multiply(new BigDecimal(1.5)).setScale(2, RoundingMode.HALF_UP);
		else  p.precioPaquete = precio.setScale(2, RoundingMode.HALF_UP);	
		this.listPaquete.add(p);
	}
	
	public BigDecimal getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}
	
	public void actualizarPrecioPaquete(){
		for (Paquete paquete : listPaquete) {
			BigDecimal precio = paquete.pesoPaquete.multiply(rutaEnvio.getPrecioRuta());
			if(paquete.fragilPaquete) paquete.precioPaquete = precio.multiply(new BigDecimal(1.5)).setScale(2, RoundingMode.HALF_UP);
			else  paquete.precioPaquete = precio.setScale(2, RoundingMode.HALF_UP);	
		}
	}
	
	public void actualizarTotal(){
		BigDecimal precioTotal = new BigDecimal(0);
		for (Paquete paquete : listPaquete) {
			precioTotal= precioTotal.add(paquete.getPrecioPaquete());	
		}
		montoTotalEnvio = precioTotal.setScale(2, RoundingMode.HALF_UP);
		actualizarDescuento();
	}
	
	public void actualizarDescuento(){
		int numeroPaquete = listPaquete.size();
		
		if(numeroPaquete>2){
			montoDescuento = montoTotalEnvio.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP));
			montoTotalEnvio = montoTotalEnvio.setScale(2, RoundingMode.HALF_UP).subtract(montoDescuento.setScale(2, RoundingMode.HALF_UP));
		}
	}
	
	
	
}
