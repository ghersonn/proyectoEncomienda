package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.entidades.Cliente;

public class DAOCliente {
	//Singleton
	public static DAOCliente _Instancia;
	
	private DAOCliente() {};
	
	public static DAOCliente Instancia() {
		if (_Instancia == null) {
			_Instancia = new DAOCliente();
		}
		return _Instancia;
	}
   //endSingleton
	//Metodos
	public Cliente obtenerClienteDNI(String dni) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Cliente objCliente = null;
		try {
			CallableStatement callableStatement = connection.prepareCall("{call BUS_ClienteDNI(?)}");
			
			callableStatement.setString(1, dni);
			ResultSet resultSet = callableStatement.executeQuery();
			
			/*
			 	id,
				nombre,
				apellidos,
				dni,
				celular,
				razonSocial,
				ruc
			 */

			if (resultSet.next()) {
				objCliente = new Cliente();
				objCliente.setIdCliente(resultSet.getInt("id"));
				objCliente.setNombreCliente(resultSet.getString("nombre"));
				objCliente.setApellidosCliente(resultSet.getString("apellidos"));
				objCliente.setDniCliente(resultSet.getString("dni"));
				objCliente.setCelularCliente(resultSet.getString("celular"));
				objCliente.setRazonSocialCliente(resultSet.getString("razonSocial"));
				objCliente.setRucCliente(resultSet.getString("ruc"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return objCliente;
	}
	//endMetodos
}
