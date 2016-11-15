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
	
	//Metodos
		public ArrayList<Ruta> listarRuta() throws Exception{
			
			Connection connection = DAOConexion.Instancia().conectar();
			ArrayList<Ruta> listRuta = new ArrayList<Ruta>();

			try {
				CallableStatement callableStatement = connection.prepareCall("{call LIS_Ruta()}");
				ResultSet resultSet = callableStatement.executeQuery();
				
				while (resultSet.next()) {
					
					Ciudad objCiudadOrigen = new Ciudad();
					objCiudadOrigen.setIdCiudad(resultSet.getInt("idCiudadOrigen"));
					objCiudadOrigen.setNombreCiudad(resultSet.getString("nombreCiudadOrigen"));
					
					
					Ciudad objCiudadDestino = new Ciudad();
					objCiudadDestino.setIdCiudad(resultSet.getInt("idCiudadDestino"));
					objCiudadDestino.setNombreCiudad(resultSet.getString("nombreCiudadDestino"));
					
					Ruta objRuta = new Ruta();
					objRuta.setIdRuta(resultSet.getInt("idRuta"));
					objRuta.setPrecioRuta(resultSet.getBigDecimal("precioRuta"));
					objRuta.setDiasDemoraRuta(resultSet.getInt("diasDemoraRuta"));
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
		}
		
		public Ruta obtenerRuta(int idRuta) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Ruta objRuta = null;
			try {
				CallableStatement callableStatement = connection.prepareCall("{call BUS_Ruta(?)}");
				
				callableStatement.setInt(1, idRuta);
				ResultSet resultSet = callableStatement.executeQuery();
				
				if (resultSet.next()) {
					
					Ciudad objCiudadOrigen = new Ciudad();
					objCiudadOrigen.setIdCiudad(resultSet.getInt("idCiudadOrigen"));
					objCiudadOrigen.setNombreCiudad(resultSet.getString("nombreCiudadOrigen"));
					
					
					Ciudad objCiudadDestino = new Ciudad();
					objCiudadDestino.setIdCiudad(resultSet.getInt("idCiudadDestino"));
					objCiudadDestino.setNombreCiudad(resultSet.getString("nombreCiudadDestino"));
					
					objRuta = new Ruta();
					objRuta.setIdRuta(resultSet.getInt("idRuta"));
					objRuta.setPrecioRuta(resultSet.getBigDecimal("precioRuta"));
					objRuta.setDiasDemoraRuta(resultSet.getInt("diasDemoraRuta"));
					objRuta.setCiudadOrigen(objCiudadOrigen);
					objRuta.setCiudadDestino(objCiudadDestino);
					
				}
				
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return objRuta;
		}
		
		public Boolean insertarRuta(Ruta objRuta) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Boolean respuesta=false;
			try {
				
				CallableStatement callableStatement = connection.prepareCall("{call REG_InsRuta(?,?,?,?)}");
				callableStatement.setBigDecimal(1, objRuta.getPrecioRuta());
				callableStatement.setInt(2, objRuta.getDiasDemoraRuta());
				callableStatement.setInt(3, objRuta.getCiudadOrigen().getIdCiudad());
				callableStatement.setInt(4, objRuta.getCiudadDestino().getIdCiudad());

				int i = callableStatement.executeUpdate();

				if (i > 0)	respuesta = true;

			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return respuesta;
		}
		
		public Boolean modificarRuta(Ruta objRuta) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Boolean respuesta=false;
			try {
						
				CallableStatement callableStatement = connection.prepareCall("{call ACT_Ruta(?,?,?,?,?)}");
				callableStatement.setInt(1, objRuta.getIdRuta());
				callableStatement.setBigDecimal(2, objRuta.getPrecioRuta());
				callableStatement.setInt(3, objRuta.getDiasDemoraRuta());
				callableStatement.setInt(4, objRuta.getCiudadOrigen().getIdCiudad());
				callableStatement.setInt(5, objRuta.getCiudadDestino().getIdCiudad());

				int i = callableStatement.executeUpdate();

				if (i > 0)	respuesta = true;

			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return respuesta;
		}
		
		public Boolean eliminarRuta(int idRuta) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Boolean respuesta=false;
			try {
				
								
				CallableStatement callableStatement = connection.prepareCall("{call ACT_RutaEstado(?)}");
				
				callableStatement.setInt(1, idRuta);

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
