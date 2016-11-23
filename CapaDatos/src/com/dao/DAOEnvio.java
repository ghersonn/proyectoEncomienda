package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.entidades.Ciudad;
import com.entidades.Cliente;
import com.entidades.Envio;
import com.entidades.Paquete;
import com.entidades.Ruta;

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
				
				listEnvio.add(objEnvio);
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return listEnvio;
	}
	//endMetodos
}
