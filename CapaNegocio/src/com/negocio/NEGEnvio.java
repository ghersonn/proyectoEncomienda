package com.negocio;

import java.util.ArrayList;

import com.dao.DAOEnvio;
import com.dao.DAORuta;
import com.entidades.Envio;
import com.entidades.Ruta;

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
	
	public ArrayList<Envio> listarEnvioEstadoR() throws Exception {
	try {
		return DAOEnvio.Instancia().listarEnvioEstadoR();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Envio obtenerEnvio(int idEnvio) throws Exception {
		Envio objEnvio = null;
		try {
			objEnvio = DAOEnvio.Instancia().obtenerEnvio(idEnvio);
		} catch (Exception e) {
			throw e;
		}
		return objEnvio;
	}
	//endMetodos
}
