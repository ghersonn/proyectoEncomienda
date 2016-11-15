package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entidades.Ciudad;
import com.entidades.Ruta;


public class DAORuta {
	
	// Singleton
		public static DAORuta _Instancia;

		private DAORuta() {};

		public static DAORuta Instancia() {
			if (_Instancia == null) {
				_Instancia = new DAORuta();
			}
			return _Instancia;
		}
     //endSingleton
		
		/*public ArrayList<Ruta> listarRuta() throws Exception{
			
			Connection connection = DAOConexion.Instancia().conectar();
			ArrayList<Ruta> listRuta = new ArrayList<Ruta>();

			try {
				CallableStatement callableStatement = connection.prepareCall("{call LIS_Ruta()}");
				ResultSet resultSet = callableStatement.executeQuery();
				
				while (resultSet.next()) {
					
					Ciudad objCiudadOrigen = new Ciudad();
					objCiudadOrigen.setIdCiudad(resultSet.getInt("idCiudadOrigen"));
					objCiudadOrigen.setNombreCiudad(resultSet.getString("CiudadOrigen"));
					
					
					Ciudad objCiudadDestino = new Ciudad();
					objCiudadDestino.setId(resultSet.getInt("idCiudadDestino"));
					objCiudadDestino.setNombre(resultSet.getString("CiudadDestino"));
					
					Ruta objRuta = new Ruta();
					objRuta.setId(resultSet.getInt("id"));
					objRuta.setPrecio(resultSet.getBigDecimal("precio"));
					objRuta.setDiasDemora(resultSet.getInt("diasDemora"));
					objRuta.setCiudadOrigen(objCiudadOrigen);
					objRuta.setCiudadDestino(objCiudadDestino);
					
					listRuta.add(objRuta);
					
				}
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return listRuta;
		}*/

}
