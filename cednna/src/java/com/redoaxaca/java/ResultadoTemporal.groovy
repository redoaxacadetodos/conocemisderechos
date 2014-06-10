package com.redoaxaca.java

import java.util.List;

class ResultadoTemporal {

	
	String   region
	int      idRegion
	String   municipio
	int      idMunicipio
	String   localidad
	int      idLocalidad
	Integer      indicador
	Double   resultadoIndicador
	int      anio
	
	
	
	
	public String toString(){
		
		return  region+" -  "+indicador+"--  "+anio
	}
	
	
}
