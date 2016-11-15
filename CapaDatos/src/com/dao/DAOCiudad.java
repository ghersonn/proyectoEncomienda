package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entidades.Ciudad;

public class DAOCiudad {

  // Singleton
	public static DAOCiudad _Instancia;

	private DAOCiudad() {};

	public static DAOCiudad Instancia() {
		if (_Instancia == null) {
			_Instancia = new DAOCiudad();
		}
		return _Instancia;
	}
 //endSingleton
	
	public ArrayList<Ciudad> listarCiudad() throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<Ciudad> listCiudad = new ArrayList<Ciudad>();

		try {
			CallableStatement callableStatement = connection.prepareCall("{call LIS_Ciudad()}");
			ResultSet resultSet = callableStatement.executeQuery();

			while (resultSet.next()) {
				Ciudad objCiudad = new Ciudad();
				objCiudad.setIdCiudad(resultSet.getInt("idCiudad"));
				objCiudad.setNombreCiudad(resultSet.getString("nombreCiudad"));
				objCiudad.setDireccionCiudad(resultSet.getString("direccionCiudad"));

				listCiudad.add(objCiudad);

			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listCiudad;
	}
	
}
