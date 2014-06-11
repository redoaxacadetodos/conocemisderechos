package mx.gob.redoaxaca.cednna

import mx.gob.redoaxaca.cednna.domino.CatOrigenDatos
import mx.gob.redoaxaca.cednna.domino.Variable

import groovy.sql.Sql

import org.springframework.transaction.annotation.Transactional

import grails.plugins.springsecurity.Secured


class TablasService {
    def dataSource
    def springSecurityService

    def serviceMethod() {

    }

    def getTablaVariables(params, cuenta){
    	if (cuenta){ 
            return CatOrigenDatos.list().size()
        }

        String orden='clave '
        if(params?.iSortCol_0){
            switch (params?.iSortCol_0) {
                case '0':
                    orden = 'clave '
                    break
                case '1':
                    orden = 'descripcion '
                    break
            }
        }


        def list = []
        def maximo = params.iDisplayLength!=null?params.iDisplayLength:10
        def inicio = params.iDisplayStart!=null?params.iDisplayStart:0
        
        def tipo = params.sSortDir_0 != null ? params.sSortDir_0 : 'asc'
        if(params?.iSortCol_0){
            switch (params?.iSortCol_0) {
                case '0':
                    orden = 'clave'
                    break
                case '1':
                    orden = 'descripcion'
                    break  
            }
        }

        String sql = """
            select cod_id id, cod_clave clave, cod_descripcion descripcion from cat_origen_datos 
            where upper(cod_clave) like upper('%${params?.sSearch}%')
            or upper(cod_descripcion) like upper('%${params?.sSearch}%')
        """
        if(!params?.iSortCol_0){
                sql+=" ORDER BY clave "
            }else{
                sql += " ORDER BY " +orden+ " " + (params.sSortDir_0 != null ? params.sSortDir_0 : '' ) + " "
            }
            sql += " LIMIT "+ (params.iDisplayLength != null ?params.iDisplayLength:'10') +" OFFSET " + (params.iDisplayStart!=null?params.iDisplayStart:'0')

    	def variables = executeQuery(sql)

    	variables.each{
    		list<<[
    			'0':"<a href='/cednna/catOrigenDatos/show/"+it.id+"'>"+it.clave+"</a>",
    			'1':it.descripcion
    		]	
    	}


    	return list
    }

