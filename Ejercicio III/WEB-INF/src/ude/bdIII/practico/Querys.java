package ude.bdIII.practico;

public class Querys {

	/* crea el texto de la consulta que obtiene un listado de todos */
	/* los exámenes registrados en la BD */
	public static String listarExamenes ()
	{
		return "Select * from examenes";
	}
	
	/* crea el texto de la consulta que inserta un resultado de examen */
	public static String insertarResultado () {
		
		return "Insert into resultados values(?,?,?)";
	}
	
	/* crea el texto de la consulta que obtiene todos los resultados */
	/* de los exámenes rendidos por un estudiante, dada su cédula */
	public static String listarResultados () {
		
		return "Select * from resultados where cedula = (?)";
	}
	
	
}
