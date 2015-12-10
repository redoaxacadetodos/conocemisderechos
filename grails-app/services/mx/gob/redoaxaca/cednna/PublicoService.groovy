package mx.gob.redoaxaca.cednna

import grails.plugins.springsecurity.Secured
import groovy.sql.Sql

import java.text.NumberFormat

import mx.gob.redoaxaca.cednna.domino.DVariable
import mx.gob.redoaxaca.cednna.domino.Variable

import org.springframework.transaction.annotation.Transactional

@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
class PublicoService {
	
	def dataSource
	
    def serviceMethod() {

    }
	
	@Transactional(readOnly = true)
	def getTablaDetalleDatosEstadisticos(categorias, params, cuenta){
		int tamCategorias = categorias.size()-1
		def dVariableInstance = DVariable.get(params.variable.toLong())
		def list = []
		def sqlWhere = ""
		def columnas = ""
		def columnasFiltro = ["descripcion", "upper(c.cct_descripcion)", "cvv_anio::text", "total", "hombres", "mujeres"]
		def orden = ["descripcion", "area", "c.cct_id", "cvv_anio", "total", "hombres", "mujeres"]
		
		if(params.areaGeografica.equals("1") || params.areaGeografica.equals("2")){
			orden.add(2,"mun.mun_descripcion")
			columnasFiltro.add(1,"area")
			columnasFiltro.add(2,"upper(mun.mun_descripcion)")
		}else{
			columnasFiltro.add(1,"upper(mun.mun_descripcion)")
		}
			
		columnas+="c.cct_descripcion categoria, cvv_descripcion descripcion, cvv_hombres hombres, cvv_mujeres mujeres,  (cvv_hombres + cvv_mujeres) total, cvv_anio anio, cvv_id id"
			
		String sql = "select "
		
		if(params.areaGeografica.equals("1")){
			sql+="e.ent_descripcion area, mun.mun_descripcion municipio, " + columnas + " from cat_variable v LEFT JOIN cat_entidad e ON (e.ent_id = v.cvv_estado) LEFT JOIN cat_municipio mun ON (v.cvv_municipio = mun.mun_id) "
		}else if(params.areaGeografica.equals("2")){
			sql+="r.crg_descripcion area, mun.mun_descripcion municipio, " + columnas + " from cat_variable v LEFT JOIN cat_region r ON (v.cvv_region = r.crg_id) LEFT JOIN cat_municipio mun ON (v.cvv_municipio = mun.mun_id)"
			sqlWhere+="and v.cvv_region = ${params.idCategoria}"
		}else if(params.areaGeografica.equals("3")){
			sql+="mun.mun_descripcion area, " + columnas + " from cat_variable v LEFT JOIN cat_municipio mun ON (v.cvv_municipio = mun.mun_id) "
			sqlWhere+="and v.cvv_municipio = ${params.idCategoria}"
		}
		
		sql+="left join cat_variable_categoria on (cvv_id = cvc_cvv_id) left join cat_categoria c on (cvc_cct_id = cct_id) where "
		
		if(tamCategorias>=0)
			sql+=" ("
		
		categorias.eachWithIndex{ categoria, i ->
			sql+= " c.cct_id = "+categoria.id 
			
			if(i!=tamCategorias)
				sql += " or "
			
		}
		
		if(tamCategorias>=0)
			sql+=" ) and"
		
		
		sql+=" cvv_clave = '${dVariableInstance.claveVar}' "
		
		if(sqlWhere.length()!=0){
			sql+= sqlWhere
		}
		
		columnasFiltro.eachWithIndex{ valor, indice ->
			def i = indice
			if(params["sSearch_"+i]!=null && params["sSearch_"+i]!=''){
				sqlWhere = sqlWhere.equals("WHERE")?sqlWhere:(sqlWhere + " AND")
				sqlWhere += " " + valor + " LIKE UPPER ('%"+params["sSearch_"+i]+"%')"
			}
		}
		
		sql+= sqlWhere
		
		if (cuenta){
			return (executeQuery(" select count(*) numero from ( "+ sql +" ) consulta" ))?.numero
		}else{
			if(!params?.iSortCol_0 || params?.iSortCol_0?.toInteger()==0){
				sql+=" ORDER BY " + orden[1] + ", c.cct_id asc"
			}else{
				sql += " ORDER BY " + orden[params?.iSortCol_0?.toInteger()]+ " " + (params.sSortDir_0 != null ? params.sSortDir_0 : '' ) + " "
			}
			if(params.paginado!=false)
				sql += " LIMIT "+ (params.iDisplayLength != null ?params.iDisplayLength:'10') +" OFFSET " + (params.iDisplayStart!=null?params.iDisplayStart:'0')
		}
		
		def variableList = executeQuery(sql)
		
		if(params.areaGeografica.equals("1") || params.areaGeografica.equals("2")){
			variableList.each{
				list<<[
					"0":it.descripcion,
					"1":it.area,
					"2":it.municipio!=null?it.municipio:'-',
					"3":it.categoria!=null?it.categoria:'-',
					"4":it.anio,
					"5":it.total,
					"6":it.hombres,
					"7":it.mujeres
				]
			}
		}else{
			variableList.each{
				list<<[
					"0":it.descripcion,
					"1":it.area,
					"2":it.categoria!=null?it.categoria:'-',
					"3":it.anio,
					"4":it.total,
					"5":it.hombres,
					"6":it.mujeres
				]
			}
		}
		
		return list
	}

