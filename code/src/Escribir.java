import java.io.FileWriter;
import java.io.PrintWriter;


public class Escribir {

	public void linea(String linea, String ruta) {

        FileWriter fichero = null;
        PrintWriter pw = null; 
        try{
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero, true);
            pw.print(linea);
            pw.println();
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
