package com.redoaxaca.java

import groovy.sql.Sql
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import mx.gob.redoaxaca.cednna.domino.Categoria;
import mx.gob.redoaxaca.cednna.domino.Dependencia
import mx.gob.redoaxaca.cednna.domino.Estado
import mx.gob.redoaxaca.cednna.domino.Periodo

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.redoaxaca.java.ArchivoDescarga;
import com.redoaxaca.java.LeeArchivo
import com.redoaxaca.java.LeerExcell
import com.redoaxaca.java.ResultCategorias;
import com.redoaxaca.java.Row
import com.redoaxaca.java.TotalVariable
import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import mx.gob.redoaxaca.cednna.seguridad.Usuario;

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile


import grails.plugins.springsecurity.Secured
import groovy.sql.Sql



class LeerExcell {
	def dataTablesService
	def sessionFactory

	def springSecurityService
	ArrayList<Row> renglones = new ArrayList<Row>();
	int claveDependencia ;
	int opcion =0;
	int numCategorias =0;
	def usuario = null
	//springSecurityService.currentUser

	def archivo

	int total

	public LeerExcell(File fileName, Long actual,Estado estado, Dependencia dependencia, String path) throws ParseException {

		try {
			def sFileNameCv=path+"csvCV_"+actual+".csv"
//			def sFileNameCt=path+"csvCT_"+actual+".csv"
			def contadorBuenos = 0
			def contadorMalos = 0
			def contador = 0
			def mensaje=""

			//FileWriter writer = new FileWriter(sFileNameCv);

			Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sFileNameCv), "UTF-8"));

//			writer.append("cvv_anio");
//			writer.append(',');
//			writer.append("cvv_clave");
//			writer.append(',');
//			writer.append("cvv_descripcion");
//			writer.append(',');
//			writer.append("cvv_estado");
//			writer.append(',');
//			writer.append("cvv_hombres");
//			writer.append(',');
//			writer.append("cvv_localidad");
//			writer.append(',');
//			writer.append("cvv_mujeres");
//			writer.append(',');
//			writer.append("cvv_municipio");
//			writer.append(',');
//			writer.append("cvv_poblacion_total");
//			writer.append(',');
//			writer.append("cvv_region");
//			writer.append(',');
//			writer.append("cvv_dependencia");
//			writer.append(',');
//			writer.append("cvv_ped_id");
		
			writer.append("id");
			writer.append(',');
			writer.append("anio");
			writer.append(',');
			writer.append("clave");
			writer.append(',');
			writer.append("descripcion");
			writer.append(',');
			writer.append("estado");
			writer.append(',');
			writer.append("hombres");
			writer.append(',');
			writer.append("localidad");
			writer.append(',');
			writer.append("mujeres");
			writer.append(',');
			writer.append("municipio");
			writer.append(',');
			writer.append("poblacion_total");
			writer.append(',');
			writer.append("region");
			writer.append(',');
			writer.append("dependencia");
			writer.append(',');
			writer.append("periodo");
			writer.append(',');
			writer.append("categoria1");
			writer.append(',');
			writer.append("categoria2");
			writer.append(',');
			writer.append("categoria3");
			writer.append(',');
			writer.append("categoria4");
			writer.append(',');
			writer.append("categoria5");
			writer.append(',');
			writer.append("categoria6");
			writer.append(',');
			writer.append("categoria7");
			writer.append(',');
			writer.append("categoria8");
			writer.append(',');
			writer.append("categoria9");
			writer.append(',');
			writer.append("categoria10");
			

			writer.append('\n');

			//  FileWriter writerCT = new FileWriter(sFileNameCt);
