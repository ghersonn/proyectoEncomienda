package com.negocio;

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
}
