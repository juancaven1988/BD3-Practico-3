package ude.bdIII.practico;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		
		
		Properties prop = new Properties();
		String properties = "config/data.properties";
		
		try {
			
			prop.load(new FileInputStream(properties));
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String sena = prop.getProperty("sena");
		String insertData = prop.getProperty("insertData");
		
		
		try {
			
			Connection con = DriverManager.getConnection(url, user, sena);
			
			//CREA LA BASE DE DATOS SI NO EXISTE
			QueryExecuter.Create(con);
			QueryExecuter.MoveToDB(con);
			
			System.out.println("Se creo la DB");
			
			
			//CREAR TABLA EXAMENES
			QueryExecuter.CreateTables(con);
			
			System.out.println("Se crearon las Tablas");
			
			if(insertData.equals("true"))
			{
				QueryExecuter.InsertDefaultData(con);
				prop.setProperty("insertData", "false");
				prop.store(new FileWriter(properties),null);
				System.out.println("Se inserto la info");
			}
			
			con.close();
			
		} catch (Exception e) {
			
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
		}
		

	}

}
