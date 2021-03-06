package com.negocio;


import java.util.ArrayList;
import java.util.Date;

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
			if(objEnvio.getRemitenteEnvio().idCliente<=0){throw new ArithmeticException("Falta Remitente");}
			if(objEnvio.getDestinatarioEnvio().idCliente<=0){throw new ArithmeticException("Falta Destinatario");}
			if(objEnvio.getListPaquete().isEmpty()){throw new ArithmeticException("Ingrese paquetes a la lista");}
			
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
	
	public ArrayList<Envio> listarEnvioEstadoL() throws Exception {
		try {
			return DAOEnvio.Instancia().listarEnvioEstadoL();
			} catch (Exception e) {
				throw e;
			}
		}
	
	public Envio obtenerEnvio(int idEnvio) throws Exception {
		Envio objEnvio = null;
		try {
			if(idEnvio<=0){
				throw new ArithmeticException("El envio no existe");
			}
			objEnvio = DAOEnvio.Instancia().obtenerEnvio(idEnvio);
		} catch (Exception e) {
			throw e;
		}
		return objEnvio;
	}
	
	public ArrayList<Envio> reporteEnvio(Date fechaEmision) throws Exception {
		try {
			return DAOEnvio.Instancia().reporteEnvio(fechaEmision);
			} catch (Exception e) {
				throw e;
			}
		}
	
	public Boolean actualizarEnvioEstadoX(int codigoEnvio) throws Exception {
		try {
			return DAOEnvio.Instancia().actualizarEnvioEstadoX(codigoEnvio);
		} catch (Exception e) {
			throw e;
		}
	}
	//endMetodos
}
