package com.negocio;

import com.dao.DAOViaje;
import com.entidades.Envio;

public class NEGViaje {
	// Singleton
	   public static NEGViaje _Instancia;

    private NEGViaje() {};

	   public static NEGViaje Instancia() {
			if (_Instancia == null) {
				_Instancia = new NEGViaje();
				}
				return _Instancia;
			}
	//endSingleton
	   
	   //Metodo
	   
	   public Boolean insertarViaje(Envio objEnvio) throws Exception {
		   Boolean verificar=false;
		   try {
			verificar = DAOViaje.Instancia().insertarViaje(objEnvio);
		} catch (Exception e) {
			// TODO: handle exception
		}
		   return verificar;
	   }
	   
	   //endMetodo
}
