package com.entidades;

import java.util.Date;

public class Viaje {
	public int idViaje;
	public Date fechaEnvioViaje;
	public Boolean estadoViaje;
	public UnidadTransporte unidadTransporteViaje;
	public Ruta rutaViaje;
	
	
	public int getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}
	public Date getFechaEnvioViaje() {
		return fechaEnvioViaje;
	}
	public void setFechaEnvioViaje(Date fechaEnvioViaje) {
		this.fechaEnvioViaje = fechaEnvioViaje;
	}
	public Boolean getEstadoViaje() {
		return estadoViaje;
	}
	public void setEstadoViaje(Boolean estadoViaje) {
		this.estadoViaje = estadoViaje;
	}
	public UnidadTransporte getUnidadTransporteViaje() {
		return unidadTransporteViaje;
	}
	public void setUnidadTransporteViaje(UnidadTransporte unidadTransporteViaje) {
		this.unidadTransporteViaje = unidadTransporteViaje;
	}
	public Ruta getRutaViaje() {
		return rutaViaje;
	}
	public void setRutaViaje(Ruta rutaViaje) {
		this.rutaViaje = rutaViaje;
	}
	
	

}
