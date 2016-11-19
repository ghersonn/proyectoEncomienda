package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

import com.entidades.Envio;
import com.entidades.Paquete;

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
			
			/*REGISTRAR ENVIO*/			
			CallableStatement cst1 = cn.prepareCall("{call REG_InsEnvio(?,?,?,?,?,?,?)}");
	
			/*
			 	--@prmstrId int 
				--,@prmstrCodigoGenerado int
				@prmstrFechaEmision date
				,@prmstrFechaLlegada date
				--,@prmstrFechaEntrega date
				,@prmstrMontoTotal decimal(10,10)
				--,@prmstrEstadoPago bit
				--,@prmstrEstadoEnvio char(1)
				--,@prmstrEstado bit
				,@prmstrIdRemitente int
				,@prmstrIdDestinatario int
				,@prmstrIdRuta int
				--,@prmstrIdViaje int
				,@prmstrIdUsuario int
			*/
			
			cst1.setDate(1, (Date)objEnvio.getFechaEmisionEnvio());
			cst1.setDate(2, (Date)objEnvio.getFechaLlegadaEnvio());
			cst1.setBigDecimal(3, objEnvio.getMontoTotalEnvio());
			cst1.setInt(4, objEnvio.getRemitenteEnvio().getIdCliente());
			cst1.setInt(5, objEnvio.getDestinatarioEnvio().getIdCliente());
			cst1.setInt(6, objEnvio.getRutaEnvio().getIdRuta());
			cst1.setInt(7, objEnvio.getUsuarioEnvio().getIdUsuario());
			
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
	//endMetodos
}
