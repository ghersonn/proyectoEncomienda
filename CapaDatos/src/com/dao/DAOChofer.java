package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entidades.Chofer;
import com.entidades.Usuario;

public class DAOChofer {

	//Singleton
		public static DAOChofer _Instancia;
		
		private DAOChofer() {};
		
		public static DAOChofer Instancia() {
			if (_Instancia == null) {
				_Instancia = new DAOChofer();
			}
			return _Instancia;
		}
	   //endSingleton
		//Metodos
		
		public ArrayList<Chofer> listarChofer() throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			ArrayList<Chofer> listChofer = new ArrayList<Chofer>();

			try {
				CallableStatement callableStatement = connection.prepareCall("{call LIS_Chofer()}");
				ResultSet resultSet = callableStatement.executeQuery();
				
				while (resultSet.next()) {
					Chofer objChofer = new Chofer();
					objChofer.setIdChofer(resultSet.getInt("idChofer"));
					objChofer.setNombreChofer(resultSet.getString("nombreChofer"));
					objChofer.setApellidoChofer(resultSet.getString("apellidoChofer"));
					objChofer.setTelefonoChofer(resultSet.getString("telefonoChofer"));
					objChofer.setDniChofer(resultSet.getString("dniChofer"));
					
					listChofer.add(objChofer);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return listChofer;
		}
		
		public ArrayList<Chofer> buscarChoferes(String nombre) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			ArrayList<Chofer> listChofer = new ArrayList<Chofer>();
			
			try {
				CallableStatement callableStatement = connection.prepareCall("{call BUS_Choferes(?)}");
				
				callableStatement.setString(1, nombre);
				ResultSet resultSet = callableStatement.executeQuery();
				
				while (resultSet.next()) {
					Chofer objChofer = new Chofer();
					objChofer.setIdChofer(resultSet.getInt("idChofer"));
					objChofer.setNombreChofer(resultSet.getString("nombreChofer"));
					objChofer.setApellidoChofer(resultSet.getString("apellidoChofer"));
					objChofer.setTelefonoChofer(resultSet.getString("telefonoChofer"));
					objChofer.setDniChofer(resultSet.getString("dniChofer"));
					
					listChofer.add(objChofer);
				}
				
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return listChofer;
		}
		
		public Chofer obtenerChofer(int idChofer) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Chofer objChofer = null;
			try {
				CallableStatement callableStatement = connection.prepareCall("{call BUS_Chofer(?)}");
				
				callableStatement.setInt(1, idChofer);
				ResultSet resultSet = callableStatement.executeQuery();
				
				if (resultSet.next()) {
					objChofer = new Chofer();
					objChofer.setIdChofer(resultSet.getInt("id"));
					objChofer.setNombreChofer(resultSet.getString("nombre"));
					objChofer.setApellidoChofer(resultSet.getString("apellido"));
					objChofer.setTelefonoChofer(resultSet.getString("telefono"));
					objChofer.setDniChofer(resultSet.getString("dni"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return objChofer;
		}
		
		public Boolean insertarChofer(Chofer objChofer) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Boolean respuesta=false;
			try {
				
				
				CallableStatement callableStatement = connection.prepareCall("{call REG_InsChofer(?,?,?,?)}");
				
				
				callableStatement.setString(1, objChofer.getNombreChofer());
				callableStatement.setString(2, objChofer.getApellidoChofer());
				callableStatement.setString(3, objChofer.getTelefonoChofer());
				callableStatement.setString(4, objChofer.getDniChofer());
				
				int i = callableStatement.executeUpdate();

				if (i > 0)	respuesta = true;

			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return respuesta;
		}
		
		public Boolean modificarChofer(Chofer objChofer) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Boolean respuesta=false;
			try {
							
				CallableStatement callableStatement = connection.prepareCall("{call ACT_Chofer(?,?,?,?,?)}");
				callableStatement.setInt(1, objChofer.getIdChofer());
				callableStatement.setString(2, objChofer.getNombreChofer());
				callableStatement.setString(3, objChofer.getApellidoChofer());
				callableStatement.setString(4, objChofer.getTelefonoChofer());
				callableStatement.setString(5, objChofer.getDniChofer());

				int i = callableStatement.executeUpdate();

				if (i > 0)	respuesta = true;

			} catch (Exception e) {
				throw e;
			} finally {
				connection.close();
			}
			return respuesta;
		}
		
		public Boolean eliminarChofer(int idChofer) throws Exception {
			Connection connection = DAOConexion.Instancia().conectar();
			Boolean respuesta=false;
			try {
							
				CallableStatement callableStatement = connection.prepareCall("{call ACT_ChoferEstado(?)}");
				
				callableStatement.setInt(1, idChofer);

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
