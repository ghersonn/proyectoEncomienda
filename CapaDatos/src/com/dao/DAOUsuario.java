package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	
	public ArrayList<Usuario> listarUsuario() throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();

		try {
			CallableStatement callableStatement = connection.prepareCall("{call LIS_Usuario()}");
			ResultSet resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				Usuario objUsuario = new Usuario();
				objUsuario.setIdUsuario(resultSet.getInt("idUsuario"));
				objUsuario.setUserNameUsuario(resultSet.getString("userNameUsuario"));
				objUsuario.setContraseniaUsuario(resultSet.getString("contraseniaUsuario"));
				objUsuario.setNombreUsuario(resultSet.getString("nombreUsuario"));
				objUsuario.setApellidoUsuario(resultSet.getString("apellidoUsuario"));
				objUsuario.setTelefonoUsuario(resultSet.getString("telefonoUsuario"));
				objUsuario.setDniUsuario(resultSet.getString("dniUsuario"));
				
				listUsuario.add(objUsuario);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listUsuario;
	}
	
	public ArrayList<Usuario> buscarUsuarios(String nombre) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call BUS_Usuarios(?)}");
			
			callableStatement.setString(1, nombre);
			ResultSet resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				Usuario objUsuario = new Usuario();
				objUsuario.setIdUsuario(resultSet.getInt("idUsuario"));
				objUsuario.setUserNameUsuario(resultSet.getString("userNameUsuario"));
				objUsuario.setContraseniaUsuario(resultSet.getString("contrasenioUsuario"));
				objUsuario.setNombreUsuario(resultSet.getString("nombreUsuario"));
				objUsuario.setApellidoUsuario(resultSet.getString("apellidoUsuario"));
				objUsuario.setTelefonoUsuario(resultSet.getString("telefonoUsuario"));
				objUsuario.setDniUsuario(resultSet.getString("dniUsuario"));
				
				listUsuario.add(objUsuario);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listUsuario;
	}
	
	public Usuario obtenerUsuario(int idUsuario) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Usuario objUsuario = null;
		try {
			CallableStatement callableStatement = connection.prepareCall("{call BUS_Usuario(?)}");
			
			callableStatement.setInt(1, idUsuario);
			ResultSet resultSet = callableStatement.executeQuery();
			
			if (resultSet.next()) {
				objUsuario = new Usuario();
				objUsuario.setIdUsuario(resultSet.getInt("id"));
				objUsuario.setUserNameUsuario(resultSet.getString("userName"));
				objUsuario.setContraseniaUsuario(resultSet.getString("contrasenia"));
				objUsuario.setNombreUsuario(resultSet.getString("nombre"));
				objUsuario.setApellidoUsuario(resultSet.getString("apellido"));
				objUsuario.setTelefonoUsuario(resultSet.getString("telefono"));
				objUsuario.setDniUsuario(resultSet.getString("dni"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return objUsuario;
	}
	
	public Boolean insertarUsuario(Usuario objUsuario) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
			
			
			CallableStatement callableStatement = connection.prepareCall("{call REG_InsUsuario(?,?,?,?,?,?)}");
			
			callableStatement.setString(1, objUsuario.getUserNameUsuario());
			callableStatement.setString(2, objUsuario.getContraseniaUsuario());
			callableStatement.setString(3, objUsuario.getNombreUsuario());
			callableStatement.setString(4, objUsuario.getApellidoUsuario());
			callableStatement.setString(5, objUsuario.getTelefonoUsuario());
			callableStatement.setString(6, objUsuario.getDniUsuario());
			
			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta = true;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
	public Boolean modificarUsuario(Usuario objUsuario) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
						
			CallableStatement callableStatement = connection.prepareCall("{call ACT_Usuario(?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, objUsuario.getIdUsuario());
			callableStatement.setString(2, objUsuario.getUserNameUsuario());
			callableStatement.setString(3, objUsuario.getContraseniaUsuario());
			callableStatement.setString(4, objUsuario.getNombreUsuario());
			callableStatement.setString(5, objUsuario.getApellidoUsuario());
			callableStatement.setString(6, objUsuario.getTelefonoUsuario());
			callableStatement.setString(7, objUsuario.getDniUsuario());

			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta = true;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
	public Boolean eliminarUsuario(int idUsuario) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
						
			CallableStatement callableStatement = connection.prepareCall("{call ACT_UsuarioEstado(?)}");
			
			callableStatement.setInt(1, idUsuario);

			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta= true;
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
	
}
