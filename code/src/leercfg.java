import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class leercfg {
	
	public static boolean pass(String palabra) throws IOException {
		String ruta = "cfg.dat";	 
		File archivo = new File(ruta);
		String linea = new String();
		FileReader lector = new FileReader(archivo);
		BufferedReader buff = new BufferedReader(lector);
		linea = buff.readLine();
		boolean pass = false;
		if(linea.equalsIgnoreCase(palabra)){
			pass = true;
		}
		else{
			pass = false;
		}
		buff.close();
		lector.close();
		//if( ( linea = buff.readLine() ) != null && seguir) {}
		return pass;
	}
	
	public static String extraer() throws IOException {
		String ruta = "cfg.dat";	 
		File archivo = new File(ruta);
		String linea = new String();
		FileReader lector = new FileReader(archivo);
		BufferedReader buff = new BufferedReader(lector);
		linea = buff.readLine();
		buff.close();
		lector.close();
		return linea;
	}
}
