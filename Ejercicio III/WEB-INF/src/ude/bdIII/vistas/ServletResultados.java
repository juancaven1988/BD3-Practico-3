package ude.bdIII.vistas;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.util.StringUtils;

import ude.bdIII.practico.QueryExecuter;
import ude.bdIII.practico.Resultado;

public class ServletResultados extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String sena;
	private String url;
	
	public void init(ServletConfig config) throws ServletException
	{
		
		super.init(config);
		
		usuario = this.getInitParameter("usuario");
		sena = this.getInitParameter("sena");
		url = this.getInitParameter("url");
		
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("Index.jsp");
		rd.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		String cedula = req.getParameter("cedula");
		RequestDispatcher rd;
		List<Resultado> resultados = new ArrayList<Resultado>();
		HttpSession session = req.getSession();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("No existe la clase");
		} 
		
		if(cedula.length() != 8)
		{
			session.setAttribute("error", "La cedula debe ser de largo 8");
			rd = req.getRequestDispatcher("Error.jsp");
		}
		else {
			  
			
			{
				try {
					
					synchronized (session) {
					Connection con = DriverManager.getConnection(url, usuario, sena);
					resultados = QueryExecuter.listarResultados(con, Integer.parseInt(cedula));
					con.close();
					
					session.setAttribute("resultados", resultados);
					session.setAttribute("cedula", Integer.parseInt(cedula));
				}
									
				rd = req.getRequestDispatcher("ListaResultados.jsp");
				
			} catch (SQLException e) {
				
				session.setAttribute("error", e.getMessage() + e.getCause());
							
				rd = req.getRequestDispatcher("Error.jsp");
				rd.forward(req, resp);
				}
			}
			
			
		}
		
		rd.forward(req, resp);
	}
	
	
}
