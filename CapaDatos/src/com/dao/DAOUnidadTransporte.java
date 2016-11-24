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
	
	
	public Boolean insertarUnidadTransporte(UnidadTransporte objUnidadTransporte) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
			
			/*
			 * --@prmtid int
			@prmtmatricula varchar(30)
			,@prmtmarca varchar(20)
			,@prmtmodelo varchar(50)
			,@prmtidChofer int
			--,@prmtestado bit
			*/
			
			CallableStatement callableStatement = connection.prepareCall("{call REG_InsUnidadTransporte(?,?,?,?)}");
			callableStatement.setString(1, objUnidadTransporte.getMatriculaUnidadTransporte());
			callableStatement.setString(2, objUnidadTransporte.getMarcaUnidadTransporte());
			callableStatement.setString(3, objUnidadTransporte.getModeloUnidadTransporte());
			callableStatement.setInt(4, objUnidadTransporte.getChoferUnidadTransporte().getIdChofer());

			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta = true;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
	
	public Boolean modificarUnidadTransporte(UnidadTransporte objUnidadTransporte) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
			/*
			 @prmtid int
			,@prmtmatricula varchar(30)
			,@prmtmarca varchar(20)
			,@prmtmodelo varchar(50)
			,@prmtidChofer int
			--,@prmtestado bit
			 */
					
			CallableStatement callableStatement = connection.prepareCall("{call ACT_UnidadTransporte(?,?,?,?,?)}");
			callableStatement.setInt(1, objUnidadTransporte.getIdUnidadTransporte());
			callableStatement.setString(2, objUnidadTransporte.getMatriculaUnidadTransporte());
			callableStatement.setString(3, objUnidadTransporte.getMarcaUnidadTransporte());
			callableStatement.setString(4, objUnidadTransporte.getModeloUnidadTransporte());
			callableStatement.setInt(5, objUnidadTransporte.getChoferUnidadTransporte().getIdChofer());

			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta = true;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
	
	public Boolean eliminarUnidadTransporte(int idUnidadTransporte) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
			
							
			CallableStatement callableStatement = connection.prepareCall("{call ACT_UnidadTransporteEstado(?)}");
			
			callableStatement.setInt(1, idUnidadTransporte);

			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta= true;
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
	//EndMetodo
}
