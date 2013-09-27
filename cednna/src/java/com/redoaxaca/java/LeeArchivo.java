package com.redoaxaca.java;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeeArchivo {



	ArrayList<Row> renglones = new ArrayList<Row>();
	int claveDependencia ;
	int opcion;
	int numCategorias;

	public LeeArchivo(File fileName) throws ParseException {

		try {
			System.out.println("ENTRO A LEER EXCCEL");
			
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			int numeroHojas = workBook.getNumberOfSheets();

			System.out.println("Numerode hojas del archivo de exceel "+numeroHojas );
			for (int i = 0; i < numeroHojas; i++) {

					XSSFSheet hssfSheet = workBook.getSheetAt(i);    
					Iterator rowIterator = hssfSheet.rowIterator();
					
					while (rowIterator.hasNext()) {
	
						XSSFRow hssfRow = (XSSFRow) rowIterator.next();
						Iterator iterator = hssfRow.cellIterator();
						Row tempRow = new Row();
	
						while (iterator.hasNext()) {
							
							XSSFCell hssfCell = (XSSFCell) iterator.next();
	
							
							if(hssfCell.getRowIndex() == 1 && hssfCell.getColumnIndex()==1){
								
								numCategorias=Integer.parseInt(hssfCell.getStringCellValue());
							}
							if(hssfCell.getRowIndex() == 2 && hssfCell.getColumnIndex()==1){
								opcion=Integer.parseInt(hssfCell.getStringCellValue());
								
							}
						
							if(hssfCell.getRowIndex() >= 4 && hssfCell.getColumnIndex() >= 0){
							
								switch (opcion) {
								case 1:
												switch (hssfCell.getColumnIndex()) {
														
												
														case 0:
															tempRow.setClave(hssfCell.getStringCellValue());
														
														case 1:
															tempRow.setDescripcion(hssfCell.getStringCellValue());
															break;
														
														case 2:
															tempRow.setMujeres(Integer.parseInt(hssfCell.getStringCellValue()));
														
														case 3:
															tempRow.setHombres(Integer.parseInt(hssfCell.getStringCellValue()));
															break;
														
														case 4:
															tempRow.setAnio(Integer.parseInt(hssfCell.getStringCellValue()));
														
														case 5:
															tempRow.setHombres(Integer.parseInt(hssfCell.getStringCellValue()));
														
															renglones.add(tempRow);
															break;
														
															
															
															
//														case 6:
//															tempRow.setDescripcion(hssfCell.getStringCellValue());
//															renglones.add(tempRow);
//															break;
											}
											break;
									
									
											
								case 2:
									
									
									
									break;
									
									
								case 3:
									
									
									
									
									break;
								
								default:
									break;
								}
										
											
										
								
											
							}
							
							
						}
					}
			}
		} catch (Exception e) {
			e.getMessage();
		}

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
