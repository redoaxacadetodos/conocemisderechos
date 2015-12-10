package com.redoaxaca.java

import java.text.ParseException
import mx.gob.redoaxaca.cednna.domino.CatOrigenDatos
import mx.gob.redoaxaca.cednna.domino.Dependencia
import mx.gob.redoaxaca.cednna.domino.Estado
import mx.gob.redoaxaca.cednna.domino.Periodo

import org.apache.poi.ss.usermodel.WorkbookFactory



class LeerExcell {
	def dataTablesService
	def sessionFactory

	def springSecurityService
	ArrayList<Row> renglones = new ArrayList<Row>();
	int claveDependencia ;
	int opcion =0;
	int numCategorias =0;
	def usuario = null

	def archivo
	String clave

	int total

	public LeerExcell(File fileName, Long actual,Estado estado, Dependencia dependencia, String path) throws ParseException {
		
		try {
			def sFileNameCv=path+"csvCV_"+actual+".csv"
			def contadorBuenos = 0
			def contadorMalos = 0
			def contador = 0
			def mensaje=""
			
			Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sFileNameCv), "UTF-8"));

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
			
			FileInputStream fileInputStream = new FileInputStream(fileName);
			
			org.apache.poi.ss.usermodel.Workbook workBook = WorkbookFactory.create(fileInputStream);
			org.apache.poi.ss.usermodel.Sheet hssfSheet = workBook.getSheetAt(0);

			int numeroHojas = workBook.getNumberOfSheets();
			
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
						clave = hssfSheet.getRow(i).getCell(0).getStringCellValue()
						def origenDato = CatOrigenDatos.findByClave(clave)
						dep = origenDato?.dependencia?.id
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

						for(int x=1;x<=(numCategorias*2); x++){
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
						def origenDato = CatOrigenDatos.findByClave(hssfSheet.getRow(i).getCell(2).getStringCellValue())
						dep = origenDato?.dependencia?.id
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

						for(int x=1;x<=(numCategorias*2); x++){
							writer.append(',');
							writer.append(new Double(hssfSheet.getRow(i).getCell(x+6).getNumericCellValue()).longValue().toString());
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
					
					for(i=5;i<=total; i++){
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
						def origenDato = CatOrigenDatos.findByClave(hssfSheet.getRow(i).getCell(4).getStringCellValue())
						dep = origenDato?.dependencia?.id
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
						

						for(int x=1;x<=(numCategorias*2); x++){
							writer.append(',');
							writer.append(new Double(hssfSheet.getRow(i).getCell(x+8).getNumericCellValue()).longValue().toString());
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

		} catch (Exception e) {
			total = 4;
			e.printStackTrace();
		}

	}

	def total(){
		return (total-4)
	}
	
	def getClave(){
		return clave
	}

}
