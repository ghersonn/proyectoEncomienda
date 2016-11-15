package com.entidades;

public class Permiso {
	public int idPermiso;
	public String descripcionPermiso;
	public String urlPermiso;
	public Boolean estadoPermiso;
	
	public int getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	public String getDescripcionPermiso() {
		return descripcionPermiso;
	}
	public void setDescripcionPermiso(String descripcionPermiso) {
		this.descripcionPermiso = descripcionPermiso;
	}
	public String getUrlPermiso() {
		return urlPermiso;
	}
	public void setUrlPermiso(String urlPermiso) {
		this.urlPermiso = urlPermiso;
	}
	public Boolean getEstadoPermiso() {
		return estadoPermiso;
	}
	public void setEstadoPermiso(Boolean estadoPermiso) {
		this.estadoPermiso = estadoPermiso;
	}
}
