package com.froms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.Frame;

public class frmPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane dspContenedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
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
	public frmPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Sistema de Encomienda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenedor = new JMenu("Mantenedor");
		menuBar.add(mnMantenedor);
		
		JMenuItem menuRuta = new JMenuItem("Ruta");
		menuRuta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				frmGestionarRuta r = new frmGestionarRuta();
				dspContenedor.add(r);
				r.setVisible(true);
				
			}
		});
		mnMantenedor.add(menuRuta);
		
		JMenuItem menuUsuario = new JMenuItem("Usuario");
		menuUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
  				frmGestionarUsuario u = new frmGestionarUsuario();
				dspContenedor.add(u);
				u.setVisible(true);
				
			}
		});
		mnMantenedor.add(menuUsuario);
		
		JMenuItem menuChofer = new JMenuItem("Chofer");
		menuChofer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
  				frmGestionarChofer  c = new frmGestionarChofer();
				dspContenedor.add(c);
				c.setVisible(true);
				
			}
		});
		mnMantenedor.add(menuChofer);
		
		JMenuItem mntmUnidadTransporte = new JMenuItem("Unidad Transporte");
		mntmUnidadTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGestionarUnidadTransporte u = new frmGestionarUnidadTransporte();
				dspContenedor.add(u);
				u.setVisible(true);
			}
		});
		mnMantenedor.add(mntmUnidadTransporte);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	    dspContenedor = new JDesktopPane();
		contentPane.add(dspContenedor, BorderLayout.CENTER);
		dspContenedor.setLayout(null);
		
		
		
		
	}
}
