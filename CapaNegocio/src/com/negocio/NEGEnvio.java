package com.negocio;

import com.dao.DAOEnvio;
import com.entidades.Envio;

public class NEGEnvio {
	//Singleton
	public static NEGEnvio _Instancia;
	private NEGEnvio(){};
	public static NEGEnvio Instancia(){
		if(_Instancia==null){
			_Instancia = new NEGEnvio();
		}
		return _Instancia;
	}
	//endSingleton
	//Metodos
	public Boolean enviar(Envio objEnvio)throws Exception{
		try {
			Boolean verificar = DAOEnvio.Instancia().enviar(objEnvio);
			if(!verificar==true){
				throw new ArithmeticException("Error al registrar el envio!");
			}
			return verificar;
		} catch (Exception e) {
			throw e;
		}
	}
	//endMetodos
}