    def getTablaDatosEstadisticos(params, cuenta){
        String orden='clave '
        if(params?.iSortCol_0){
            switch (params?.iSortCol_0) {
                case '0':
                    orden = 'clave '
                    break
                case '1':
                    orden = 'descripcion '
                    break
                case '2':
                    orden = 'region '
                    break
                case '3':
                    orden = 'municipio '
                    break                
                case '4':
                    orden = 'categoria '
                    break
                case '5':
                    orden = 'anio '
                    break
                case '6':
                    orden = 'poblaciontotal '
                    break
                case '7':
                    orden = 'mujeres '
                    break
                case '8':
                    orden = 'hombres '
                    break
            }
        }

        String sql = """
            select 
            cvv_id id, cvv_anio anio, cvv_clave clave, cvv_descripcion descripcion, 
            e.ent_descripcion estado,  mun.mun_descripcion municipio, cvv_hombres hombres, 
            cvv_mujeres mujeres , cvv_poblacion_total poblaciontotal, r.crg_descripcion region, 
            cat.cct_descripcion categoria, p.descripcion periodo
            from cat_variable v 
            LEFT JOIN cat_entidad e ON (e.ent_id = v.cvv_estado) 
            
            LEFT JOIN cat_municipio mun ON (v.cvv_municipio = mun.mun_id) 
            LEFT JOIN cat_region r ON (v.cvv_region = r.crg_id)
            LEFT JOIN cat_variable_categoria vc ON (vc.cvc_cvv_id = v.cvv_id) 
            LEFT JOIN cat_categoria cat ON (cat.cct_id = vc.cvc_cct_id) 
            LEFT JOIN periodo p ON (p.id = v.cvv_ped_id)
            """
        
        if(params?.sSearch!=null && params?.sSearch!=''){
            sql +=
            """
            WHERE cvv_anio::text LIKE ('%${params?.sSearch}%') 
            OR UPPER(cvv_clave) LIKE UPPER ('%${params?.sSearch}%') 
            OR UPPER(cvv_descripcion) LIKE UPPER ('%${params?.sSearch}%') 
            OR UPPER(e.ent_descripcion) LIKE UPPER ('%${params?.sSearch}%') 
            OR UPPER(mun.mun_descripcion) LIKE UPPER ('%${params?.sSearch}%')             
            OR UPPER(r.crg_descripcion) LIKE UPPER ('%${params?.sSearch}%') 
            OR UPPER(cat.cct_descripcion) LIKE UPPER ('%${params?.sSearch}%')                          
        """
        }
        
        if (cuenta){ 
            return (executeQuery(" select count(*) numero from ( "+ sql +" ) consulta" ))?.numero
        }else{
            if(!params?.iSortCol_0){
                sql+=" ORDER BY clave, anio desc, categoria "
            }else{
                sql += " ORDER BY " +orden+  " " +(params.sSortDir_0 != null ? params.sSortDir_0 : '' ) + ", anio desc, categoria "
            }
            sql += " LIMIT "+ (params.iDisplayLength != null ?params.iDisplayLength:'10') +" OFFSET " + (params.iDisplayStart!=null?params.iDisplayStart:'0')
        }


        boolean editar = false
        def rol = springSecurityService.getPrincipal().getAuthorities()
        def metodo = ''
        def esString = rol instanceof String
        if(esString){
            if(rol.equals('ROLE_DEP') || rol.equals('ROLE_ADMIN')){
                editar = true
            }    
        }else{
            rol.each {
                if(it.equals('ROLE_DEP')||it.equals('ROLE_ADMIN')){
                    editar = true
                }
            }
        }        

        if(editar){
            metodo = 'editaRegistro'
        }else{
            metodo = 'monitorRegistro'
        } 
        
        def list = []
        def variables = executeQuery(sql)
        variables.each{
            def anio = it.periodo
            
            if(anio==null){
                anio = it.anio
            }
            list<<[
                '0':it.clave,
                '1':it.descripcion,
                '2':it.region,
                '3':it.municipio,                
                '4':it.categoria,
                '5':anio,
                '6':it.poblaciontotal,
                '7':it.mujeres,
                '8':it.hombres,
                '9':"<a onclick='"+metodo+"("+it.id+"); ' class='uk-icon-button uk-icon-edit' href='#'> </a>"
            ]   
        }
        return list
    }

    @Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
    def getTablaBuscador(params, cuenta){
        String orden='modulo '
        if(params?.iSortCol_0){
            switch (params?.iSortCol_0) {
                case '0':
                    orden = 'modulo '
                    break
                case '1':
                    orden = 'seccion '
                    break
                case '2':
                    orden = 'indicador '
                    break
            }
        }

        String sql = """
            select i.idn_id id,  idn_urlexterna url,  e.id ejeid, e.descripcion modulo, d.descripcion seccion,  i.idn_nombre indicador from idn_indicador i
            left join division d on (i.division_id=d.id) left join eje e on (d.eje_id = e.id)
            WHERE idn_publico = true
        """

        if(params?.sSearch!=null && params?.sSearch!=''){
            sql +=
            """
            and (UPPER(idn_nombre) LIKE UPPER ('%${params?.sSearch}%') 
            OR UPPER(e.descripcion) LIKE UPPER ('%${params?.sSearch}%') 
            OR UPPER(d.descripcion) LIKE UPPER ('%${params?.sSearch}%') )
        """
        }

        if (cuenta){ 
            return (executeQuery(" select count(*) numero from ( "+ sql +" ) consulta" ))?.numero
        }else{
            if(!params?.iSortCol_0){
                sql+=" ORDER BY modulo, seccion "
            }else{
                sql += " ORDER BY " +orden+ (params.sSortDir_0 != null ? params.sSortDir_0 : '' ) + " "
            }
            sql += " LIMIT "+ (params.iDisplayLength != null ?params.iDisplayLength:'10') +" OFFSET " + (params.iDisplayStart!=null?params.iDisplayStart:'0')
        }
        println 'sql:'+sql

        def list = []
        def indicadores = executeQuery(sql)
        def url = ""
        indicadores.each{
            if(it.url){
                url = "<a href='"+it.url+"' class='uk-icon-button uk-icon-edit'> </a>"
            }else{
                url = "<a href='publico/mostrarIndicador/"+it.id+"?ejeInstance="+it.ejeid+"' class='uk-icon-button uk-icon-edit'> </a>"
            }

            list<<[
                '0':it.modulo,
                '1':it.seccion,
                '2':it.indicador,
                '3':url
            ]   
        }
        return list

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