//			Writer writerCT = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sFileNameCt), "UTF-8"));
//
//			writerCT.append("cvc_cvv_id");
//			writerCT.append(',');
//			writerCT.append("cvc_cct_id");
//			writerCT.append('\n');

			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			int numeroHojas = workBook.getNumberOfSheets();


			XSSFSheet hssfSheet = workBook.getSheetAt(0);
			Iterator rowIterator = hssfSheet.rowIterator();
			int contadorFilas=0;

			numCategorias=new Double(hssfSheet.getRow(1).getCell(1).getNumericCellValue()).intValue();
			opcion=new Double(hssfSheet.getRow(2).getCell(1).getNumericCellValue()).intValue();

			Integer dep =dependencia?.id

			Integer municipio=null
			Integer localidad=null
			Integer region=null
			int i= 5
			total= hssfSheet.getLastRowNum();
			switch (opcion) {
				case 1:
					for(i=5;i<=total; i++){
						boolean periodo = false
//						actual++
						
						writer.append(actual.toString());
						writer.append(',');

						try{
							writer.append(hssfSheet.getRow(i).getCell(2).getNumericCellValue().intValue().toString());
						}catch(Exception e){
							String anio = hssfSheet.getRow(i).getCell(2).getStringCellValue().substring(0,4)
							writer.append(anio);
							periodo = true
						}
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(0).getStringCellValue());
						writer.append(',');
						writer.append('"'+hssfSheet.getRow(i).getCell(1).getStringCellValue()+'"');
						writer.append(',');
						writer.append(estado?.id?.toString());
						writer.append(',');
						writer.append('"'+hssfSheet.getRow(i).getCell(4).getNumericCellValue().round(0).intValue().toString()+'"');
						writer.append(',');
						writer.append(localidad?.toString());
						writer.append(',');
						writer.append('"'+hssfSheet.getRow(i).getCell(3).getNumericCellValue().round(0).intValue().toString()+'"');
						writer.append(',');
						writer.append(municipio?.toString());
						writer.append(',');
						def suma=(new Double(hssfSheet.getRow(i).getCell(3).getNumericCellValue()).round(0).intValue()+new Double(hssfSheet.getRow(i).getCell(4).getNumericCellValue().round(0).intValue()).intValue());
						writer.append(suma.toString());
						writer.append(',');
						writer.append(region?.toString());
						writer.append(',');
						writer.append(dep?.toString());
						writer.append(',');
						if(periodo){
							Periodo p = Periodo.findByDescripcion(hssfSheet.getRow(i).getCell(2).getStringCellValue())
							writer.append(p.id.toString());
						}else{
							writer.append(null);
						}
						
//						writer.append('\n');

						for(int x=1;x<=(numCategorias*2); x++){
//							writerCT.append(actual.toString());
							writer.append(',');
							writer.append(new Double(hssfSheet.getRow(i).getCell(x+4).getNumericCellValue()).longValue().toString());
							
							x++;
						}
						
						for(int j=numCategorias;j<10;j++){
							writer.append(',');
							writer.append(null);
						}
						
						writer.append('\n');
					}
					break;

				case 2:
					for(i=5;i<=total; i++){
						boolean periodo = false
//						actual++
						
						writer.append(actual.toString());
						writer.append(',');

						try{
							writer.append(hssfSheet.getRow(i).getCell(4).getNumericCellValue().intValue().toString());//ANIO
						}catch(Exception e){
							String anio = hssfSheet.getRow(i).getCell(4).getStringCellValue().substring(0,4)
							writer.append(anio);
							periodo = true
						}
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(2).getStringCellValue());//CLAVE
						writer.append(',');
						writer.append('"'+hssfSheet.getRow(i).getCell(3).getStringCellValue()+'"');//DESCRIPCION
						writer.append(',');
						writer.append(estado?.id?.toString());//ESTADO
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(6).getNumericCellValue().round(0).intValue().toString());//HOMBRES
						writer.append(',');
						writer.append(localidad?.toString());//LOCALIDAD
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(5).getNumericCellValue().round(0).intValue().toString());//MUJERES
						writer.append(',');
						writer.append(municipio?.toString());//MUNICIPIO
						writer.append(',');
						
						def suma=(new Double(hssfSheet.getRow(i).getCell(6).getNumericCellValue().round(0)).intValue()+new Double(hssfSheet.getRow(i).getCell(5).getNumericCellValue().round(0)).intValue());
						writer.append(suma.toString());
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(0).getNumericCellValue().intValue().toString());//REGION
						writer.append(',');
						writer.append(dep?.toString());//DEPENDENCIA
						writer.append(',');
						if(periodo){
							Periodo p = Periodo.findByDescripcion(hssfSheet.getRow(i).getCell(4).getStringCellValue())
							writer.append(p.id.toString());
						}else{
							writer.append(null);
						}
						
//						writer.append('\n');

						for(int x=1;x<=(numCategorias*2); x++){
//							writerCT.append(actual.toString());
							writer.append(',');
							writer.append(new Double(hssfSheet.getRow(i).getCell(x+6).getNumericCellValue()).longValue().toString());
//							writer.append('\n');
							x++;
						}
						for(int j=numCategorias;j<10;j++){
							writer.append(',');
							writer.append(null);
						}
						
						writer.append('\n');
					}
					break;
					
				case 3:
					println '---------municipios'
					for(i=5;i<=total; i++){
//						actual++
						boolean periodo = false
						
						writer.append(actual.toString());
						writer.append(',');
						
						try{
							writer.append(hssfSheet.getRow(i).getCell(6).getNumericCellValue().intValue().toString());//ANIO
						}catch(Exception e){
							String anio = hssfSheet.getRow(i).getCell(6).getStringCellValue().substring(0,4)
							writer.append(anio);
							periodo = true
						}
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(4).getStringCellValue());//CLAVE
						writer.append(',');
						writer.append('"'+hssfSheet.getRow(i).getCell(5).getStringCellValue()+'"');//DESCRIPCION
						writer.append(',');
						writer.append(estado.id.toString());//ESTADO
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(8).getNumericCellValue().round(0).intValue().toString());//HOMBRES
						writer.append(',');
						writer.append(localidad.toString());//LOCALIDAD
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(7).getNumericCellValue().round(0).intValue().toString());//MUJERES
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(2).getNumericCellValue().intValue().toString());//MUNICIPIO
						writer.append(',');
						
						def suma=(new Double(hssfSheet.getRow(i).getCell(8).getNumericCellValue().round(0)).intValue()+new Double(hssfSheet.getRow(i).getCell(7).getNumericCellValue().round(0)).intValue());
						writer.append(suma.toString());
						writer.append(',');
						writer.append(hssfSheet.getRow(i).getCell(0).getNumericCellValue().intValue().toString());//REGION
						writer.append(',');
						writer.append(dep.toString());//DEPENDENCIA
						writer.append(',');
						if(periodo){
							Periodo p = Periodo.findByDescripcion(hssfSheet.getRow(i).getCell(6).getStringCellValue())
							writer.append(p.id.toString());
						}else{
							writer.append(null);
						}
//						writer.append('\n');
						
						

						for(int x=1;x<=(numCategorias*2); x++){
//							writerCT.append(actual.toString());
							writer.append(',');
							writer.append(new Double(hssfSheet.getRow(i).getCell(x+8).getNumericCellValue()).longValue().toString());
//							writer.append('\n');
							x++;
						}
						for(int j=numCategorias;j<10;j++){
							writer.append(',');
							writer.append(null);
						}
						
						writer.append('\n');
					}
					break;
			}

			writer.flush();
			writer.close();
//			writerCT.flush();
//			writerCT.close();

		} catch (Exception e) {
			total = 4;
		}

	}

	def total(){

		return (total-4)

	}

}
