package com.redoaxaca.java;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import mx.gob.redoaxaca.cednna.domino.Categoria;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class ArchivoDescarga {
	
	String ruta;
	
	public ArchivoDescarga(ArrayList<com.redoaxaca.java.Row> data, ArrayList<String>  categoria){
			
		SXSSFWorkbook wb = new SXSSFWorkbook(); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
                        
        Row rowCabecera = sh.createRow(0);
        Cell cellCabecera = rowCabecera.createCell(0);
        cellCabecera.setCellValue("REGION_ID");
        
        cellCabecera = rowCabecera.createCell(1);
        cellCabecera.setCellValue("REGION");
       
        cellCabecera = rowCabecera.createCell(2);
        cellCabecera.setCellValue("MUNICIPIO_ID");
       
        cellCabecera = rowCabecera.createCell(3);
        cellCabecera.setCellValue("MUNICIPIO");
       
        
        cellCabecera = rowCabecera.createCell(4);
        cellCabecera.setCellValue("LOCALIDAD_ID");
        
        
        cellCabecera = rowCabecera.createCell(5);
        cellCabecera.setCellValue("LOCALIDAD");
        
        cellCabecera = rowCabecera.createCell(6);
        cellCabecera.setCellValue("CLAVE");
        
        
        cellCabecera = rowCabecera.createCell(7);
        cellCabecera.setCellValue("DESCRIPCION");
        
        cellCabecera = rowCabecera.createCell(8);
        cellCabecera.setCellValue("ANIO");
        
        
        cellCabecera = rowCabecera.createCell(9);
        cellCabecera.setCellValue("MUJER");
        
        
        cellCabecera = rowCabecera.createCell(10);
        cellCabecera.setCellValue("HOMBRE");
   
   
        
        int cont =10;
        for(String s : categoria){
        		cont++;
        		 cellCabecera = rowCabecera.createCell(cont);
        	   cellCabecera.setCellValue(s);
              
               
        }
        int contFila=1;
        
        for (com.redoaxaca.java.Row fila : data ) { //Renglones
            
        		Row row = sh.createRow(contFila);
            
                Cell cell = row.createCell(0);
                cell.setCellValue(fila.idRegion);
                
                cell = row.createCell(1);
                cell.setCellValue(fila.region);
                
                cell = row.createCell(2);
                cell.setCellValue(fila.idMunicipio);


                cell = row.createCell(3);
                cell.setCellValue(fila.municipio);
                
               
                
                cell = row.createCell(4);
                cell.setCellValue(fila.idLocalidad);
                
                
                cell = row.createCell(5);
                cell.setCellValue(fila.localidad);
                
                cell = row.createCell(6);
                cell.setCellValue(fila.clave);
                
                cell = row.createCell(7);
                cell.setCellValue(fila.descripcion);
                
           
                cell = row.createCell(8);
                cell.setCellValue(fila.anio);
                
                
                cell = row.createCell(9);
                cell.setCellValue(fila.hombres);
                
                
                cell = row.createCell(10);
                cell.setCellValue(fila.mujeres);
                
                int conC=11;
                
                for(long c : fila.categorias){
            	  
            	     
                  cell = row.createCell(conC);
                  cell.setCellValue(c);
                  conC++;
            	  
                }
                
                contFila++;
                
                   
        }
               
        try{
        	File carpetaDestino = new File("/var/lib/cednnafiles");
        	File tempFile = File.createTempFile("xxx",".xlsx",carpetaDestino);
        	FileOutputStream out = new FileOutputStream(tempFile);
        	ruta = tempFile.getAbsolutePath();
        	wb.write(out);
        	out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
       
	}
	
	public String getRuta(){
		return ruta;
	}
	
}
