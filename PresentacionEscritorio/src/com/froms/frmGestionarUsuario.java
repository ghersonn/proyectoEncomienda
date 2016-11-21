package com.froms;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dao.DAOUsuario;
import com.entidades.Ruta;
import com.entidades.Usuario;
import com.negocio.NEGRuta;
import com.negocio.NEGUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmGestionarUsuario extends JInternalFrame {
	private JTextField txtUserName;
	private JTextField txtContrasenia;
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
	
	//private JScrollPane scrollPane;
	private String TipoEdicion;
	
	private void LimpiarFormulario(){
		
		txtUserName.setText("");
		txtContrasenia.setText("");
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
		
		txtUserName.setEditable(Edicion);
		txtContrasenia.setEditable(Edicion);
		txtNombre.setEditable(Edicion);
		txtApellido.setEditable(Edicion);
		txtDni.setEditable(Edicion);
		txtTelefono.setEditable(Edicion);
			
		jtlLista.enable(!Edicion);
	}

	private void ListarUsuarios(){
		try {
			
			ArrayList<Usuario> lista = NEGUsuario.Instancia().listarUsuario();
			((DefaultTableModel)jtlLista.getModel()).setRowCount(0);
			
			for (Usuario usuario : lista) {
				
				int idUsuario = usuario.getIdUsuario();
				String UserNameUsuario = usuario.getUserNameUsuario();
				String ContraseniaUsuario= usuario.getContraseniaUsuario();
				String NombreUsuario = usuario.getNombreUsuario();
				String ApellidoUsuario = usuario.getApellidoUsuario();
				String TelefonoUsuario = usuario.getTelefonoUsuario();
				String DniUsuario = usuario.getDniUsuario();
								
				Object[] xyz = new Object[]{idUsuario,UserNameUsuario,ContraseniaUsuario,NombreUsuario, ApellidoUsuario,TelefonoUsuario,DniUsuario};
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
					frmGestionarUsuario frame = new frmGestionarUsuario();
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
	public frmGestionarUsuario() {
		setBounds(100, 100, 532, 465);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Datos del Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 11, 484, 192);
		getContentPane().add(panel);
		
		JLabel lblUserName = new JLabel("UserName:");
		lblUserName.setBounds(10, 30, 93, 14);
		panel.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setEditable(false);
		txtUserName.setColumns(10);
		txtUserName.setBounds(113, 30, 303, 20);
		panel.add(txtUserName);
		
		JLabel lblContrasenia = new JLabel("Contrasenia:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenia.setBounds(10, 58, 93, 14);
		panel.add(lblContrasenia);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setEditable(false);
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(113, 58, 303, 20);
		panel.add(txtContrasenia);
		
		JLabel lblNombre = new JLabel("Nombres:");
		lblNombre.setBounds(10, 86, 93, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(10, 114, 93, 14);
		panel.add(lblApellido);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(113, 86, 303, 20);
		panel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(113, 114, 303, 20);
		panel.add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		txtDni.setColumns(10);
		txtDni.setBounds(113, 170, 303, 20);
		panel.add(txtDni);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(10, 170, 93, 14);
		panel.add(lblDni);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 142, 93, 14);
		panel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(113, 142, 303, 20);
		panel.add(txtTelefono);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 214, 484, 158);
		getContentPane().add(scrollPane);
				
		jtlLista = new JTable();
		jtlLista.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0){
			
				try {
					int fila = jtlLista.getSelectedRow();
					int id = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
					
					Usuario u = NEGUsuario.Instancia().obtenerUsuario(id);
					if(u!=null){
					    //txtIdUsuario.setText(String.valueOf(u.getIdUsuario()));
						txtUserName.setText(u.getUserNameUsuario());
						txtContrasenia.setText(u.getContraseniaUsuario());
						txtNombre.setText(u.getNombreUsuario());
						txtApellido.setText(u.getApellidoUsuario());
						txtTelefono.setText(u.getTelefonoUsuario());
						txtDni.setText(u.getDniUsuario());
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
				"idUsuario", "UserName", "Contraseña", "Nombres", "Apellidos", "Telefono", "Dni"
			}
		));
		scrollPane.setViewportView(jtlLista);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(22, 380, 484, 33);
		getContentPane().add(panel_1);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HabilitarControles(true);
				LimpiarFormulario();
				TipoEdicion="N";
			}
		});
		//btnNuevo.setEnabled(true);
		btnNuevo.setBounds(10, 5, 71, 23);
		panel_1.add(btnNuevo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarControles(true);
				TipoEdicion="E";
			}
		});
		//btnEditar.setEnabled(true);
		btnEditar.setBounds(91, 5, 71, 23);
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//Inicio Eliminar
				try{
				int fila = jtlLista.getSelectedRow();
				int idUsuario = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
				
				
				int i = JOptionPane.showConfirmDialog(null, 
							"Esta seguro de eliminar?", 
							"sistema Encomienda", JOptionPane.YES_NO_OPTION);
				
				if(i==JOptionPane.YES_OPTION){
					
					Boolean x = NEGUsuario.Instancia().eliminarUsuario(idUsuario);
					if(x){
						JOptionPane.showMessageDialog(null,
						"Se Elimino satisfactoriamente", 
						"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
						ListarUsuarios();
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
		//btnEliminar.setEnabled(true);
		btnEliminar.setBounds(172, 5, 69, 23);
		panel_1.add(btnEliminar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//Inicio Guardar
				
				try{
					
					if(txtUserName.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el User Name del Usuario", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}					
					if(txtContrasenia.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar la contraseña del usuario", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}					
									
					if(txtNombre.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el nombre del usuario", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtApellido.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el apellido del usuario", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}					
					if(txtTelefono.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el telefono del usuario", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtDni.getText().equals("")){
						JOptionPane.showMessageDialog(null,
						"Debe ingresar el Dni del usuario", 
						"Sistema Encomienda",JOptionPane.WARNING_MESSAGE);
						return;
					}					

					
					
					if(TipoEdicion.equals("N")){
						//guardar el usuario
						Usuario u = new Usuario();
						u.setUserNameUsuario(txtUserName.getText());
						u.setContraseniaUsuario(txtContrasenia.getText());
						u.setNombreUsuario(txtNombre.getText());
						u.setApellidoUsuario(txtApellido.getText());
						u.setTelefonoUsuario(txtTelefono.getText());
						u.setDniUsuario(txtDni.getText());
						
						Boolean x = NEGUsuario.Instancia().insertarUsuario(u);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se inserto satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarUsuarios();
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo insertar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}
					}else{
						//editar el usuario
						
						jtlLista.enable(false);
						int fila = jtlLista.getSelectedRow();
						int idUsuario = Integer.parseInt(jtlLista.getValueAt(fila, 0).toString());
												
						Usuario u = new Usuario();
						
						u.setIdUsuario(idUsuario);
						u.setUserNameUsuario(txtUserName.getText());
						u.setContraseniaUsuario(txtContrasenia.getText());
						u.setNombreUsuario(txtNombre.getText());
						u.setApellidoUsuario(txtApellido.getText());
						u.setTelefonoUsuario(txtTelefono.getText());
						u.setDniUsuario(txtDni.getText());
						
						
						Boolean x =NEGUsuario.Instancia().modificarUsuario(u);
						if(x){
							JOptionPane.showMessageDialog(null,
							"Se Edito satisfactoriamente", 
							"Sistema Encomienda",JOptionPane.INFORMATION_MESSAGE);
							HabilitarControles(false);
							ListarUsuarios();
						}else{
							JOptionPane.showMessageDialog(null,
							"No se pudo editar", 
							"Sistema Encomienda",JOptionPane.OK_OPTION);
						}						
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,
							ex.getMessage(), "Sistema Farmacia", 
							JOptionPane.ERROR_MESSAGE);					
				}
				
			//Fin Guardar
			}
		});
		//btnGuardar.setEnabled(false);
		btnGuardar.setBounds(251, 5, 75, 23);
		panel_1.add(btnGuardar);
		
				
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarControles(false);
				LimpiarFormulario();
			}
		});
		//btnCancelar.setEnabled(false);
		btnCancelar.setBounds(336, 5, 75, 23);
		panel_1.add(btnCancelar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		//btnSalir.setEnabled(true);
		btnSalir.setBounds(421, 5, 53, 23);
		panel_1.add(btnSalir);

		
		ListarUsuarios();
		HabilitarControles(false);
	}
}
