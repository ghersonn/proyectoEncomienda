package com.entidades;

import java.math.BigDecimal;

public class Ruta {
	
	private int idRuta;
	private BigDecimal precioRuta;
	private int diasDemoraRuta;
	private Boolean estadoRuta;
	private Ciudad ciudadOrigenRuta;
	private Ciudad ciudadDestinoRuta;
	
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public BigDecimal getPrecioRuta() {
		return precioRuta;
	}
	public void setPrecioRuta(BigDecimal precioRuta) {
		this.precioRuta = precioRuta;
	}
	public int getDiasDemoraRuta() {
		return diasDemoraRuta;
	}
	public void setDiasDemoraRuta(int diasDemoraRuta) {
		this.diasDemoraRuta = diasDemoraRuta;
	}
	public Boolean getEstadoRuta() {
		return estadoRuta;
	}
	public void setEstadoRuta(Boolean estadoRuta) {
		this.estadoRuta = estadoRuta;
	}
	public Ciudad getCiudadOrigenRuta() {
		return ciudadOrigenRuta;
	}
	public void setCiudadOrigenRuta(Ciudad ciudadOrigenRuta) {
		this.ciudadOrigenRuta = ciudadOrigenRuta;
	}
	public Ciudad getCiudadDestinoRuta() {
		return ciudadDestinoRuta;
	}
	public void setCiudadDestinoRuta(Ciudad ciudadDestinoRuta) {
		this.ciudadDestinoRuta = ciudadDestinoRuta;
	}
	
	

}
