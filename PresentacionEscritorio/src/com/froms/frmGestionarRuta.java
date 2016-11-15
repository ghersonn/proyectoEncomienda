package com.froms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.entidades.Ciudad;
import com.entidades.Ruta;
import com.negocio.NEGCiudad;
import com.negocio.NEGRuta;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class frmGestionarRuta extends JInternalFrame {
	private JTable table;
	private JTextField txtDiasDemora;
	private JTextField txtPrecio;

	private JComboBox cmbCiudadOrigen;
	private JComboBox cmbCiudadDestino;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	
	private String TipoEdicion;
	
	
	private void LimpiarFormulario(){
		txtDiasDemora.setText("");
		txtPrecio.setText("");
		
	}
		
	private void LlenarComboOrigen(){
		try {
			DefaultComboBoxModel x = new DefaultComboBoxModel();
			cmbCiudadOrigen.setModel(x);
			
			
			ArrayList<Ciudad> lista = NEGCiudad.Instancia().listarCiudad();
						
			for (Ciudad ciudad : lista) {
				x.addElement(ciudad.getIdCiudad());
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				e.getMessage(), "Sistema Encomiendas", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private void LlenarComboDestino(){
		try {
			DefaultComboBoxModel y = new DefaultComboBoxModel();
			cmbCiudadDestino.setModel(y);
			
			ArrayList<Ciudad> lista = NEGCiudad.Instancia().listarCiudad();
						
			for (Ciudad ciudad : lista) {
				y.addElement(ciudad.getIdCiudad());
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				e.getMessage(), "Sistema Encomiendas", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
     private void HabilitarControles(Boolean Edicion){
		
		btnNuevo.setEnabled(!Edicion);
		btnEditar.setEnabled(!Edicion);
		btnEliminar.setEnabled(!Edicion);
		btnGuardar.setEnabled(Edicion);
		btnCancelar.setEnabled(Edicion);
		btnSalir.setEnabled(!Edicion);
		
		txtDiasDemora.setEditable(Edicion);
		txtPrecio.setEditable(Edicion);
		//cmbCiudadOrigen.enable(Edicion);
		//cmbCiudadDestino.enable(Edicion);

		table.enable(!Edicion);
	}
     
     private void ListarRutas(){
 		try {
 			ArrayList<Ruta> lista = NEGRuta.Instancia().listarRuta();
 			
 			((DefaultTableModel)table.getModel()).setRowCount(0);
 			
 			for (Ruta ruta : lista) {
 				int idRuta = ruta.getIdRuta();
 				BigDecimal precio = ruta.getPrecioRuta();
 				int diasDemora = ruta.getDiasDemoraRuta();
 				int idCiudadOrigen = ruta.getCiudadOrigen().getIdCiudad();
 				String ciudadOrigen = ruta.getCiudadOrigen().getNombreCiudad();
 				int idCiudadDestino = ruta.getCiudadDestino().getIdCiudad();
 				String ciudadDestino = ruta.getCiudadDestino().getNombreCiudad();
 				
 				Object[] xyz = new Object[]{idRuta, idCiudadOrigen, ciudadOrigen,idCiudadDestino,ciudadDestino,diasDemora,precio};
 				((DefaultTableModel)table.getModel()).addRow(xyz);				
 			}
 			
 		} catch (Exception e) {
 			JOptionPane.showMessageDialog(null,
 					e.getMessage(), 
 					"Sistema Encomienda", 
 					JOptionPane.ERROR_MESSAGE);
 		}		
 	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmGestionarRuta frame = new frmGestionarRuta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		
	/**
	 * Create the frame.
	 */
	public frmGestionarRuta() {
		setBounds(100, 100, 491, 375);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 160, 455, 108);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ciudad Origen", "Ciudad Destino", "Dias Demora", "Precio"
			}
		));
		scrollPane.setColumnHeaderView(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Datos de la Ruta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 455, 138);
		getContentPane().add(panel);
		
		JLabel lblCiudadOrigen = new JLabel("Ciudad Origen:");
		lblCiudadOrigen.setBounds(10, 24, 85, 14);
		panel.add(lblCiudadOrigen);
		
		JLabel lblCiudadDestino = new JLabel("Ciudad Destino:");
		lblCiudadDestino.setBounds(10, 50, 86, 14);
		panel.add(lblCiudadDestino);
		
		JComboBox cmbCiudadOrigen = new JComboBox();
		cmbCiudadOrigen.setBounds(108, 21, 285, 20);
		panel.add(cmbCiudadOrigen);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 113, 87, 14);
		panel.add(lblPrecio);
		
		JLabel lblDiasDemora = new JLabel("Dias Demora:");
		lblDiasDemora.setBounds(10, 79, 90, 14);
		panel.add(lblDiasDemora);
		
		JComboBox cmbCiudadDestino = new JComboBox();
		cmbCiudadDestino.setBounds(108, 50, 285, 20);
		panel.add(cmbCiudadDestino);
		
		txtDiasDemora = new JTextField();
		txtDiasDemora.setColumns(10);
		txtDiasDemora.setBounds(108, 77, 285, 20);
		panel.add(txtDiasDemora);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(108, 107, 285, 20);
		panel.add(txtPrecio);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 290, 455, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HabilitarControles(true);
				LimpiarFormulario();
				TipoEdicion="N";
			}
		});
		btnNuevo.setBounds(10, 11, 63, 23);
		panel_1.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarControles(true);
				TipoEdicion="E";
			}
		});
		btnEditar.setBounds(83, 11, 63, 23);
		panel_1.add(btnEditar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HabilitarControles(false);
				LimpiarFormulario();
			}
		});
		btnCancelar.setBounds(305, 11, 74, 23);
		panel_1.add(btnCancelar);
		
		//guardar
	    btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					if(txtDiasDemora.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el dia demora", 
						"Sistema Farmacia",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtPrecio.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el precio", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}		
					
					if(TipoEdicion.equals("N")){
						//guardar 
						
						Ciudad co = new Ciudad();
						co.setIdCiudad((int)cmbCiudadOrigen.getSelectedItem());
						
						Ciudad cd = new Ciudad();
						cd.setIdCiudad((int)cmbCiudadDestino.getSelectedItem());
						
						Ruta r = new Ruta();
						r.setDiasDemoraRuta(Integer.parseInt(txtDiasDemora.getText()));
						r.setCiudadOrigen(co);
						r.setCiudadDestino(cd);
						
						// **************   PRECIO   ************************
						DecimalFormatSymbols symbols = new DecimalFormatSymbols();
						symbols.setDecimalSeparator('.');

						String pattern = "#.##";

						DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
						decimalFormat.setParseBigDecimal(true);
						
						try {
							r.setPrecioRuta((BigDecimal) decimalFormat.parse(txtPrecio.getText()));
						} catch (ParseException ex) {
							ex.printStackTrace();
						}		
						
						//***************************************************
						
						
						Boolean x = NEGRuta.Instancia().insertarRuta(r);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se inserto satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarRutas();
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo insertar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}
					}else{
						//editar el cliente
						table.enable(false);
						int fila = table.getSelectedRow();
						int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
						
										
						Ciudad co = new Ciudad();
						co.setIdCiudad((int)cmbCiudadOrigen.getSelectedItem());
						
						Ciudad cd = new Ciudad();
						cd.setIdCiudad((int)cmbCiudadDestino.getSelectedItem());
						
						Ruta r = new Ruta();
						r.setIdRuta(id);
						r.setDiasDemoraRuta(Integer.parseInt(txtDiasDemora.getText()));
						r.setCiudadOrigen(co);
						r.setCiudadDestino(cd);
						
						// **************   PRECIO   ************************
						DecimalFormatSymbols symbols = new DecimalFormatSymbols();
						symbols.setDecimalSeparator('.');

						String pattern = "#.##";

						DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
						decimalFormat.setParseBigDecimal(true);
						
						try {
							r.setPrecioRuta((BigDecimal) decimalFormat.parse(txtPrecio.getText()));
						} catch (ParseException ex) {
							ex.printStackTrace();
						}		
						
						
						Boolean x = NEGRuta.Instancia().modificarRuta(r);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se Edito satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarRutas();
							table.enable(true);
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo editar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}						
					}
					
									
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							ex.getMessage(), "Sistema Farmacia", 
							JOptionPane.ERROR_MESSAGE);		
				}	
								
			
		}
	});
		btnGuardar.setBounds(227, 11, 71, 23);
		panel_1.add(btnGuardar);
		
		//finguardar
		
	    btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					int fila = table.getSelectedRow();
					int idRuta = Integer.parseInt(table.getValueAt(fila, 0).toString());
					
					
					int i = JOptionPane.showConfirmDialog(null, 
								"Esta seguro de eliminar?", 
								"sistema Encomienda", JOptionPane.YES_NO_OPTION);
					
					if(i==JOptionPane.YES_OPTION){
						
						Boolean x = NEGRuta.Instancia().eliminarRuta(idRuta);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se Elimino satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							ListarRutas();
							LimpiarFormulario();
							
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo Eliminar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}		
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							ex.getMessage(), "Sistema Encomienda", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(156, 11, 68, 23);
		panel_1.add(btnEliminar);
		
	    btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(388, 11, 57, 23);
		panel_1.add(btnSalir);

		//FALTA VER LA TABLA
	}
}
