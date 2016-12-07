package com.froms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entidades.Usuario;
import com.negocio.NEGUsuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class frmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
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
	public frmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(174, 88, 210, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(50, 91, 114, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(50, 131, 114, 14);
		contentPane.add(lblPassword);
		
		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Usuario u = NEGUsuario.Instancia().VerificarAcceso(txtUsuario.getText(), txtPassword.getText());
					if(u!=null){
						frmPrincipal p = new frmPrincipal();
						p.setVisible(true);
						dispose();
						JOptionPane.showMessageDialog(null,
								"BIENVENIDO "+u.getNombreUsuario()+u.getApellidoUsuario(), 
								"Sistema Encomienda", 
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							e.getMessage(), 
							"Sistema Encomienda", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIniciarSesion.setBounds(75, 180, 282, 23);
		contentPane.add(btnIniciarSesion);
		
		JLabel lblSistemaDeEncomiendas = new JLabel("SISTEMA DE ENCOMIENDAS");
		lblSistemaDeEncomiendas.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSistemaDeEncomiendas.setBounds(75, 28, 282, 20);
		contentPane.add(lblSistemaDeEncomiendas);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(174, 128, 210, 20);
		contentPane.add(txtPassword);
	}
}
