package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entidades.Chofer;
import com.entidades.Ciudad;
import com.entidades.Ruta;
import com.entidades.UnidadTransporte;

public class DAOUnidadTransporte {
	//Singleton
	public static DAOUnidadTransporte _Instancia;
	
	private DAOUnidadTransporte() {};
	
	public static DAOUnidadTransporte Instancia() {
		if (_Instancia == null) {
			_Instancia = new DAOUnidadTransporte();
		}
		return _Instancia;
	}
	//endSingleton
	
	//Metodo
	public ArrayList<UnidadTransporte> listarUnidadTransporte() throws Exception{
		
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<UnidadTransporte> listUnidadTransporte = new ArrayList<UnidadTransporte>();

		try {
			CallableStatement callableStatement = connection.prepareCall("{call LIS_UnidadTransporte()}");
			ResultSet resultSet = callableStatement.executeQuery();
			
			/*
			 * id
				,matricula
				,marca
				,modelo
				,idChofer
				,estado
			*/
			
			while (resultSet.next()) {
				
				UnidadTransporte objUnidadTransporte = new UnidadTransporte();
				
				Chofer objChofer = new Chofer();
				objChofer.setIdChofer(resultSet.getInt("idChofer"));
				
				
				objUnidadTransporte.setIdUnidadTransporte(resultSet.getInt("id"));
				objUnidadTransporte.setMatriculaUnidadTransporte(resultSet.getString("matricula"));
				objUnidadTransporte.setMarcaUnidadTransporte(resultSet.getString("marca"));
				objUnidadTransporte.setModeloUnidadTransporte(resultSet.getString("modelo"));
				objUnidadTransporte.setChoferUnidadTransporte(objChofer);
				objUnidadTransporte.setEstadoUnidadTransporte(resultSet.getBoolean("estado"));
				
				listUnidadTransporte.add(objUnidadTransporte);
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listUnidadTransporte;
	}
	
	public UnidadTransporte obtenerUnidadTransporte(int idUnidadTransporte) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		UnidadTransporte objUnidadTransporte = null;
		try {
			CallableStatement callableStatement = connection.prepareCall("{call BUS_UnidadTransporte(?)}");
			
			callableStatement.setInt(1, idUnidadTransporte);
			ResultSet resultSet = callableStatement.executeQuery();
			
			if (resultSet.next()) {
				
				objUnidadTransporte = new UnidadTransporte();
				
				Chofer objChofer = new Chofer();
				objChofer.setIdChofer(resultSet.getInt("idChofer"));
				
				
				objUnidadTransporte.setIdUnidadTransporte(resultSet.getInt("id"));
				objUnidadTransporte.setMatriculaUnidadTransporte(resultSet.getString("matricula"));
				objUnidadTransporte.setMarcaUnidadTransporte(resultSet.getString("marca"));
				objUnidadTransporte.setModeloUnidadTransporte(resultSet.getString("modelo"));
				objUnidadTransporte.setChoferUnidadTransporte(objChofer);
				objUnidadTransporte.setEstadoUnidadTransporte(resultSet.getBoolean("estado"));				
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return objUnidadTransporte;
	}
	
	//EndMetodo
}
