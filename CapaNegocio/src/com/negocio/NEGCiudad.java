package com.negocio;

import java.util.ArrayList;

import com.dao.DAOCiudad;
import com.entidades.Ciudad;

public class NEGCiudad {
	
	// Singleton
		public static  NEGCiudad _Instancia;
		private  NEGCiudad() {};
		public static  NEGCiudad Instancia() {
			if (_Instancia == null) {
				_Instancia = new  NEGCiudad();
			}
			return _Instancia;
		}
		// endSingleton

		
		//Metodos
		public ArrayList<Ciudad> listarCiudad() throws Exception {
			try {
				return DAOCiudad.Instancia().listarCiudad();
				} catch (Exception e) {
						throw e;
				}
				}
				
}
