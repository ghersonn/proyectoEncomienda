package com.entidades;

import java.util.List;

public class Usuario {

	private int idUsuario;
	private String userNameUsuario;
	private String contrasenioUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String telefonoUsuario;
	private String dniUsuario;
	private Boolean estadoUsuario;
	private List<Permiso> listPermisoUsuario;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUserNameUsuario() {
		return userNameUsuario;
	}
	public void setUserNameUsuario(String userNameUsuario) {
		this.userNameUsuario = userNameUsuario;
	}
	public String getContrasenioUsuario() {
		return contrasenioUsuario;
	}
	public void setContrasenioUsuario(String contrasenioUsuario) {
		this.contrasenioUsuario = contrasenioUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}
	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	public Boolean getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(Boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
	public List<Permiso> getListPermisoUsuario() {
		return listPermisoUsuario;
	}
	public void setListPermisoUsuario(List<Permiso> listPermisoUsuario) {
		this.listPermisoUsuario = listPermisoUsuario;
	}
}