    def getTablaIndicador(resultadosIndicador, tipo){
    	def list = []
    	if(resultadosIndicador!=null){
	    	resultadosIndicador.each{ lista ->
				def rowAux = []
				
				if(tipo==1)
					rowAux.add("Oaxaca")
				
				if(tipo==2 || tipo==3 || tipo==4)
					rowAux.add(lista.region)
				
				if(tipo==3 || tipo==4)
					rowAux.add(lista.municipio)
				
				lista.resultados.each{
					rowAux.add(it.indicador!=null?it.indicador.round(2):'-')
				}
				list.add(rowAux)
	    	}
    	}
    	return list
    }
	
	def descargarSerieHistorica(resultadosIndicador, tipo){
		def list = []
		if(resultadosIndicador!=null){
			resultadosIndicador.each{ lista ->
				def rowAux = [:]
				def col = 0
				
				if(tipo==1){
					rowAux.put(col.toString(),"Oaxaca")
					col++
				}
				
				if(tipo==2 || tipo==3 || tipo==4){
					rowAux.put(col.toString(),lista.region)
					col++
				}
				
				if(tipo==3 || tipo==4){
					rowAux.put(col.toString(),lista.municipio)
					col++
				}
				
				lista.resultados.each{
					if(it.indicador!=null)
						rowAux.put(col.toString(),it.indicador.round(2))
					else
						rowAux.put(col.toString(),"-")
					col++
				}
				list.add(rowAux)
			}
		}
		return list
	}

    def getTablaDatosCalculo(datosCalculo, tamVariables, tipo, variables, anios, esCiclo){
    	def list = []

    	if(datosCalculo.size()>0){
			ArrayList variablesList = datosCalculo.sort{it.letra}.descripcion as Set
			
			for(int i=0; i<tamVariables; i++){
				ArrayList datos = datosCalculo.findAll{it.descripcion == variablesList[i]}
				
				for(int j=0; j<datos.valores[0].size(); j++){
					def row = []
					row.add( variablesList[i])
					if(tipo==1){
						row.add('Oaxaca')
					}else if(tipo==2){
						row.add(datos.valores[0].region[j])
					}else{
						row.add(datos.valores[0].municipio[j])
					}
					
					anios.eachWithIndex(){ anio, cont ->
						datos
						if(!esCiclo){
							if(datos.mostrarCiclo[cont]!=null && datos.mostrarCiclo[cont]==true){
								row.add(anio.anio.toString() + "-" + (anio.anio+1).toString())
							}else{
								row.add(anio.anio.toString())
							}
						}
	    				row.add(addCommas(datos.valores[cont].indicador[j]))
						def poblacion = datos.valores[cont].poblacion[j]
						if(poblacion==1){
							row.add('-')
							row.add(addCommas(datos.valores[cont].hombres[j]))
						}else if(poblacion==2){
							row.add(addCommas(datos.valores[cont].mujeres[j]))
							row.add('-')
						}else if(poblacion==3){
							row.add('-')
							row.add('-')
						}else{
							row.add(addCommas(datos.valores[cont].mujeres[j]))
							row.add(addCommas(datos.valores[cont].hombres[j]))
						}
					}
					
					if(tipo==1){
						
						row.add("<a onclick=\"actualizarDetalle("+ datos.idDVariable[0] + ",0,"+ tipo + ")\" title='Ver detalle' class='uk-icon-button uk-icon-search' href='#divDetalleDatosCalculo'></a>")
					}else if(tipo==2){
					
						row.add("<a onclick=\"actualizarDetalle("+ datos.idDVariable[0] + "," + datos.valores[0].idRegion[j] + ","+ tipo +")\" title='Ver detalle' class='uk-icon-button uk-icon-search' href='#divDetalleDatosCalculo'></a>")
					}else{
						row.add("<a onclick=\"actualizarDetalle("+ datos.idDVariable[0] + "," + datos.valores[0].idMunicipio[j] + ","+ tipo +")\" title='Ver detalle' class='uk-icon-button uk-icon-search' href='#divDetalleDatosCalculo'></a>")
					}
					
					list.add(row)
				}
			}			
		}
    	return list
    }
	
