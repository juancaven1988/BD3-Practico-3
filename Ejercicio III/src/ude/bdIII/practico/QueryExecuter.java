package ude.bdIII.practico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryExecuter {

	/* devuelve un listado de todos los exámenes registrados en la BD */
	/* de cada examen se tiene código, asignatura y período */
	public static List<Examen> listarExamenes (Connection con) throws SQLException
	{
		ArrayList<Examen> examenes = new ArrayList<Examen>();
		String codigo;
		String materia;
		String periodo;
		
		String query = Querys.listarExamenes();
		
		Statement stm  = con.createStatement();
		
		ResultSet rs = stm.executeQuery(query);
		
		while(rs.next())
		{
			codigo = (rs.getString("codigo"));
			materia = (rs.getString("materia"));
			periodo = (rs.getString("periodo"));
			
			examenes.add(new Examen(codigo,materia,periodo));
		}
		
		rs.close();
		
		stm.close();
		
		return examenes;
	}
	
	/* ingresa el resultado de un examen en la BD, los datos del */
	/* resultado vienen almacenados en el objeto resu */
	public static void ingresarResultado (Connection con, Resultado resu) throws SQLException 
	{
		
		String query = Querys.insertarResultado();
		
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setInt(1, resu.getCedula());
		pstm.setString(2, resu.getCodigo());
		pstm.setInt(3, resu.getCalificacion());
		
		pstm.executeUpdate();
		
		pstm.close();		
		
	}
		
	
	
}
