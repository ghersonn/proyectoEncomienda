package com.negocio;

import java.util.ArrayList;

import com.dao.DAOUnidadTransporte;
import com.entidades.UnidadTransporte;

public class NEGUnidadTransporte {
	// Singleton
	   public static NEGUnidadTransporte  _Instancia;
	   private NEGUnidadTransporte () {};
	   public static NEGUnidadTransporte  Instancia() {
			if (_Instancia == null) {
				_Instancia = new NEGUnidadTransporte ();
				}
				return _Instancia;
			}
	//endSingleton
	   
	   
	   //metodo
	   
	   public ArrayList<UnidadTransporte> listarUnidadTransporte() throws Exception {
			try {
				return DAOUnidadTransporte.Instancia().listarUnidadTransporte();
				} catch (Exception e) {
					throw e;
				}
			}	
	   
	   public UnidadTransporte obtenerUnidadTransporte(int idUnidadTransporte) throws Exception {
			UnidadTransporte objUnidadTransporte = null;
			try {
				if(idUnidadTransporte<=0){
					throw new ArithmeticException("La unidad de transporte no existe");
				}
				objUnidadTransporte = DAOUnidadTransporte.Instancia().obtenerUnidadTransporte(idUnidadTransporte);
			} catch (Exception e) {
				throw e;
			}
			return objUnidadTransporte;
		}
	   
	   public Boolean insertarUnidadTransporte(UnidadTransporte objUnidadTransporte) throws Exception {
			Boolean respuesta=false;
			try {
				
				for (UnidadTransporte c : DAOUnidadTransporte.Instancia().listarUnidadTransporte())
				{
					if (c.getMatriculaUnidadTransporte().equals(objUnidadTransporte.getMatriculaUnidadTransporte()))
						throw new Exception("La UnidadTransporte ya existe");
				}
					
				respuesta = DAOUnidadTransporte.Instancia().insertarUnidadTransporte(objUnidadTransporte);
				
			} catch (Exception e) {
				throw e;
			}
			
			return respuesta;
		}
	   
	   public Boolean modificarUnidadTransporte(UnidadTransporte objUnidadTransporte) throws Exception {
			
			Boolean respuesta=false;
			try {
				respuesta = DAOUnidadTransporte.Instancia().modificarUnidadTransporte(objUnidadTransporte);
			} catch (Exception e) {
				throw e;
			}
			return respuesta;
		}
	   
	   public Boolean eliminarUnidadTransporte(int idUnidadTransporte) throws Exception {
			
			Boolean respuesta=false;
			try {
				respuesta = DAOUnidadTransporte.Instancia().eliminarUnidadTransporte(idUnidadTransporte);
			} catch (Exception e) {
				throw e;
			}
			return respuesta;
		}
	   
	   //endMetodo
	   
}
