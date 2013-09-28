package com.redoaxaca.java;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;


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

			
			for (int i = 0; i < numeroHojas; i++) {

					XSSFSheet hssfSheet = workBook.getSheetAt(i);    
					Iterator rowIterator = hssfSheet.rowIterator();
					int contadorFilas=0;
					while (rowIterator.hasNext()) {
						System.out.println("ENTRO A VES "+(++contadorFilas));
						XSSFRow hssfRow = (XSSFRow) rowIterator.next();
						Iterator iterator = hssfRow.cellIterator();
						Row tempRow = new Row();
	
						while (iterator.hasNext()) {
							
							XSSFCell hssfCell = (XSSFCell) iterator.next();
	
							
							if(hssfCell.getRowIndex() == 1 && hssfCell.getColumnIndex()==1){
									numCategorias=new Double(hssfCell.getNumericCellValue()).intValue();
							}else if(hssfCell.getRowIndex() == 2 && hssfCell.getColumnIndex()==1){
							
										opcion=new Double(hssfCell.getNumericCellValue()).intValue();
								
										 }else  if(hssfCell.getRowIndex() > 4 && hssfCell.getColumnIndex() >= 0){
											
													switch (opcion) {
													case 1:
																	switch (hssfCell.getColumnIndex()) {
																			
																	
																			case 0:
																				tempRow.setClave(hssfCell.getStringCellValue());
																			
																			case 1:
																				tempRow.setDescripcion(hssfCell.getStringCellValue());
																				break;
																			
																			
																			
																			case 2:
																				tempRow.setAnio(new Double(hssfCell.getNumericCellValue()).intValue());
																				break;
																		
																			case 3:
																				tempRow.setMujeres(new Double(hssfCell.getNumericCellValue()).intValue());
																			
																			case 4:
																				tempRow.setHombres(new Double(hssfCell.getNumericCellValue()).intValue());
																				renglones.add(tempRow);
																				break;
																	
																	}
													break;
														
														
																
													case 2:
														
														
																			switch (hssfCell.getColumnIndex()) {
																			
																			
																			case 0:
																				tempRow.setClave(hssfCell.getStringCellValue());
																			
																			case 1:
																				tempRow.setDescripcion(hssfCell.getStringCellValue());
																				break;
																			
																			
																			
																			case 2:
																				tempRow.setAnio(new Double(hssfCell.getNumericCellValue()).intValue());
																				break;
																		
																			case 3:
																				tempRow.setMujeres(new Double(hssfCell.getNumericCellValue()).intValue());
																			
																			case 4:
																				tempRow.setHombres(new Double(hssfCell.getNumericCellValue()).intValue());
																				
																				break;
																	
																	}
														
														
														break;
														
														
													case 3:
																	
														
																		switch (hssfCell.getColumnIndex()) {
																		
																		
																		case 0:
																			tempRow.setClave(hssfCell.getStringCellValue());
																		
																		case 1:
																			tempRow.setDescripcion(hssfCell.getStringCellValue());
																			break;
																		
																		
																		
																		case 2:
																			tempRow.setAnio(new Double(hssfCell.getNumericCellValue()).intValue());
																			break;
																	
																		case 3:
																			tempRow.setMujeres(new Double(hssfCell.getNumericCellValue()).intValue());
																		
																		case 4:
																			tempRow.setHombres(new Double(hssfCell.getNumericCellValue()).intValue());
																			renglones.add(tempRow);
																			break;
																
																}
														
														
														
														break;
													
													
													}//FIN DEL SWICH PRINCIPAL DE VSEPARACION DE ARCHIVOS
													
													
													
										 	}//CIERRE DE CICLO IF DE VALIDACION DE CELDAS
							
						}//FIN DEL RECORRIODO POR COLUMNAS
					}//FIN DEL RECORRIDO POR FILAS
			}//FIN DEL RECORRIDO POR HOJAS 
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
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
