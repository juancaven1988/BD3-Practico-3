package ude.bdIII.practico;

public class Resultado {

	private int cedula;
	private String codigo;
	private int calificacion;
	
	public Resultado(int ced, String cod, int cal)
	{
		cedula = ced;
		codigo = cod;
		calificacion = cal;
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
