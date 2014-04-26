import java.io.*;
import java.security.MessageDigest;
import java.util.Calendar;


public class Mod {

	/**
	 * Apagado automatico del PC
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		desocultar();
		File pass = new File ("cfg.dat");
		
		if(!pass.exists()){
			System.out.println("Antes de usar el programa debe crear una clave");
			System.out.print("Introduzca la clave: ");
			String clave = Utilidades.leerCadena();
			String encriptado = MD5(clave);
			System.out.println("MD5 "+encriptado);
			try {
				// A partir del objeto File creamos el fichero físicamente
				 if (pass.createNewFile()){
				 System.out.println("Fichero de configuracion creado correctamente");
				 System.out.println();
				 }
				 else if(pass.exists()){
					 System.out.println("Parametros de configuracion cargados correctamente");
				 }
				 else
					 System.out.println("No ha podido ser creado el fichero de configuracion");
					} catch (IOException ioe) {
					  ioe.printStackTrace();
					}
			
			EscribirFichero.escribir(encriptado);
			System.out.println("Clave creada correctamente");
			System.out.println("Reinicie el programa");
			}
		else{
			int sel=0;
			do{
				System.out.println("********** Apagado automatico del PC **********");
				System.out.println("MENU");
				System.out.println("\t 1. Apagar ahora");
				System.out.println("\t 2. Apagado programado");
				System.out.println("\t 3. Cancelar Apagado");
				System.out.println("\t 4. Cambiar clave");
				System.out.println("\t 5. Salir");
				System.out.print("Opcion: ");
				sel = Utilidades.leerEntero();
				if(sel>5 || sel<1){
					System.out.println("La opcion introducida no esta en la lista");
					System.out.println();
				}
				else{
					switch (sel){
					case 1:
						apagarAhora(leercfg.extraer());
						break;
					case 2:
						apagarProgramado();
						break;
					case 3:
						cancelar(leercfg.extraer());
						break;
					case 4:
						cambiar();
						break;
					case 5:
						System.out.println("Programa finalizado");
					}
				}
			
			}while(sel != 5);
		}
		ocultar();
}

	public static void cambiar() throws Exception {
		System.out.println();
		System.out.println("MENU: Cambiar Clave");
		System.out.println();
		int errores = 0;
		boolean erronea = true;
		do{
			System.out.print("Introduzca su clave actual: ");
			String actual = Utilidades.leerCadena();
			if(leercfg.pass(MD5(actual))){
				System.out.println("Comprobacion correcta.");
				System.out.print("Introduzca la nueva clave: ");
				String nueva = Utilidades.leerCadena();
				String encriptado = MD5(nueva);
				System.out.println("MD5 "+encriptado);
				EscribirFichero.escribir(encriptado);
				erronea = false;
				System.out.println();
				System.out.println("La clave se ha cambiado con exito");
				System.out.println();
			}
			else{
				System.out.println("La clave es incorrecta");
				errores++;
			}
		}while(errores<3 && erronea);
		if(errores==3){
			System.out.println();
			System.out.println("Ha fallado tres veces. Por seguridad, vuelva a intentarlo mas tarde.");
			System.out.println();
		}
	}

	public static String MD5(String nueva) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(nueva.getBytes());
		
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
				}
			}
		return h.toString();
	}
	
	public static void anulados(){
		System.out.println();
		System.out.println("Se ha establecido un nuevo apagado programado.");
		System.out.println("Todos los anteriores han sido anulados");
		System.out.println();
	}

	public static void cancelar(String actual) throws Exception {
		boolean valida = false;
		int errores = 0;
		do{
			System.out.print("Introduzca la clave: ");
			String clave = Utilidades.leerCadena();
			if(MD5(clave).equals(actual)){
				Runtime cancelar= Runtime.getRuntime();
				try{
				       cancelar.exec("cmd /c shutdown -a");
				       System.out.println();
				       System.out.println("Se cancelo el apagado del sistema");
				       System.out.println();
					   valida = true;
				       }
				       catch(Exception e){
				         System.out.println(e.getMessage());
				       }
			}
			else{
				System.out.println("La clave es incorrecta");
				errores++;
			}
		}while(errores<3 && !valida);
		if(errores==3){
			System.out.println();
			System.out.println("Ha fallado tres veces. Por seguridad, vuelva a intentarlo mas tarde.");
			System.out.println();
		}
	}

	public static void apagarProgramado() {
		
		System.out.println();
		System.out.println("	TIEMPOS");
		System.out.println();
		System.out.println("0. 10 minutos");
		System.out.println("1. 20 minutos");
		System.out.println("2. 30 minutos");
		System.out.println("3. 40 minutos");
		System.out.println("4. 1 h");
		System.out.println("5. 1h y 30 min");
		System.out.println("6. 2h");
		System.out.println("7. 2h y 30 min");
		System.out.println("8. 3h");
		System.out.println("9. 4h");
		System.out.println("10. 5h");
		System.out.println("11. 6h");
		System.out.println("12. 7h");
		System.out.println("13. 8h");
		System.out.println("14. 1 dia");
		System.out.println();
		System.out.print("Opcion (1...14): ");
		int opc = Utilidades.leerEntero();
		System.out.println();
		if(opc > 14 || opc < 0){
			System.out.println("La opcion introducida no esta en la lista");
			System.out.println();
		}
		else{
			switch (opc){
			case 0:
				cancelarRoot();
				apagarEn(10*60);
				anulados();
				break;
			case 1:
				cancelarRoot();
				apagarEn(20*60);
				anulados();
				break;
			case 2:
				cancelarRoot();
				apagarEn(30*60);
				anulados();
				break;
			case 3:
				cancelarRoot();
				apagarEn(40*60);
				anulados();
				break;
			case 4:
				cancelarRoot();
				apagarEn(3600);
				anulados();
				break;
			case 5:
				cancelarRoot();
				apagarEn(5400);
				anulados();
				break;
			case 6:
				cancelarRoot();
				apagarEn(7200);
				anulados();
				break;
			case 7:
				cancelarRoot();
				apagarEn(9000);
				anulados();
				break;
			case 8:
				cancelarRoot();
				apagarEn(10800);
				anulados();
				break;
			case 9:
				cancelarRoot();
				apagarEn(4*3600);
				anulados();
				break;
			case 10:
				cancelarRoot();
				apagarEn(5*3600);
				anulados();
				break;
			case 11:
				cancelarRoot();
				apagarEn(6*3600);
				anulados();
				break;
			case 12:
				cancelarRoot();
				apagarEn(7*3600);
				anulados();
				break;
			case 13:
				cancelarRoot();
				apagarEn(8*3600);
				anulados();
				break;
			case 14:
				cancelarRoot();
				apagarEn(24*3600);
				anulados();
				break;
				}
		}
	}
	
	public static void cancelarRoot() {
		Runtime cancelar= Runtime.getRuntime();
		try{
		       cancelar.exec("cmd /c shutdown -a");
		       }
		       catch(Exception e){
		         System.out.println(e.getMessage());
		       }
	}

	public static void apagarEn(int tiempo) {
		// TODO Auto-generated method stub
		//Da la orden de apagar el ordenador con un retardo de %tiempo% segundos
				Runtime apagar= Runtime.getRuntime();
				try{
				       apagar.exec("cmd /c shutdown -s -t "+tiempo);
				       }
				       catch(Exception e){
				         System.out.println(e.getMessage());
				       }
	}

	public static void apagarAhora(String actual) throws IOException, Exception {
		boolean valida = false;
		int errores = 0;
		do{
			System.out.print("Introduzca la clave: ");
			String clave = Utilidades.leerCadena();
			if(MD5(clave).equals(actual)){
				Runtime cancelar= Runtime.getRuntime();
				try{
				       cancelar.exec("cmd /c shutdown -s -t 1");
				       System.out.println();
					   System.out.println("El ordenador se apagara en 1 segundo.");
					   System.out.println("Cierre las aplicaciones que tenga abiertas.");
				       System.out.println();
					   valida = true;
				       }
				       catch(Exception e){
				         System.out.println(e.getMessage());
				       }
			}
			else{
				System.out.println("La clave es incorrecta");
				errores++;
			}
		}while(errores<3 && !valida);
		if(errores==3){
			System.out.println();
			System.out.println("Ha fallado tres veces. Por seguridad, vuelva a intentarlo mas tarde.");
			System.out.println();
		}
	}

	public static String hora() {
		int hora, minutos;
		Calendar calendario = Calendar.getInstance();
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		return hora + ":" + minutos;
	}
	
	public static void ocultar() {

		String ruta= "\"C:\\users\\"+ System.getProperty("user.name") +"\\Documents\\cfg.dat\"";
		Runtime cancelar= Runtime.getRuntime();
		try{
		       cancelar.exec("cmd /c attrib +H "+ruta);
		       }
		       catch(Exception e){
		         System.out.println(e.getMessage());
		       }
	}
	
	public static void desocultar() {

		String ruta= "\"C:\\users\\"+ System.getProperty("user.name") +"\\Documents\\cfg.dat\"";
		Runtime cancelar= Runtime.getRuntime();
		try{
		       cancelar.exec("cmd /c attrib -H "+ruta);
		       }
		       catch(Exception e){
		         System.out.println(e.getMessage());
		       }
	}


}
