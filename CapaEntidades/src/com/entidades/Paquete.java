package com.entidades;

import java.math.BigDecimal;

public class Paquete {
	public int idPaquete;
	public String descripcionPaquete;
	public BigDecimal pesoPaquete;
	public BigDecimal precioPaquete;
	public Boolean fragilPaquete;
	public Boolean estadoPaquete;
	//public Envio envioPaquete;
	
	public int getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}
	public String getDescripcionPaquete() {
		return descripcionPaquete;
	}
	public void setDescripcionPaquete(String descripcionPaquete) {
		this.descripcionPaquete = descripcionPaquete;
	}
	public BigDecimal getPesoPaquete() {
		return pesoPaquete;
	}
	public void setPesoPaquete(BigDecimal pesoPaquete) {
		this.pesoPaquete = pesoPaquete;
	}
	public BigDecimal getPrecioPaquete() {
		return precioPaquete;
	}
	public void setPrecioPaquete(BigDecimal precioPaquete) {
		this.precioPaquete = precioPaquete;
	}
	public Boolean getFragilPaquete() {
		return fragilPaquete;
	}
	public void setFragilPaquete(Boolean fragilPaquete) {
		this.fragilPaquete = fragilPaquete;
	}
	public Boolean getEstadoPaquete() {
		return estadoPaquete;
	}
	public void setEstadoPaquete(Boolean estadoPaquete) {
		this.estadoPaquete = estadoPaquete;
	}
	//public Envio getEnvioPaquete() {
	//	return envioPaquete;
	//}
	//public void setEnvioPaquete(Envio envioPaquete) {
	//	this.envioPaquete = envioPaquete;
	//}
}
