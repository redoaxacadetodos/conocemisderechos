package com.redoaxaca.java;

import java.util.List;

public class Row {

	int     idRegion;
	String  region;
	int     idMunicipio;
	String  municipio;
	int     idLocalidad;
	String  localidad;
	int		hombres;
	int		mujeres;
	int     total;
	
	List<Integer> categorias;
	
	

	@Override
	public String toString() {
		return "Row [idRegion=" + idRegion + ", region=" + region
				+ ", idMunicipio=" + idMunicipio + ", municipio=" + municipio
				+ ", idLocalidad=" + idLocalidad + ", localidad=" + localidad
				+ ", hombres=" + hombres + ", mujeres=" + mujeres + ", total="
				+ total + ", categorias=" + categorias + "]";
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getHombres() {
		return hombres;
	}

	public void setHombres(int hombres) {
		this.hombres = hombres;
	}

	public int getMujeres() {
		return mujeres;
	}

	public void setMujeres(int mujeres) {
		this.mujeres = mujeres;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Integer> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Integer> categorias) {
		this.categorias = categorias;
	}
	
	

}
