package com.negocio;

import java.util.ArrayList;

import com.dao.DAORuta;
import com.entidades.Ruta;

public class NEGRuta {

	
	// Singleton
	   public static NEGRuta _Instancia;

       private NEGRuta() {};

	   public static NEGRuta Instancia() {
			if (_Instancia == null) {
				_Instancia = new NEGRuta();
				}
				return _Instancia;
			}
	//endSingleton
			
			
	//Metodos
		public ArrayList<Ruta> listarRuta() throws Exception {
			try {
				return DAORuta.Instancia().listarRuta();
				} catch (Exception e) {
					throw e;
				}
			}		
		
		public Ruta obtenerRuta(int idRuta) throws Exception {
			Ruta objRuta = null;
			try {
				objRuta = DAORuta.Instancia().obtenerRuta(idRuta);
				if(objRuta==null){
					throw new ArithmeticException("seleccione una ruta existente");
				}
			} catch (Exception e) {
				throw e;
			}
			return objRuta;
		}
		
		public Boolean insertarRuta(Ruta objRuta) throws Exception {
			Boolean respuesta=false;
			try {
				
								
				respuesta = DAORuta.Instancia().insertarRuta(objRuta);
				
			} catch (Exception e) {
				throw e;
			}
			
			return respuesta;
		}
		
		public Boolean modificarRuta(Ruta objRuta) throws Exception {
			
			Boolean respuesta=false;
			try {
				respuesta = DAORuta.Instancia().modificarRuta(objRuta);
			} catch (Exception e) {
				throw e;
			}
			return respuesta;
		}
		
		public Boolean eliminarRuta(int idRuta) throws Exception {
			
			Boolean respuesta=false;
			try {
				respuesta = DAORuta.Instancia().eliminarRuta(idRuta);
			} catch (Exception e) {
				throw e;
			}
			return respuesta;
		}
}
