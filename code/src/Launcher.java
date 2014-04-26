
public class Launcher {

	public static void main(String[] args) throws Exception{
		if(System.getProperty("os.name").toLowerCase().contains("windows"))
			Mod.main(null);
		else
			System.err.println("Esta App\nSolo se puede ejecutar en entornos Windows\nSu ordenador esta ejecutando "+System.getProperty("os.name").toLowerCase());
	}

}
