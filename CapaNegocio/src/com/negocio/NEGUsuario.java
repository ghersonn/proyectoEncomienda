package com.negocio;

import java.util.ArrayList;

import com.dao.DAOUsuario;
import com.entidades.Usuario;

public class NEGUsuario {
	//Singleton
	public static NEGUsuario _Instancia;
	private NEGUsuario(){};
	public static NEGUsuario Instancia(){
		if(_Instancia==null){
			_Instancia = new NEGUsuario();
		}
		return _Instancia;
	}
	//endSingleton
	
	//Metodos
	public Usuario VerificarAcceso(String userName, String contrasenia)throws Exception{
		try {
			Usuario u = DAOUsuario.Instancia().VerificarAcceso(userName, contrasenia);
			if(u==null){
				throw new ArithmeticException("Usuario o Password no valido!");
			}else if(!u.getEstadoUsuario()){
				throw new ArithmeticException("Su usuario ha sido dado de baja!");
			}
			return u;
		} catch (Exception e) {
			throw e;
		}
	}
	//endMetodos
	
	public ArrayList<Usuario> listarUsuario() throws Exception {
		try {
			return DAOUsuario.Instancia().listarUsuario();
		} catch (Exception e) {
			throw e;
		}
	}
		
	public Usuario obtenerUsuario(int idUsuario) throws Exception {
		Usuario objUsuario = null;
		try {
			objUsuario = DAOUsuario.Instancia().obtenerUsuario(idUsuario);
		} catch (Exception e) {
			throw e;
		}
		return objUsuario;
	}
	
	public ArrayList<Usuario> buscarUsuarios(String nombre) throws Exception {
		ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
		try {
			listUsuario = DAOUsuario.Instancia().buscarUsuarios(nombre);
		} catch (Exception e) {
			throw e;
		}
		return listUsuario;
	}

	public Boolean insertarUsuario(Usuario objUsuario) throws Exception {
		Boolean respuesta=false;
		try {
			
			for (Usuario c : DAOUsuario.Instancia().listarUsuario())
			{
				if (c.getDniUsuario().equals(objUsuario.getDniUsuario()))
					throw new Exception("El DNI del Usuario ya existe");
			}
			
			respuesta = DAOUsuario.Instancia().insertarUsuario(objUsuario);
		} catch (Exception e) {
			throw e;
		}
		
		return respuesta;
	}

	public Boolean modificarUsuario(Usuario objUsuario) throws Exception {
		Boolean respuesta=false;
		try {
			respuesta = DAOUsuario.Instancia().modificarUsuario(objUsuario);
		} catch (Exception e) {
			throw e;
		}
		return respuesta;
	}

	public Boolean eliminarUsuario(int idUsuario) throws Exception {
		Boolean respuesta=false;
		try {
			respuesta = DAOUsuario.Instancia().eliminarUsuario(idUsuario);
		} catch (Exception e) {
			throw e;
		}
		return respuesta;
	}
	
	
}
