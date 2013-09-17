package com.redoaxaca.java

class ResultadoIndicador {
	String   region
	Integer  idRegion 
	String   municipio
	Integer  idMunicipio
	String   localidad
	Integer  idLocalidad
	List <Resultado> resultados 
	public ResultadoIndicador(){
		region = null
		municipio = null
		localidad =  null
		idRegion= null
		idMunicipio= null
		idLocalidad= null
	}
}
