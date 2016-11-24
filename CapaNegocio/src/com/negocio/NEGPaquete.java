package com.negocio;

import java.util.ArrayList;
import java.util.Date;

import com.dao.DAOEnvio;
import com.dao.DAOPaquete;
import com.entidades.Envio;
import com.entidades.Paquete;

public class NEGPaquete {

	       // Singleton
			public static NEGPaquete _Instancia;	
			private NEGPaquete() {
			};	
			public static NEGPaquete Instancia() {
				if (_Instancia == null) {
					_Instancia = new NEGPaquete();
				}
				return _Instancia;
			}
			// endSingleton
			
			public ArrayList<Paquete> listaPaqueteEnvio(int idEnvio) throws Exception {
				try {
					return DAOPaquete.Instancia().listaPaqueteEnvio(idEnvio);
					} catch (Exception e) {
						throw e;
					}
				}

}
