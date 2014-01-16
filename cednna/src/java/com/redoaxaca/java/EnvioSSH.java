package com.redoaxaca.java;

import java.io.FileWriter;  
import java.io.PrintWriter;  
  
import com.jcraft.jsch.ChannelSftp;  
import com.jcraft.jsch.JSch;  
import com.jcraft.jsch.Session;  
import com.jcraft.jsch.UserInfo;  
 
public class EnvioSSH {
    private String  user = "czuluaga";
    private String  host = "remoto123";
    private Integer port = 22;
    private String  pass = "clave321";
 
    
    
    
    public void enviar() throws Exception {  
    	  System.out.println("------------------- INICIO ----------------- ");  
    	  JSch jsch = new JSch();  
    	  Session session = jsch.getSession(user, host, port);  
  //
    	  
    	  
    	// UserInfo ui = UserInfo.class.newInstance();  
    	//S UserInfo ui = new UserInfo()EnvioSSH;  
    	
    	 
    	  /* Informacion de las rutas del archivo */  
    	  String nombreLocalArchivo = "localServidor.txt";  
    	  String carpetaLocal = "c:/test/";  
    	  String nombreRemotoArchivo = "NombreArchivoRemoto.html";  
    	  String carpetaRemota = "/listas/";  
    	  
    	//  session.setUser("wqe");  
    	  session.setPassword(pass);  
    	  session.connect();  
    	  ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");  
    	  sftp.connect();  
    	  generarFichero(carpetaLocal, nombreLocalArchivo);  
    	  // Nos ubicamos en la carpeta Remota  
    	  sftp.cd("/docs/web/" + carpetaRemota);  
    	  System.out.println("Subiendo ...");  
    	  // Colocamos el archivo local en el servidor a la carpeta remota.  
    	  sftp.put(carpetaLocal + nombreLocalArchivo, nombreRemotoArchivo);  
    	  
    	  System.out.println("Archivo subido...");  
    	  
    	  System.out.println("El archivo se puede verificar en la url:");  
    	  System.out.println("http://artemisa.unicauca.edu.co/~test"  
    	    + carpetaRemota + nombreRemotoArchivo);  
    	  
    	  sftp.exit();  
    	  sftp.disconnect();  
    	  session.disconnect();  
    	  
    	  System.out.println("----------------- FIN ----------------- ");  
    	 }  
    	  
    	 private  void generarFichero(String carpetaLocal, String nombreLocalArchivo) {  
	    	  
    		  FileWriter fichero = null;  
	    	  PrintWriter pw = null;  
	    	  try {  
	    	   fichero = new FileWriter(carpetaLocal + nombreLocalArchivo);  
	    	   pw = new PrintWriter(fichero);  
	    	   pw.println("Fichero con extensi—n TXT, para ser cargado por SFTP");  
	    	  
	    	  } catch (Exception e) {  
	    	   e.printStackTrace();  
	    	  } finally {  
	    	   try {  
	    	    if (null != fichero)  
	    	     fichero.close();  
	    	   } catch (Exception e2) {  
	    	    e2.printStackTrace();  
	    	   }  
	    	  }  
    	 }  
    	

}