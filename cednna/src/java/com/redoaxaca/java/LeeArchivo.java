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
	String claveDependencia = "";
	
	public LeeArchivo(File fileName) throws ParseException {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
			int numeroHojas = workBook.getNumberOfSheets();
			for (int i = 0; i < numeroHojas; i++) {
				XSSFSheet hssfSheet = workBook.getSheetAt(i);
				Iterator rowIterator = hssfSheet.rowIterator();
				while (rowIterator.hasNext()) {
					XSSFRow hssfRow = (XSSFRow) rowIterator.next();
					Iterator iterator = hssfRow.cellIterator();
					Row tempRow = new Row();
					while (iterator.hasNext()) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						//Obtener la clave de la dependencia
						if(hssfCell.getRowIndex() == 3 && hssfCell.getColumnIndex() == 2){
						 }else if(hssfCell.getRowIndex() == 0 && hssfCell.getColumnIndex() == 3){ 
							 claveDependencia =  hssfCell.getStringCellValue();
						
						 }else if(hssfCell.getRowIndex() >= 6 && hssfCell.getColumnIndex() >= 0 && hssfCell.getColumnIndex() <= 13){ //Obtener los datos de la tabla
							switch (hssfCell.getCellType()) {
								case HSSFCell.CELL_TYPE_NUMERIC:
									switch (hssfCell.getColumnIndex()) {
										case 1:
											tempRow.setFligth(new Double(hssfCell.getNumericCellValue()).intValue());
											break;
										case 9:
											tempRow.setFligthOut(new Double(hssfCell.getNumericCellValue()).intValue());
											//tempRow.setDay(new Double(hssfCell.getNumericCellValue()).intValue());
											break;
										case 13:
											try{
											String val= String.valueOf(hssfCell.getNumericCellValue());
											
											
											String tt[]=val.split("\\.");
											if(tt.length>0){
												tempRow.setIntervalo(tt[0]);
												
											}
											renglones.add(tempRow);
											}catch (Exception e) {
												renglones.add(tempRow);
												e.printStackTrace();
											}
											break;
									}
									break;
								case HSSFCell.CELL_TYPE_STRING:
									switch (hssfCell.getColumnIndex()) {
										case 0:
											//System.out.println("-1");
											tempRow.setEst(hssfCell.getStringCellValue());
											//System.out.println("+1");
											break;
										case 2:
											tempRow.setOri(hssfCell.getStringCellValue());
											//tempRow.setFligthOut(new Double(hssfCell.getNumericCellValue()).intValue());
											break;
										case 3:
											tempRow.setItd(hssfCell.getStringCellValue());
											//tempRow.setAirCraft(hssfCell.getStringCellValue());
											break;
										case 4:
											tempRow.setEta(hssfCell.getStringCellValue());
											//tempRow.setOri(hssfCell.getStringCellValue());
											break;
										case 5:
											tempRow.setTiempoEstacion(hssfCell.getStringCellValue());
   										    //tempRow.setEst(hssfCell.getStringCellValue());
											break;
										case 6:
											tempRow.setEdt(hssfCell.getStringCellValue());
											//tempRow.setDes(hssfCell.getStringCellValue());
											break;
										case 7:
											tempRow.setOad(hssfCell.getStringCellValue());
											//tempRow.setEta(hssfCell.getStringCellValue());
											break;
										case 8:
											tempRow.setDes(hssfCell.getStringCellValue());
											//tempRow.setEdt(hssfCell.getStringCellValue());
											break;
										case 10:
											tempRow.setAirCraft(hssfCell.getStringCellValue());
											break;
										case 11:
											tempRow.setFIni(hssfCell.getStringCellValue());
											//tempRow.setRevision(hssfCell.getStringCellValue());
											break;
										case 12:
											tempRow.setFFin(hssfCell.getStringCellValue());
											//tempRow.setFIni(hssfCell.getStringCellValue());
											break;
										case 13:
											tempRow.setIntervalo(hssfCell.getStringCellValue());
											//tempRow.setFFin(hssfCell.getStringCellValue());
											System.out.println(hssfCell.getStringCellValue());
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
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	public String getClaveDependencia(){
		return claveDependencia;
	}
	
	public ArrayList<Row> getDatos() {
		return renglones;
	}
	
}
