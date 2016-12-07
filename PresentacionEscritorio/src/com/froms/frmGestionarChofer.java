package com.froms;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entidades.Chofer;
import com.entidades.Usuario;
import com.negocio.NEGChofer;
import com.negocio.NEGUsuario;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmGestionarChofer extends JInternalFrame {
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JTable jtlLista;


	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	
	private String TipoEdicion;

	
private void LimpiarFormulario(){
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtTelefono.setText("");
	}
	
	private void HabilitarControles(Boolean Edicion){
		
		/*btnNuevo.setEnabled(!Edicion);
		btnEditar.setEnabled(!Edicion);
		btnEliminar.setEnabled(!Edicion);
		btnGuardar.setEnabled(Edicion);
		btnCancelar.setEnabled(Edicion);
		btnSalir.setEnabled(!Edicion);*/
		
		txtNombre.setEditable(Edicion);
		txtApellido.setEditable(Edicion);
		txtDni.setEditable(Edicion);
		txtTelefono.setEditable(Edicion);
			
		jtlLista.enable(!Edicion);
	}
	
	private void ListarChoferes(){
		try {
			
			ArrayList<Chofer> lista = NEGChofer.Instancia().listarChofer();
			((DefaultTableModel)jtlLista.getModel()).setRowCount(0);
			
			for (Chofer Chofer : lista) {
				
				int idChofer = Chofer.getIdChofer();
				String NombreChofer = Chofer.getNombreChofer();
				String ApellidoChofer = Chofer.getApellidoChofer();
				String TelefonoChofer = Chofer.getTelefonoChofer();
				String DniChofer = Chofer.getDniChofer();
								
				Object[] xyz = new Object[]{idChofer,NombreChofer, ApellidoChofer,TelefonoChofer,DniChofer};
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
					frmGestionarChofer frame = new frmGestionarChofer();
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
	public frmGestionarChofer() {
		setBounds(100, 100, 534, 432);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Chofer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 498, 158);
		getContentPane().add(panel);
		
		JLabel lblNombre = new JLabel("Nombres:");
		lblNombre.setBounds(10, 29, 93, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(10, 57, 93, 14);
		panel.add(lblApellido);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(113, 29, 303, 20);
		panel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(113, 57, 303, 20);
		panel.add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		txtDni.setColumns(10);
		txtDni.setBounds(113, 113, 303, 20);
		panel.add(txtDni);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(10, 113, 93, 14);
		panel.add(lblDni);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 85, 93, 14);
		panel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(113, 85, 303, 20);
		panel.add(txtTelefono);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 494, 158);
		getContentPane().add(scrollPane);
		
		jtlLista = new JTable();
		jtlLista.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0){
			
				try {
					int fila = jtlLista.getSelectedRow();
					int id = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
					
					Chofer u = NEGChofer.Instancia().obtenerChofer(id);
					if(u!=null){
					    //txtIdUsuario.setText(String.valueOf(u.getIdUsuario()));
						txtNombre.setText(u.getNombreChofer());
						txtApellido.setText(u.getApellidoChofer());
						txtTelefono.setText(u.getTelefonoChofer());
						txtDni.setText(u.getDniChofer());
					}
				
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							e.getMessage(), 
							"Sistema Encomienda", 
							JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
		jtlLista.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdChofer","Nombres", "Apellidos", "Telefono", "Dni"
			}
		));
		scrollPane.setViewportView(jtlLista);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 349, 494, 33);
		getContentPane().add(panel_1);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HabilitarControles(true);
				LimpiarFormulario();
				TipoEdicion="N";
			}
		});
		btnNuevo.setBounds(10, 5, 71, 23);
		panel_1.add(btnNuevo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarControles(true);
				TipoEdicion="E";
			}
		});
		btnEditar.setBounds(91, 5, 71, 23);
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Inicio Eliminar
				try{
				int fila = jtlLista.getSelectedRow();
				int idChofer = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
				
				
				int i = JOptionPane.showConfirmDialog(null, 
							"Esta seguro de eliminar?", 
							"sistema Encomienda", JOptionPane.YES_NO_OPTION);
				
				if(i==JOptionPane.YES_OPTION){
					
					Boolean x = NEGChofer.Instancia().eliminarChofer(idChofer);
					if(x){
						JOptionPane.showMessageDialog(null,
						"Se Elimino satisfactoriamente", 
						"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
						ListarChoferes();
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
				
			//Fin elimimar
				
				
			}
		});
		btnEliminar.setBounds(172, 5, 69, 23);
		panel_1.add(btnEliminar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
               //Inicio Guardar
				
				try{
									
					/*if(txtNombre.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el nombre del Chofer", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtApellido.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el apellido del Chofer", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}					
					if(txtTelefono.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el telefono del Chofer", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtDni.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el Dni del Chofer", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}	*/
					
					if(TipoEdicion.equals("N")){
						//guardar el Chofer
						Chofer c = new Chofer();
						c.setNombreChofer(txtNombre.getText());
						c.setApellidoChofer(txtApellido.getText());
						c.setTelefonoChofer(txtTelefono.getText());
						c.setDniChofer(txtDni.getText());
						
						Boolean x = NEGChofer.Instancia().insertarChofer(c);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se inserto satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarChoferes();
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo insertar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}
					}else{
						//editar el Chofer
						
						jtlLista.enable(false);
						int fila = jtlLista.getSelectedRow();
						int idChofer = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
												
						Chofer c = new Chofer();
						
						c.setIdChofer(idChofer);
						c.setNombreChofer(txtNombre.getText());
						c.setApellidoChofer(txtApellido.getText());
						c.setTelefonoChofer(txtTelefono.getText());
						c.setDniChofer(txtDni.getText());
						
						Boolean x =NEGChofer.Instancia().modificarChofer(c);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se Edito satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarChoferes();
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo editar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}						
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,
							ex.getMessage(), "Sistema Encomienda", 
							JOptionPane.ERROR_MESSAGE);					
				}
				
			//Fin Guardar
				
			}
		});
		btnGuardar.setBounds(251, 5, 75, 23);
		panel_1.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarControles(false);
				LimpiarFormulario();
			}
		});
		btnCancelar.setBounds(336, 5, 75, 23);
		panel_1.add(btnCancelar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(421, 5, 63, 23);
		panel_1.add(btnSalir);

		ListarChoferes();
		HabilitarControles(false);
	}
}
