package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.entidades.Usuario;

public class DAOUsuario {
	// Singleton
	public static DAOUsuario _Instancia;	
	private DAOUsuario() {
	};	
	public static DAOUsuario Instancia() {
		if (_Instancia == null) {
			_Instancia = new DAOUsuario();
		}
		return _Instancia;
	}
	// endSingleton
	
	//Metodos
	public Usuario VerificarAcceso(String userName, String contrasenia) throws Exception {
		Connection cn = null;
		Usuario u = null;
		try {
			cn = DAOConexion.Instancia().conectar();
			CallableStatement cst = cn.prepareCall("{call SEG_VerificarAcceso(?,?)}");

			/*
			 *	@prmstrUsuario varchar(30), 
				@prmstrPassword varchar(30) 
			 * */
			
			cst.setString(1, userName);
			cst.setString(2, contrasenia);
			
			ResultSet rs = cst.executeQuery();
			if (rs.next()) {
				
				/*
				 	u.id as idUsuario,
					u.nombre as nombreUsuario,
					u.apellido as apellidoUsuario,
					u.telefono as telefonoUsuario,
					u.DNI as dniUsuario,
					u.estado as estadoUsuario
				 */
				
				u = new Usuario();
				u.setIdUsuario(rs.getInt("idUsuario"));
				u.setNombreUsuario(rs.getString("nombreUsuario"));
				u.setApellidoUsuario(rs.getString("apellidoUsuario"));
				u.setTelefonoUsuario(rs.getString("telefonoUsuario"));
				u.setDniUsuario(rs.getString("dniUsuario"));
				u.setEstadoUsuario(rs.getBoolean("estadoUsuario"));
				
			}
			return u;
		} catch (Exception e) {
			throw e;
		} finally {
			cn.close();
		}
	}
	//endMetodos
}
