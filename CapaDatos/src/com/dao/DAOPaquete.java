package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.entidades.Ciudad;
import com.entidades.Cliente;
import com.entidades.Envio;
import com.entidades.Paquete;
import com.entidades.Ruta;

public class DAOPaquete {
	
	// Singleton
		public static DAOPaquete _Instancia;	
		private DAOPaquete() {
		};	
		public static DAOPaquete Instancia() {
			if (_Instancia == null) {
				_Instancia = new DAOPaquete();
			}
			return _Instancia;
		}
		// endSingleton

		public ArrayList<Paquete> listaPaqueteEnvio(int idEnvio) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			ArrayList<Paquete> listpaquete = new ArrayList<Paquete>();
			
			try {
				CallableStatement callableStatement = connection.prepareCall("{call LIS_PaqueteEnvio(?)}");
				
				callableStatement.setInt(1, idEnvio);
				ResultSet resultSet = callableStatement.executeQuery();
				
				while (resultSet.next()) {
					
					Paquete objPaquete = new Paquete();
					//objEnvio.setIdPaquete(resultSet.getInt("id"));
					objPaquete.setDescripcionPaquete(resultSet.getString("descripcion"));
					objPaquete.setPesoPaquete(resultSet.getBigDecimal("peso"));
					objPaquete.setPrecioPaquete(resultSet.getBigDecimal("precio"));
					objPaquete.setFragilPaquete(resultSet.getBoolean("fragil"));
					
									
					listpaquete.add(objPaquete);
				}
				
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return listpaquete;
		}
}
