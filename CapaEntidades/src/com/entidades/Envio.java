package com.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Envio {
	public int idEnvio;
	public int codigoGeneradoEnvio;
	public Date fechaEmisionEnvio;
	public Date fechaLlegadaEnvio;
	public Date fechaEntregaEnvio;
	public BigDecimal montoTotalEnvio;
	public Boolean estadoPagoEnvio;
	public String estadoEnvio;
	public Boolean estado;
	public Cliente remitenteEnvio;
	public Cliente destinatarioEnvio;
	public Ruta rutaEnvio;
	public Viaje viajeEnvio;
	public Usuario usuarioEnvio;
	
	public List<Paquete> listPaquete;

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
		return montoTotalEnvio;
	}

	public void setMontoTotalEnvio(BigDecimal montoTotalEnvio) {
		this.montoTotalEnvio = montoTotalEnvio;
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

	public List<Paquete> getListPaquete() {
		return listPaquete;
	}

	public void setAddPaquete(Paquete p) {
		this.listPaquete.add(p);
	}
}
