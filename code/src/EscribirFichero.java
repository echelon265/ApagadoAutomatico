import java.io.*;

public class EscribirFichero {

	public static void escribir(String linea){
	        FileWriter fichero = null;
	        PrintWriter pw = null;
			String ruta = "cfg.dat";	 
	        try{
	            fichero = new FileWriter(ruta);
	            pw = new PrintWriter(fichero);
	            pw.print(linea);
	        } 
	        catch (Exception e){
	            e.printStackTrace();
	        } 
	        finally{
	           try{
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } 
	           catch (Exception e2){
	              e2.printStackTrace();
	           }
	        }
	    }
}