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
import com.entidades.UnidadTransporte;
import com.entidades.Usuario;

public class DAOEnvio {
	// Singleton
	public static DAOEnvio _Instancia;	
	private DAOEnvio() {
	};	
	public static DAOEnvio Instancia() {
		if (_Instancia == null) {
			_Instancia = new DAOEnvio();
		}
		return _Instancia;
	}
	// endSingleton
	
	//Metodos
	public Boolean enviar(Envio objEnvio) throws Exception {
		Connection cn = null;
		Boolean verificarEnvio = false;
		try {
			cn = DAOConexion.Instancia().conectar();
			cn.setAutoCommit(false);
			/*REGISTRAR ENVIO*/			
			CallableStatement cst1 = cn.prepareCall("{call REG_InsEnvio(?,?,?,?,?,?,?)}");
	
			/*
			 	--@prmstrId int 
				--,@prmstrCodigoGenerado int
				@prmstrFechaEmision date
				,@prmstrFechaLlegada date
				--,@prmstrFechaEntrega date
				,@prmstrMontoTotal decimal(10,2)
				--,@prmstrEstadoPago bit
				--,@prmstrEstadoEnvio char(1)
				--,@prmstrEstado bit
				,@prmstrIdRemitente int
				,@prmstrIdDestinatario int
				,@prmstrIdRuta int
				--,@prmstrIdViaje int
				,@prmstrIdUsuario int
			*/
			
			cst1.setDate(1, new java.sql.Date(objEnvio.getFechaEmisionEnvio().getTime()));
			cst1.setDate(2, new java.sql.Date(objEnvio.getFechaLlegadaEnvio().getTime()));
			cst1.setBigDecimal(3, objEnvio.getMontoTotalEnvio());
			cst1.setInt(4, objEnvio.getRemitenteEnvio().getIdCliente());
			cst1.setInt(5, objEnvio.getDestinatarioEnvio().getIdCliente());
			cst1.setInt(6, objEnvio.getRutaEnvio().getIdRuta());
			cst1.setInt(7, 1);
			//cst1.setInt(7, objEnvio.getUsuarioEnvio().getIdUsuario());
			
			ResultSet rs1 = cst1.executeQuery();
			if (rs1.next()) {				
				/*
				 * idEnvio
				 */
				objEnvio.setIdEnvio(rs1.getInt("idEnvio"));				
			}
			cst1.close();
			rs1.close();
			
			
			/*REGISTRAR PAQUETES*/
			Boolean verificarPaquete=false;
			for (Paquete objPaquete : objEnvio.getListPaquete()) {
				
				CallableStatement cst2 = cn.prepareCall("{call REG_InsPaquete(?,?,?,?,?)}");
				/*
					@descripcion varchar(100),
				    @peso decimal(10,2),
					@precio decimal(10,2),
					@fragil bit,
					--estado bit,
					@idEnvio int
				*/
				cst2.setString(1, objPaquete.getDescripcionPaquete());
				cst2.setBigDecimal(2, objPaquete.getPesoPaquete());
				cst2.setBigDecimal(3, objPaquete.getPrecioPaquete());
				cst2.setBoolean(4, objPaquete.getFragilPaquete());
				cst2.setInt(5, objEnvio.getIdEnvio());
				
				int i = cst2.executeUpdate();

				if (i > 0)	
				{
					verificarPaquete=true;
				}else{
					verificarPaquete=false;
					break;
				}
				
				cst2.close();
			}
			
			if(verificarPaquete==true){
				cn.commit();
				verificarEnvio=true;
			}
			else{
				cn.rollback();
			}
			
			return verificarEnvio;
			
		} catch (Exception e) {
			cn.rollback();
			throw e;
		} finally {
			cn.close();
		}
	}
	
	public ArrayList<Envio> listarEnvioEstadoR() throws Exception{
		
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<Envio> listEnvio = new ArrayList<Envio>();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call LIS_EnvioEstadoR()}");
			ResultSet resultSet = callableStatement.executeQuery();
			
			/*
			id
			,codigoGenerado
			,fechaEmision
			,fechaLlegada
			--,fechaEntrega
			,montoTotal
			,estadoPago
			,estadoEnvio
			,estado
			,idRemitente
			,idDestinatario
			,idRuta
			--,idViaje
			--,idUsuario
			*/
			
			while (resultSet.next()) {
				
				Cliente objRemitente = new Cliente();
				objRemitente.setIdCliente(resultSet.getInt("idRemitente"));
				
				Cliente objDestinatario = new Cliente();
				objDestinatario.setIdCliente(resultSet.getInt("idDestinatario"));
				
				Ruta objRuta = new Ruta();
				objRuta.setIdRuta(resultSet.getInt("idRuta"));
				
				Envio objEnvio = new Envio();
				objEnvio.setIdEnvio(resultSet.getInt("id"));
				objEnvio.setCodigoGeneradoEnvio(resultSet.getInt("codigoGenerado"));
				objEnvio.setRutaEnvio(objRuta);
				objEnvio.setRemitenteEnvio(objRemitente);
				objEnvio.setDestinatarioEnvio(objDestinatario);				
				
				listEnvio.add(objEnvio);
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listEnvio;
	}
	
public ArrayList<Envio> listarEnvioEstadoL() throws Exception{
		
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<Envio> listEnvio = new ArrayList<Envio>();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call LIS_EnvioEstadoL()}");
			ResultSet resultSet = callableStatement.executeQuery();
			
			/*
			id
			,codigoGenerado
			,fechaEmision
			,fechaLlegada
			--,fechaEntrega
			,montoTotal
			,estadoPago
			,estadoEnvio
			,estado
			,idRemitente
			,idDestinatario
			,idRuta
			--,idViaje
			--,idUsuario
			*/
			
