package com.froms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.dao.DAOCiudad;
import com.entidades.Chofer;
import com.entidades.Ciudad;
import com.entidades.Ruta;
import com.entidades.UnidadTransporte;
import com.negocio.NEGChofer;
import com.negocio.NEGRuta;
import com.negocio.NEGUnidadTransporte;

import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class frmGestionarUnidadTransporte extends JInternalFrame {
	private JTable jtlLista;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtModelo;
	
	private JComboBox cmbChofer;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	
	private String TipoEdicion;
	
	
	private void LlenarComboChofer(){
		try {
			DefaultComboBoxModel x = new DefaultComboBoxModel();
			cmbChofer.setModel(x);
			
			ArrayList<Chofer> lista = NEGChofer.Instancia().listarChofer();
			for (Chofer c : lista) {
				x.addElement(c.getIdChofer());
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				e.getMessage(), "Sistema Encomienda", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private void LimpiarFormulario(){
		txtMatricula.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
	}
	
private void HabilitarControles(Boolean Edicion){
		
		btnNuevo.setEnabled(!Edicion);
		btnEditar.setEnabled(!Edicion);
		btnEliminar.setEnabled(!Edicion);
		btnGuardar.setEnabled(Edicion);
		btnCancelar.setEnabled(Edicion);
		btnSalir.setEnabled(!Edicion);
		
		txtMatricula.setEditable(Edicion);
		txtMarca.setEditable(Edicion);
		txtModelo.setEditable(Edicion);
		cmbChofer.enable(Edicion);
	   // cmbCiudadDestino.enable(Edicion);

		jtlLista.enable(!Edicion);
	}

	private void ListarUnidadTransporte(){
		try {
			ArrayList<UnidadTransporte> lista = NEGUnidadTransporte.Instancia().listarUnidadTransporte();
			
			((DefaultTableModel)jtlLista.getModel()).setRowCount(0);
			
			for (UnidadTransporte u : lista) {
				u.setChoferUnidadTransporte(NEGChofer.Instancia().obtenerChofer(u.getChoferUnidadTransporte().getIdChofer()));
				
				int idUnidadTransporte = u.getIdUnidadTransporte();
				String matricula = u.getMatriculaUnidadTransporte();
				String marca = u.getMarcaUnidadTransporte();
				String modelo = u.getModeloUnidadTransporte();
				String chofer = u.getChoferUnidadTransporte().getNombreChofer();
				
				Object[] xyz = new Object[]{idUnidadTransporte,matricula,marca, modelo, chofer};
				((DefaultTableModel)jtlLista.getModel()).addRow(xyz);				
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
					frmGestionarUnidadTransporte frame = new frmGestionarUnidadTransporte();
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
	public frmGestionarUnidadTransporte() {
		setBounds(100, 100, 569, 415);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 195, 501, 108);
		getContentPane().add(scrollPane);
		
		jtlLista = new JTable();
		jtlLista.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0){
				
				try {
					int fila = jtlLista.getSelectedRow();
					int id = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
					
					UnidadTransporte u = NEGUnidadTransporte.Instancia().obtenerUnidadTransporte(id);
					if(u!=null){
						
						//cmbCiudadDestino.setSelectedItem(String.valueOf(u.getCiudadDestino()));
						//cmbCiudadOrigen.setSelectedItem(String.valueOf(u.getCiudadOrigen()));
						txtMatricula.setText(String.valueOf(u.getMatriculaUnidadTransporte()));
						txtMarca.setText(String.valueOf(u.getMarcaUnidadTransporte()));
						txtModelo.setText(String.valueOf(u.getModeloUnidadTransporte()));
				
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							e.getMessage(), 
							"Sistema", 
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		scrollPane.setViewportView(jtlLista);
		jtlLista.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"idUnidadTransporte", "Matricula", "Marca", "Modelo","Chofer"
			}
		));
		scrollPane.setColumnHeaderView(jtlLista);
		scrollPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{jtlLista}));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Datos de la Unidad Transporte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 11, 501, 161);
		getContentPane().add(panel);
		
		JLabel lblChofer = new JLabel("Chofer:");
		lblChofer.setBounds(10, 119, 85, 14);
		panel.add(lblChofer);
		
		cmbChofer = new JComboBox();
		cmbChofer.setBounds(108, 116, 355, 20);
		panel.add(cmbChofer);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 59, 87, 14);
		panel.add(lblMarca);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 23, 90, 14);
		panel.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setEditable(false);
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(108, 21, 355, 20);
		panel.add(txtMatricula);
		
		txtMarca = new JTextField();
		txtMarca.setEditable(false);
		txtMarca.setColumns(10);
		txtMarca.setBounds(108, 53, 355, 20);
		panel.add(txtMarca);
		
		txtModelo = new JTextField();
		txtModelo.setEditable(false);
		txtModelo.setBounds(108, 84, 355, 20);
		panel.add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 90, 46, 14);
		panel.add(lblModelo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(21, 321, 501, 43);
		getContentPane().add(panel_1);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setEnabled(true);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HabilitarControles(true);
				LimpiarFormulario();
				TipoEdicion="N";
			}
		});
		btnNuevo.setBounds(0, 11, 71, 23);
		panel_1.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(true);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarControles(true);
				TipoEdicion="E";
			}
		});
		btnEditar.setBounds(73, 11, 71, 23);
		panel_1.add(btnEditar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HabilitarControles(false);
				LimpiarFormulario();
			}
		});
		btnCancelar.setBounds(337, 11, 91, 23);
		panel_1.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					if(txtMatricula.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el dia demora", 
						"Sistema Farmacia",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtMarca.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el precio", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}	
					if(txtModelo.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el precio", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}	
					
					if(TipoEdicion.equals("N")){
						//guardar 
						
						Chofer c = new Chofer();
						c.setIdChofer((int)cmbChofer.getSelectedItem());
						
						UnidadTransporte u = new UnidadTransporte();
						u.setMarcaUnidadTransporte(txtMarca.getText());
						u.setMatriculaUnidadTransporte(txtMatricula.getText());
						u.setModeloUnidadTransporte(txtModelo.getText());
						u.setChoferUnidadTransporte(c);
						
						Boolean x = NEGUnidadTransporte.Instancia().insertarUnidadTransporte(u);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se inserto satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarUnidadTransporte();
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo insertar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}
					}else{
						//editar el cliente
						jtlLista.enable(false);
						int fila = jtlLista.getSelectedRow();
						int id = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
						
						Chofer c = new Chofer();
						c.setIdChofer((int)cmbChofer.getSelectedItem());
						
						UnidadTransporte u = new UnidadTransporte();
						u.setIdUnidadTransporte(id);
						u.setMarcaUnidadTransporte(txtMarca.getText());
						u.setMatriculaUnidadTransporte(txtMatricula.getText());
						u.setModeloUnidadTransporte(txtModelo.getText());
						u.setChoferUnidadTransporte(c);
						
						Boolean x = NEGUnidadTransporte.Instancia().modificarUnidadTransporte(u);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se Edito satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarUnidadTransporte();
							jtlLista.enable(true);
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo editar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}						
					}
								
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							ex.getMessage(), "Sistema Encomienda", 
							JOptionPane.ERROR_MESSAGE);		
				}	
		}
	});
		btnGuardar.setBounds(246, 11, 81, 23);
		panel_1.add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(true);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					int fila = jtlLista.getSelectedRow();
					int id = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
					
					
					int i = JOptionPane.showConfirmDialog(null, 
								"Esta seguro de eliminar?", 
								"sistema Encomienda", JOptionPane.YES_NO_OPTION);
					
					if(i==JOptionPane.YES_OPTION){
						
						Boolean x = NEGUnidadTransporte.Instancia().eliminarUnidadTransporte(id);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se Elimino satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							ListarUnidadTransporte();
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
		btnEliminar.setBounds(154, 11, 84, 23);
		panel_1.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setEnabled(true);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(438, 11, 63, 23);
		panel_1.add(btnSalir);
		
		ListarUnidadTransporte();
		HabilitarControles(false);
		LlenarComboChofer();

	}
}
