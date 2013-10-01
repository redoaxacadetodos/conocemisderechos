package com.redoaxaca.java;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;


import mx.gob.redoaxaca.cednna.domino.Categoria;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeeArchivo {



	ArrayList<Row> renglones = new ArrayList<Row>();
	int claveDependencia ;
	int opcion =0;
	int numCategorias =0;

	public LeeArchivo(File fileName) throws ParseException {

		try {
			
			
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			int numeroHojas = workBook.getNumberOfSheets();


					XSSFSheet hssfSheet = workBook.getSheetAt(0);    
					Iterator rowIterator = hssfSheet.rowIterator();
					int contadorFilas=0;
					
					numCategorias=new Double(hssfSheet.getRow(1).getCell(1).getNumericCellValue()).intValue();
					opcion=new Double(hssfSheet.getRow(2).getCell(1).getNumericCellValue()).intValue();

					
					while (rowIterator.hasNext()) {
						System.out.println("ENTRO A VES "+(++contadorFilas));
						XSSFRow hssfRow = (XSSFRow) rowIterator.next();
						Iterator iterator = hssfRow.cellIterator();
						Row tempRow = new Row();
					
						if(hssfRow.getRowNum()>4){
						
						switch (opcion) {
						case 1:
							
							tempRow.setClave(hssfRow.getCell(0).getStringCellValue());
							tempRow.setDescripcion(hssfRow.getCell(1).getStringCellValue());
							tempRow.setAnio(new Double(hssfRow.getCell(2).getNumericCellValue()).intValue());
							tempRow.setMujeres(new Double(hssfRow.getCell(3).getNumericCellValue()).intValue());
							tempRow.setHombres(new Double(hssfRow.getCell(4).getNumericCellValue()).intValue());
							
							tempRow.categorias = new ArrayList<Categoria>();
							
						
							for(int x=1;x<=(numCategorias*2); x++){
								
								Categoria c = new Categoria();
								c.setValorID(new Double(hssfRow.getCell(x+4).getNumericCellValue()).longValue());
							
								x++;
								tempRow.categorias.add(c);
							}
							
							
							
							break;
							
							
						case 2:
							
							tempRow.setIdRegion((new Double(hssfRow.getCell(0).getNumericCellValue()).intValue()));
							tempRow.setRegion(hssfRow.getCell(1).getStringCellValue());
							tempRow.setClave(hssfRow.getCell(2).getStringCellValue());
							tempRow.setDescripcion(hssfRow.getCell(3).getStringCellValue());
							tempRow.setAnio(new Double(hssfRow.getCell(4).getNumericCellValue()).intValue());
							tempRow.setMujeres(new Double(hssfRow.getCell(5).getNumericCellValue()).intValue());
							tempRow.setHombres(new Double(hssfRow.getCell(6).getNumericCellValue()).intValue());
							
							tempRow.categorias = new ArrayList<Categoria>();
							
							for(int x=1;x<=(numCategorias*2); x++){
								
								Categoria c = new Categoria();
								c.setValorID(new Double(hssfRow.getCell(x+6).getNumericCellValue()).longValue());
								x++;
								tempRow.categorias.add(c);
							}
							
							
							
							break;

						case 3:
							
							tempRow.setIdRegion((new Double(hssfRow.getCell(0).getNumericCellValue()).intValue()));
							tempRow.setRegion(hssfRow.getCell(1).getStringCellValue());
							tempRow.setIdMunicipio((new Double(hssfRow.getCell(2).getNumericCellValue()).intValue()));
							tempRow.setMunicipio(hssfRow.getCell(3).getStringCellValue());
							tempRow.setClave(hssfRow.getCell(4).getStringCellValue());
							tempRow.setDescripcion(hssfRow.getCell(5).getStringCellValue());
							tempRow.setAnio(new Double(hssfRow.getCell(6).getNumericCellValue()).intValue());
							tempRow.setMujeres(new Double(hssfRow.getCell(7).getNumericCellValue()).intValue());
							tempRow.setHombres(new Double(hssfRow.getCell(8).getNumericCellValue()).intValue());
							
							tempRow.categorias = new ArrayList<Categoria>();
							
							for(int x=1;x<=(numCategorias*2); x++){
								
								Categoria c = new Categoria();
								c.setValorID(new Double(hssfRow.getCell(x+8).getNumericCellValue()).longValue());
								x++;
								tempRow.categorias.add(c);
							}
							
								
							break;
						}
					
					
						
						
						
						
						renglones.add(tempRow);
					
											
						}						
				
				
					}//FIN DEL RECORRIDO POR FILAS
	
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

	}
	
	

	public ArrayList<Row> getRenglones() {
		return renglones;
	}



	public void setRenglones(ArrayList<Row> renglones) {
		this.renglones = renglones;
	}



	public int getOpcion() {
		return opcion;
	}



	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}



	public int getNumCategorias() {
		return numCategorias;
	}



	public void setNumCategorias(int numCategorias) {
		this.numCategorias = numCategorias;
	}



	public void setClaveDependencia(int claveDependencia) {
		this.claveDependencia = claveDependencia;
	}



	public int getClaveDependencia(){
		return claveDependencia;
	}

	public ArrayList<Row> getDatos() {
		return renglones;
	}


	public static void main(String[] vasc){



	}
}
