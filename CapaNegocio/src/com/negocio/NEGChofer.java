package com.negocio;

import java.util.ArrayList;

import com.dao.DAOChofer;
import com.entidades.Chofer;

public class NEGChofer {
	//Singleton
			public static NEGChofer _Instancia;
			
			private NEGChofer() {};
			
			public static NEGChofer Instancia() {
				if (_Instancia == null) {
					_Instancia = new NEGChofer();
				}
				return _Instancia;
			}
			
	//endSingleton
	
	//Metodos
	
			public ArrayList<Chofer> listarChofer() throws Exception {
				try {
					return DAOChofer.Instancia().listarChofer();
				} catch (Exception e) {
					throw e;
				}
			}
				
			public Chofer obtenerChofer(int idChofer) throws Exception {
				Chofer objChofer= null;
				try {
					objChofer = DAOChofer.Instancia().obtenerChofer(idChofer);
				} catch (Exception e) {
					throw e;
				}
				return objChofer;
			}
			
			public ArrayList<Chofer> buscarChoferes(String nombre) throws Exception {
				ArrayList<Chofer> listChofer = new ArrayList<Chofer>();
				try {
					listChofer = DAOChofer.Instancia().buscarChoferes(nombre);
				} catch (Exception e) {
					throw e;
				}
				return listChofer;
			}

			public Boolean insertarChofer(Chofer objChofer) throws Exception {
				Boolean respuesta=false;
				try {
					
					for (Chofer c : DAOChofer.Instancia().listarChofer() )
					{
						if (c.getDniChofer().equals(objChofer.getDniChofer()) && c.getIdChofer()!=objChofer.getIdChofer())
							throw new Exception("El DNI del Chofer ya existe");
					}
					
					String msj=""; Boolean verificar=false;
					
					if(objChofer.getNombreChofer().equals("")||objChofer.getNombreChofer()==null){msj=msj+" | Nombre "; verificar=true;}
					if(objChofer.getApellidoChofer().equals("")||objChofer.getApellidoChofer()==null){msj=msj+" | Apellido "; verificar=true;}
					if(objChofer.getDniChofer().equals("")||objChofer.getDniChofer()==null){msj=msj+" | Dni "; verificar=true;}
							
					if(verificar){throw new Exception("Los datos:  "+msj+"son obligatorios.");}
					
					respuesta = DAOChofer.Instancia().insertarChofer(objChofer);
				} catch (Exception e) {
					throw e;
				}
				
				return respuesta;
			}

			public Boolean modificarChofer(Chofer objChofer) throws Exception {
				Boolean respuesta=false;
				try {
					
					for (Chofer c : DAOChofer.Instancia().listarChofer() )
					{
						if (c.getDniChofer().equals(objChofer.getDniChofer()) && c.getIdChofer()!=objChofer.getIdChofer())
							throw new Exception("El DNI del Chofer ya existe");
					}
					
					String msj=""; Boolean verificar=false;
					
					if(objChofer.getNombreChofer().equals("")||objChofer.getNombreChofer()==null){msj=msj+" | Nombre "; verificar=true;}
					if(objChofer.getApellidoChofer().equals("")||objChofer.getApellidoChofer()==null){msj=msj+" | Apellido "; verificar=true;}
					if(objChofer.getDniChofer().equals("")||objChofer.getDniChofer()==null){msj=msj+" | Dni "; verificar=true;}
							
					if(verificar){throw new Exception("Los datos:  "+msj+"son obligatorios.");}
					
					
					respuesta = DAOChofer.Instancia().modificarChofer(objChofer);
				} catch (Exception e) {
					throw e;
				}
				return respuesta;
			}

			public Boolean eliminarChofer(int idChofer) throws Exception {
				Boolean respuesta=false;
				try {
					respuesta = DAOChofer.Instancia().eliminarChofer(idChofer);
				} catch (Exception e) {
					throw e;
				}
				return respuesta;
			}

			
			
}
