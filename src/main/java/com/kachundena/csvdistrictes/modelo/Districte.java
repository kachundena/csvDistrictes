package com.kachundena.csvdistrictes.modelo;

public class Districte {

	private int linea_;
	private int codigo_;
	private String nombre_;

	/**
	 * 
	 * @param linea
	 * @param codigo
	 * @param nombre
	 */
	public Districte(int linea, int codigo, String nombre) {
            this.linea_ = linea;
			this.codigo_ = codigo;
			this.nombre_ = nombre;
		throw new UnsupportedOperationException();
	}

        public Districte() {
	}
        
	public int getLinea() {
            return this.linea_;
	}

	/**
	 * 
	 * @param linea
	 */
	public void setLinea(int linea) {
            this.linea_ = linea;
        }

	public int getCodigo() {
            return this.codigo_;
	}

	/**
	 * 
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
            this.codigo_ = codigo;
        }

        
	public String getNombre() {
            return this.nombre_;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
            this.nombre_ = nombre;
	}


}
