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
				objUnidadTransporte = DAOUnidadTransporte.Instancia().obtenerUnidadTransporte(idUnidadTransporte);
			} catch (Exception e) {
				throw e;
			}
			return objUnidadTransporte;
		}
	   //endMetodo
	   
}
