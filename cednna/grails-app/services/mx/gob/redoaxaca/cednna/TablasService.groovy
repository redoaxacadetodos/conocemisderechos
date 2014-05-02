package mx.gob.redoaxaca.cednna

import mx.gob.redoaxaca.cednna.domino.CatOrigenDatos
import mx.gob.redoaxaca.cednna.domino.Variable

import groovy.sql.Sql

import org.springframework.transaction.annotation.Transactional


class TablasService {
    def dataSource
    def springSecurityService

    def serviceMethod() {

    }

    def getTablaVariables(){
    	def list = []
    	def variables = CatOrigenDatos.list()
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
                    orden = 'localidad '
                    break
                case '5':
                    orden = 'categoria '
                    break
                case '6':
                    orden = 'anio '
                    break
                case '7':
                    orden = 'poblaciontotal '
                    break
                case '8':
                    orden = 'mujeres '
                    break
                case '9':
                    orden = 'hombres '
                    break
            }
        }

        String sql = """
            select 
            cvv_id id, cvv_anio anio, cvv_clave clave, cvv_descripcion descripcion, 
            e.ent_descripcion estado, l.ctl_descripcion localidad, mun.mun_descripcion municipio, cvv_hombres hombres, 
            cvv_mujeres mujeres , cvv_poblacion_total poblaciontotal, r.crg_descripcion region, 
            cat.cct_descripcion categoria, p.descripcion periodo
            from cat_variable v 
            LEFT JOIN cat_entidad e ON (e.ent_id = v.cvv_estado) 
            LEFT JOIN cat_localidad l ON (v.cvv_localidad = l.ctl_id)
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
            OR UPPER(l.ctl_descripcion) LIKE UPPER ('%${params?.sSearch}%') 
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
                sql += " ORDER BY " +orden+ (params.sSortDir_0 != null ? params.sSortDir_0 : '' ) + ", anio desc, categoria "
            }
            sql += " LIMIT "+ (params.iDisplayLength != null ?params.iDisplayLength:'10') +" OFFSET " + (params.iDisplayStart!=null?params.iDisplayStart:'0')
        }

        def rol = springSecurityService.getPrincipal().getAuthorities()
        def metodo = ''
        def esString = rol instanceof String
        if(esString){
            if(rol.equals('ROLE_DEP')){
                metodo = 'editaRegistro'
            }else if(rol.equals('ROLE_LECTURA')){
                metodo = 'monitorRegistro'
            }    
        }else{
            rol.each {
                if(it.equals('ROLE_DEP')){
                    metodo = 'editaRegistro'
                }else if(it.equals('ROLE_LECTURA')){
                    metodo = 'monitorRegistro'
                } 
            }
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
                '4':it.localidad,
                '5':it.categoria,
                '6':anio,
                '7':it.poblaciontotal,
                '8':it.mujeres,
                '9':it.hombres,
                '10':"<a onclick='"+metodo+"("+it.id+"); ' class='uk-icon-button uk-icon-edit' href='#'> </a>"
            ]   
        }
        return list
    }

    @Transactional(readOnly = true)
    def executeQuery(String sql){
        def db = new Sql(dataSource)
        db.close()
        return db.rows(sql)
    }
}