	def getGraficaDatosCalculo(datosCalculo, tamVariables, tipo, variables, anios, esCiclo){
		def resultado = [:]
		def list = []
		def titulos = []

		if(datosCalculo.size()>0){
			ArrayList variablesList = datosCalculo.sort{it.letra}.descripcion as Set
			
			for(int i=0; i<tamVariables; i++){
				ArrayList datos = datosCalculo.findAll{it.descripcion == variablesList[i]}
				
				for(int j=0; j<datos.valores[0].size(); j++){
					def row = []
					titulos.add( variablesList[i])
					
					anios.eachWithIndex(){ anio, cont ->
						row.add(datos.valores[cont].indicador[j])
					}
					list.add(row)
				}
			}
		}
		resultado.datos = list
		resultado.titulos = titulos
		return resultado
	}
	
	def descargarDatosCalculo(datosCalculo, tamVariables, tipo, variables, anios){
		def list = []

		if(datosCalculo.size()>0){
			ArrayList variablesList = datosCalculo.sort{it.letra}.descripcion as Set
			
			for(int i=0; i<tamVariables; i++){
				ArrayList datos = datosCalculo.findAll{it.descripcion == variablesList[i]}
				for(int j=0; j<datos.valores[0].size(); j++){
					def row = [:]
					def col = 0
					
					row.put(col.toString(), variablesList[i])
					col++
					if(tipo==1){
						row.put(col.toString(), 'Oaxaca')
						col++
					}else if(tipo==2){
						row.put(col.toString(), datos.valores[0].region[j])
						col++
					}else{
						row.put(col.toString(), datos.valores[0].municipio[j])
						col++
					}
					
					anios.eachWithIndex(){ anio, cont ->
						row.put(col.toString(), addCommas(datos.valores[cont].indicador[j]))
						col++
						def poblacion = datos.valores[cont].poblacion[j]
						if(poblacion==1){
							row.put(col.toString(), '-')
							col++
							row.put(col.toString(), addCommas(datos.valores[cont].hombres[j]))
							col++
						}else if(poblacion==2){
							row.put(col.toString(), addCommas(datos.valores[cont].mujeres[j]))
							col++
							row.put(col.toString(), '-')
							col++
						}else if(poblacion==3){
							row.put(col.toString(), '-')
							col++
							row.put(col.toString(), '-')
							col++
						}else{
							row.put(col.toString(), addCommas(datos.valores[cont].mujeres[j]))
							col++
							row.put(col.toString(), addCommas(datos.valores[cont].hombres[j]))
							col++
						}
					}
					list.add(row)
				}
			}
		}
		return list
	}

    def addCommas(def nStr) {
    	if(!nStr instanceof Integer){
    		nStr.toDouble()
    	}
    	nStr = nStr.toString()
    	def x = nStr.split('\\.')
    	def num = x[0].toInteger()
    	def decimales = x.length > 1 ? '.' + x[1] : ''
    	def numeroConFormato = NumberFormat.getNumberInstance(Locale.US).format(num)
    	return numeroConFormato + decimales
    }
	
	@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
	@Transactional(readOnly = true)
	def executeQuery(String sql){
		def db = new Sql(dataSource)
		def rows = db.rows(sql)
		db.close()
		return rows
	}
}
