import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

public class AccesoConfiguracion {
 public static void main(String[] args) throws IOException {
   Properties propiedades = new Properties();
   
   FileInputStream lector = null;

   try {
      lector = new FileInputStream(new File("./src/configuracion.properties"));
      propiedades.load(lector);
      propiedades.list(System.out);
   } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
   } finally {
      if (lector!= null) lector.close();
   }
 }
   
}