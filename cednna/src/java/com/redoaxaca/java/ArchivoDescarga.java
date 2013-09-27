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
	
	public ArchivoDescarga(ArrayList<com.redoaxaca.java.Row> data, ArrayList<ResultCategorias> cts,int tipo){
			
		SXSSFWorkbook wb = new SXSSFWorkbook(); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        Row rowCabecera;
        Cell cellCabecera;
        int cont;
        int contFila;
        
        rowCabecera = sh.createRow(1);
        cellCabecera = rowCabecera.createCell(0);
        cellCabecera.setCellValue("CATEGORIAS");
        cellCabecera = rowCabecera.createCell(1);
        cellCabecera.setCellValue(cts.size());
        rowCabecera = sh.createRow(2);
        cellCabecera = rowCabecera.createCell(0);
        cellCabecera.setCellValue("ZONA GEOGRAFICA");
         cellCabecera = rowCabecera.createCell(1);
        cellCabecera.setCellValue(tipo);
        
		switch (tipo) {
		case 1:
			
	                        
			rowCabecera = sh.createRow(4);
	        cellCabecera = rowCabecera.createCell(0);
	        cellCabecera.setCellValue("CLAVE");
	        cellCabecera = rowCabecera.createCell(1);
	        cellCabecera.setCellValue("DESCRIPCION");
	        cellCabecera = rowCabecera.createCell(2);
	        cellCabecera.setCellValue("ANIO");
	        cellCabecera = rowCabecera.createCell(3);
	        cellCabecera.setCellValue("MUJER");
	        cellCabecera = rowCabecera.createCell(4);
	        cellCabecera.setCellValue("HOMBRE");
	        cont =4;
	        for(ResultCategorias s : cts){
	        		cont++;
	        		 cellCabecera = rowCabecera.createCell(cont);
	        	   cellCabecera.setCellValue(s.tipo.getDescripcion());
	              
	        	   cont++;
	      		 cellCabecera = rowCabecera.createCell(cont);
	      		 cellCabecera.setCellValue("Categoria");
	               
	        }
	        contFila=5;
	        
	        for (com.redoaxaca.java.Row fila : data ) { //Renglones
	            
	        		Row row = sh.createRow(contFila);
	                Cell cell = row.createCell(0);
	                cell.setCellValue(fila.clave);
	                cell = row.createCell(1);
	                cell.setCellValue(fila.descripcion);
	                cell = row.createCell(2);
	                cell.setCellValue(fila.anio);
	                cell = row.createCell(3);
	                cell.setCellValue(fila.hombres);
	                cell = row.createCell(4);
	                cell.setCellValue(fila.mujeres);
	                
	                int conC=5;
	                
	                for(Categoria c : fila.categorias){
	            	     
	                  cell = row.createCell(conC);
	                  cell.setCellValue(c.getValorID());
	                  conC++;
	                  cell = row.createCell(conC);
	                  cell.setCellValue(c.getDescripcion());
	                  conC++;
	            	  
	                }
	                
	                contFila++;
			
	        }
			break;

			
			
		case 2:
			
			
			rowCabecera = sh.createRow(4);
	        cellCabecera = rowCabecera.createCell(0);
	        cellCabecera.setCellValue("REGION_ID");
	        cellCabecera = rowCabecera.createCell(1);
	        cellCabecera.setCellValue("REGION");
	        cellCabecera = rowCabecera.createCell(2);
	        cellCabecera.setCellValue("CLAVE");
	        cellCabecera = rowCabecera.createCell(3);
	        cellCabecera.setCellValue("DESCRIPCION");
	        cellCabecera = rowCabecera.createCell(4);
	        cellCabecera.setCellValue("ANIO");
	        cellCabecera = rowCabecera.createCell(5);
	        cellCabecera.setCellValue("MUJER");
	        
	        
	        cellCabecera = rowCabecera.createCell(6);
	        cellCabecera.setCellValue("HOMBRE");
	   
	   
	        
	        cont =6;
	        for(ResultCategorias s : cts){
	        		cont++;
	        		 cellCabecera = rowCabecera.createCell(cont);
	        	   cellCabecera.setCellValue(s.tipo.getDescripcion());
	              
	        	   cont++;
	      		 cellCabecera = rowCabecera.createCell(cont);
	      		 cellCabecera.setCellValue("Categoria");
	               
	        }
	        contFila=5;
	        
	        for (com.redoaxaca.java.Row fila : data ) { //Renglones
	            
	        		Row row = sh.createRow(contFila);
	            
	                Cell cell = row.createCell(0);
	                cell.setCellValue(fila.idRegion);
	                cell = row.createCell(1);
	                cell.setCellValue(fila.region);
	                cell = row.createCell(2);
	                cell.setCellValue(fila.clave);
	                cell = row.createCell(3);
	                cell.setCellValue(fila.descripcion);
	                cell = row.createCell(4);
	                cell.setCellValue(fila.anio);
	                cell = row.createCell(5);
	                cell.setCellValue(fila.hombres);
	                cell = row.createCell(6);
	                cell.setCellValue(fila.mujeres);
	                
	                int conC=7;
	                
	                for(Categoria c : fila.categorias){
	            	  
	            	     
	                  cell = row.createCell(conC);
	                  cell.setCellValue(c.getValorID());
	                  conC++;
	                  
	                  cell = row.createCell(conC);
	                  cell.setCellValue(c.getDescripcion());
	                  conC++;
	            	  
	                }
	                
	                contFila++;
	        }
			
			
			

			
			break;	
	
		case 3:
			
		
	                        
	        rowCabecera = sh.createRow(4);
	        cellCabecera = rowCabecera.createCell(0);
	        cellCabecera.setCellValue("REGION_ID");
	        cellCabecera = rowCabecera.createCell(1);
	        cellCabecera.setCellValue("REGION");
	        cellCabecera = rowCabecera.createCell(2);
	        cellCabecera.setCellValue("MUNICIPIO_ID");
	        cellCabecera = rowCabecera.createCell(3);
	        cellCabecera.setCellValue("MUNICIPIO");
	        cellCabecera = rowCabecera.createCell(4);
	        cellCabecera.setCellValue("CLAVE");
	        cellCabecera = rowCabecera.createCell(5);
	        cellCabecera.setCellValue("DESCRIPCION");
	        cellCabecera = rowCabecera.createCell(6);
	        cellCabecera.setCellValue("ANIO");
	        cellCabecera = rowCabecera.createCell(7);
	        cellCabecera.setCellValue("MUJER");
	        
	        
	        cellCabecera = rowCabecera.createCell(8);
	        cellCabecera.setCellValue("HOMBRE");
	   
	   
	        
	        cont =8;
	        for(ResultCategorias s : cts){
	        		cont++;
	        		 cellCabecera = rowCabecera.createCell(cont);
	        	   cellCabecera.setCellValue(s.tipo.getDescripcion());
	              
	        	   cont++;
	      		 cellCabecera = rowCabecera.createCell(cont);
	      		 cellCabecera.setCellValue("Categoria");
	               
	        }
	        contFila=5;
	        
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
	                cell.setCellValue(fila.clave);
	                cell = row.createCell(5);
	                cell.setCellValue(fila.descripcion);
	                cell = row.createCell(6);
	                cell.setCellValue(fila.anio);
	                cell = row.createCell(7);
	                cell.setCellValue(fila.hombres);
	                cell = row.createCell(8);
	                cell.setCellValue(fila.mujeres);
	                
	                int conC=9;
	                
	                for(Categoria c : fila.categorias){
	            	  
	            	     
	                  cell = row.createCell(conC);
	                  cell.setCellValue(c.getValorID());
	                  conC++;
	                  
	                  cell = row.createCell(conC);
	                  cell.setCellValue(c.getDescripcion());
	                  conC++;
	            	  
	                }
	                
	                contFila++;
	        }
			
			break;	
	
		case 4:
			
			break;	
	
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