			while (resultSet.next()) {
				
				Cliente objRemitente = new Cliente();
				objRemitente.setIdCliente(resultSet.getInt("idRemitente"));
				objRemitente.setNombreCliente(resultSet.getString("nombreRemitente"));
				
				Cliente objDestinatario = new Cliente();
				objDestinatario.setIdCliente(resultSet.getInt("idDestinatario"));
				objDestinatario.setNombreCliente(resultSet.getString("nombreDestinatario"));
				
				Ciudad objCiudadOrigen = new Ciudad();
				objCiudadOrigen.setNombreCiudad("nombreCiudadOrigen");
				
				Ciudad objCiudadDestino =  new Ciudad();
				objCiudadDestino.setNombreCiudad("nombreCiudadDestino");
				
				Ruta objRuta = new Ruta();
				objRuta.setIdRuta(resultSet.getInt("idRuta"));
				objRuta.setCiudadOrigen(objCiudadOrigen);
				objRuta.setCiudadDestino(objCiudadDestino);
				
				
				Envio objEnvio = new Envio();
				objEnvio.setIdEnvio(resultSet.getInt("id"));
				objEnvio.setCodigoGeneradoEnvio(resultSet.getInt("codigoGenerado"));
				objEnvio.setRutaEnvio(objRuta);
				objEnvio.setRemitenteEnvio(objRemitente);
				objEnvio.setDestinatarioEnvio(objDestinatario);				
				
				listEnvio.add(objEnvio);
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listEnvio;
	}
	
	public Envio obtenerEnvio(int idEnvio) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Envio objEnvio = null;
		try {
			CallableStatement callableStatement = connection.prepareCall("{call BUS_Envio(?)}");
			
			callableStatement.setInt(1, idEnvio);
			ResultSet resultSet = callableStatement.executeQuery();
			
			if (resultSet.next()) {
				
				Cliente objRemitente = new Cliente();
				objRemitente.setIdCliente(resultSet.getInt("idRemitente"));
				
				Cliente objDestinatario = new Cliente();
				objDestinatario.setIdCliente(resultSet.getInt("idDestinatario"));
				
				Ruta objRuta = new Ruta();
				objRuta.setIdRuta(resultSet.getInt("idRuta"));
				
				objEnvio = new Envio();
				objEnvio.setIdEnvio(resultSet.getInt("id"));
				objEnvio.setCodigoGeneradoEnvio(resultSet.getInt("codigoGenerado"));
				objEnvio.setRutaEnvio(objRuta);
				objEnvio.setRemitenteEnvio(objRemitente);
				objEnvio.setDestinatarioEnvio(objDestinatario);	
				
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return objEnvio;
	}
	
	//endMetodos
	
	//ReporteEnvio
	
	public ArrayList<Envio> reporteEnvio(Date fechaEmision) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		ArrayList<Envio> listEnvio = new ArrayList<Envio>();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call REPORTE_Envio(?)}");
			
			callableStatement.setDate(1, new java.sql.Date(fechaEmision.getTime()));
			ResultSet resultSet = callableStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Envio objEnvio = new Envio();
				objEnvio.setIdEnvio(resultSet.getInt("id"));
				objEnvio.setCodigoGeneradoEnvio(resultSet.getInt("codigoGenerado"));
				objEnvio.setFechaEmisionEnvio(resultSet.getDate("fechaEmision"));
				objEnvio.setFechaLlegadaEnvio(resultSet.getDate("fechaLlegada"));
				objEnvio.setFechaEntregaEnvio(resultSet.getDate("fechaEntrega"));
				objEnvio.setMontoTotalEnvio(resultSet.getBigDecimal("montoTotal"));
				objEnvio.setEstadoPagoEnvio(resultSet.getBoolean("estadoPago"));
				objEnvio.setEstadoEnvio(resultSet.getString("estadoEnvio"));
				
				Cliente objRemitente = new Cliente();
				objRemitente.setIdCliente(resultSet.getInt("idRemitente"));
				objRemitente.setNombreCliente(resultSet.getString("nombreRemitente"));
				objEnvio.setRemitenteEnvio(objRemitente);
				
				Cliente objDestinatario = new Cliente();
				objDestinatario.setIdCliente(resultSet.getInt("idDestinatario"));
				objDestinatario.setNombreCliente(resultSet.getString("nombreDestinatario"));
				objEnvio.setDestinatarioEnvio(objDestinatario);
								
				
				Ruta objRuta = new Ruta();
				objRuta.setIdRuta(resultSet.getInt("idRuta"));
				
				Ciudad objCiudadOrigen =new Ciudad();
				objCiudadOrigen.setNombreCiudad(resultSet.getString("nombreCiudadOrigen"));
				objRuta.setCiudadOrigen(objCiudadOrigen);
				
				Ciudad objCiudadDestino =new Ciudad();
				objCiudadDestino.setNombreCiudad(resultSet.getString("nombreCiudadDestino"));
				objRuta.setCiudadDestino(objCiudadDestino);
				
				objEnvio.setRutaEnvio(objRuta);
				
				listEnvio.add(objEnvio);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listEnvio;
	}
	
	public Boolean actualizarEnvioEstadoX(int codigoEnvio) throws Exception {
		Connection connection = DAOConexion.Instancia().conectar();
		Boolean respuesta=false;
		try {
			/*
			 @prmstrCodigoEnvio int
			 */
					
			CallableStatement callableStatement = connection.prepareCall("{call ACT_EnvioEstadoX(?)}");
			callableStatement.setInt(1, codigoEnvio);

			int i = callableStatement.executeUpdate();

			if (i > 0)	respuesta = true;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return respuesta;
	}
	
}
