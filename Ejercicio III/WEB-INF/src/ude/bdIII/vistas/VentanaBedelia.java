package ude.bdIII.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import ude.bdIII.practico.Examen;
import ude.bdIII.practico.QueryExecuter;
import ude.bdIII.practico.Resultado;

public class VentanaBedelia {

	private JFrame frmBedelia;
	private JTextField txtfCedula;
	private JTextField txtfCalificacion;
	private Connection connection;
	private List<Examen> examenes;
	


	/**
	 * Create the application.
	 */
	public VentanaBedelia() {
		try {
			initialize();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Bedelia", JOptionPane.ERROR_MESSAGE);
		}
		frmBedelia.setVisible(true);
	
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		
		
		InciarConexion();
		ObtenerExamenes();
		connection.close();
		
		
		frmBedelia = new JFrame();
		frmBedelia.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				try {
					System.out.print("Cerrada la ventana");
					connection.close();
					JOptionPane.showMessageDialog(null, "conexion cerrada", "Bedelia", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Bedelia", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		frmBedelia.setTitle("Bedelia");
		frmBedelia.getContentPane().setBackground(UIManager.getColor("textHighlight"));
		frmBedelia.setForeground(UIManager.getColor("scrollbar"));
		frmBedelia.getContentPane().setForeground(UIManager.getColor("textHighlight"));
		frmBedelia.setBounds(100, 100, 588, 276);
		//frmBedelia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBedelia.getContentPane().setLayout(null);
		frmBedelia.setLocationRelativeTo(null);
		
		JLabel lblSeleccion = new JLabel("Por favor seleccione examen: ");
		lblSeleccion.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSeleccion.setBounds(27, 21, 351, 36);
		frmBedelia.getContentPane().add(lblSeleccion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(22, 68, 337, 146);
		frmBedelia.getContentPane().add(scrollPane);
		
		JList<Examen> listExamenes = new JList<Examen>();
		
		
		
		listExamenes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listExamenes);
		DefaultListModel<Examen> model = new DefaultListModel<>();

		
		for(Examen e : examenes)
		{
			model.addElement(e);
		}
		listExamenes.setModel(model);
		
		
		
		
		txtfCedula = new JTextField();
		txtfCedula.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtfCedula.setBounds(476, 67, 86, 20);
		frmBedelia.getContentPane().add(txtfCedula);
		txtfCedula.setColumns(10);
		
		txtfCalificacion = new JTextField();
		txtfCalificacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtfCalificacion.setBounds(476, 113, 86, 20);
		frmBedelia.getContentPane().add(txtfCalificacion);
		txtfCalificacion.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Monospaced", Font.BOLD, 13));
		lblCedula.setBounds(414, 68, 57, 17);
		frmBedelia.getContentPane().add(lblCedula);
		
		JLabel lblCalificacion = new JLabel("Calificacion");
		lblCalificacion.setFont(new Font("Monospaced", Font.BOLD, 13));
		lblCalificacion.setBounds(369, 116, 102, 17);
		frmBedelia.getContentPane().add(lblCalificacion);
		
		JButton btnIngresar = new JButton("Ingresar");
		
		btnIngresar.setBackground(Color.ORANGE);
		btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIngresar.setBounds(416, 174, 89, 23);
		frmBedelia.getContentPane().add(btnIngresar);
		
		
		
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnIngresar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				
				
				
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
				btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				
				int cedula = Integer.parseInt(txtfCedula.getText());
				int calificacion = Integer.parseInt(txtfCalificacion.getText());
				String codigo = listExamenes.getSelectedValue().getCodigo();
				Resultado resultado = new Resultado(cedula,codigo,calificacion);
				
				try {
					InciarConexion();
					QueryExecuter.ingresarResultado(connection, resultado);
					connection.close();
					JOptionPane.showMessageDialog(null, "Datos Prosesados", "Bedelia", JOptionPane.INFORMATION_MESSAGE);
					txtfCalificacion.setText("");
					txtfCedula.setText("");
					
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Bedelia", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	private void ObtenerExamenes() throws SQLException {
		
		if(connection != null)
		{
			examenes = QueryExecuter.listarExamenes(connection);
		}
		
	}

	private void InciarConexion()
	{

		Properties prop = new Properties();
		String properties = "config/data.properties";
		
		try {
			
			prop.load(new FileInputStream(properties));
			
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Error hallando la ruta", "Bedelia", JOptionPane.ERROR_MESSAGE);
			
		}
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String sena = prop.getProperty("sena");
		
		
		
		try {
			
			connection = DriverManager.getConnection(url, user, sena);
			
						
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error de conexion", "Bedelia", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
