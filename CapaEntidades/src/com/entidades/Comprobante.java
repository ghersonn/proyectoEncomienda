package com.entidades;

public class Comprobante {
	public int idComprobante;
	public int numeroComprobanteComprobante;
	public String serieComprobante;
	public String tipoComprobanteComprobante;
	public Boolean estadoComprobante;
	public Envio envioComprobante;
	
	
	public int getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
	}
	public int getNumeroComprobanteComprobante() {
		return numeroComprobanteComprobante;
	}
	public void setNumeroComprobanteComprobante(int numeroComprobanteComprobante) {
		this.numeroComprobanteComprobante = numeroComprobanteComprobante;
	}
	public String getSerieComprobante() {
		return serieComprobante;
	}
	public void setSerieComprobante(String serieComprobante) {
		this.serieComprobante = serieComprobante;
	}
	public String getTipoComprobanteComprobante() {
		return tipoComprobanteComprobante;
	}
	public void setTipoComprobanteComprobante(String tipoComprobanteComprobante) {
		this.tipoComprobanteComprobante = tipoComprobanteComprobante;
	}
	public Boolean getEstadoComprobante() {
		return estadoComprobante;
	}
	public void setEstadoComprobante(Boolean estadoComprobante) {
		this.estadoComprobante = estadoComprobante;
	}
	public Envio getEnvioComprobante() {
		return envioComprobante;
	}
	public void setEnvioComprobante(Envio envioComprobante) {
		this.envioComprobante = envioComprobante;
	}
}
