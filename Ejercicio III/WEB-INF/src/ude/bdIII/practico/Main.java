package ude.bdIII.practico;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import ude.bdIII.vistas.VentanaBedelia;

public class Main {

	public static void main(String[] args) {
		
		try {
		
			VentanaBedelia ventana = new VentanaBedelia();
			
		}catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Server", JOptionPane.ERROR_MESSAGE);
		}
	
		

	}

}
