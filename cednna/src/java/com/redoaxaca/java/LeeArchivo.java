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

	public LeeArchivo(File fileName) throws ParseException {

		try {
			System.out.println("ENTRO A LEER EXCCEL" );
			
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
	
							
							if(hssfCell.getRowIndex() == 2 && hssfCell.getColumnIndex() == 0){
								
								claveDependencia=(int)hssfCell.getNumericCellValue();
								System.out.println(claveDependencia);
							}
							
							
							
							switch (hssfCell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC:
								switch (hssfCell.getColumnIndex()) {
									case 0:
										tempRow.setIdLocalidad(new Double(hssfCell.getNumericCellValue()).intValue());
									
										break;
									case 2:
										tempRow.setIdMunicipio(new Double(hssfCell.getNumericCellValue()).intValue());
										
										break;
									case 4:
										tempRow.setIdLocalidad(new Double(hssfCell.getNumericCellValue()).intValue());
										break;
									
									case 8:
										tempRow.setHombres(new Double(hssfCell.getNumericCellValue()).intValue());
									case 7:
										tempRow.setMujeres(new Double(hssfCell.getNumericCellValue()).intValue());
										break;
								}
								break;
							case HSSFCell.CELL_TYPE_STRING:
								switch (hssfCell.getColumnIndex()) {
									case 1:
									
										tempRow.setRegion(hssfCell.getStringCellValue());
										
										break;
									case 3:
										tempRow.setMunicipio(hssfCell.getStringCellValue());
									
										break;
									case 5:
										tempRow.setLocalidad(hssfCell.getStringCellValue());
										
										break;
									
									case 6:
										tempRow.setDescripcion(hssfCell.getStringCellValue());
										renglones.add(tempRow);
										break;
								}
								break;
							default:
								//System.out.println(hssfCell.getColumnIndex());
								break;
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
