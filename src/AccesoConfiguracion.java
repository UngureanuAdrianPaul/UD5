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
      System.out.println("***");
      System.out.println(propiedades.get("jdbc"));
      System.out.println(propiedades.get("usuario"));
      System.out.println(propiedades.get("clave"));
      
      Conexion miConexion = new Conexion(
         (String) propiedades.get("jdbc"), 
         (String) propiedades.get("usuario"), 
         (String) propiedades.get("clave")
         );

      System.out.println(miConexion.toString());   

      
      
   } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
   } finally {
      if (lector!= null) lector.close();
   }
 }
   
}