package com.negocio;

import com.dao.DAOCliente;
import com.entidades.Cliente;

public class NEGCliente {
	// Singleton
	public static NEGCliente _Instancia;
    private NEGCliente() {};
	   public static NEGCliente Instancia() {
			if (_Instancia == null) {
				_Instancia = new NEGCliente();
				}
				return _Instancia;
			}
	//endSingleton
	//Metodos
	public Cliente obtenerClienteDNI(String dni) throws Exception {
		Cliente objCliente = null;
		try {
			objCliente = DAOCliente.Instancia().obtenerClienteDNI(dni);
		} catch (Exception e) {
			throw e;
		}
		return objCliente;
	}
	
	public Cliente obtenerClienteID(int idCliente) throws Exception {
		Cliente objCliente = null;
		try {
			objCliente = DAOCliente.Instancia().obtenerClienteID(idCliente);
		} catch (Exception e) {
			throw e;
		}
		return objCliente;
	}
	
	public boolean insertarCliente(Cliente objCliente) throws Exception {	
		try {
		return DAOCliente.Instancia().insertarCliente(objCliente);
	} catch (Exception e) {
		throw e;
	}
	}
}
