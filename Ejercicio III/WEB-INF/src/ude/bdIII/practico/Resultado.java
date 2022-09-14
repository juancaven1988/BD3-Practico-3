package ude.bdIII.practico;

import java.io.Serializable;

public class Resultado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cedula;
	private String codigo;
	private int calificacion;
	
	public Resultado(int ced, String cod, int cal) 
	{
		cedula = ced;
		codigo = cod;
		calificacion = cal;
	}

	public Resultado() {
		
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public int getCedula() {
		return cedula;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getCalificacion() {
		return calificacion;
	}
	
	
	
}
