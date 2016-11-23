package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.entidades.Envio;
import com.entidades.Ruta;
import com.entidades.Viaje;

public class DAOViaje {
	// Singleton
	public static DAOViaje _Instancia;	
	private DAOViaje() {
	};	
	public static DAOViaje Instancia() {
		if (_Instancia == null) {
			_Instancia = new DAOViaje();
		}
		return _Instancia;
	}
	// endSingleton
	
	//metodo
	public Boolean insertarViaje(Envio objEnvio) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
			/*
			 * 	@prmstrIdEnvio int
				,@prmstrfechaEnvio date
				,@prmstridUnidadTransporte int
				,@prmstridRuta int
			*/
			
			CallableStatement callableStatement = connection.prepareCall("{call REG_InsViajeEnvio(?,?,?,?)}");
			callableStatement.setInt(1, objEnvio.getIdEnvio());
			callableStatement.setDate(2, new java.sql.Date(objEnvio.getViajeEnvio().getFechaEnvioViaje().getTime()));
			callableStatement.setInt(3, objEnvio.getViajeEnvio().getUnidadTransporteViaje().getIdUnidadTransporte());
			callableStatement.setInt(4, objEnvio.getRutaEnvio().getIdRuta());
			
			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta = true;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	//endMetodo
}
