import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
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

      String nuevoJdbc;
      String nuevoUsuario;
      String nuevoClave;
      Scanner teclado = new Scanner(System.in);
      System.out.print("Introduce nuevo jdbc: ");
      nuevoJdbc = teclado.nextLine();
      System.out.print("Introduce nuevo usuario: ");
      nuevoUsuario = teclado.nextLine();
      System.out.print("Introduce nuevo clave: ");
      nuevoClave = teclado.nextLine();

      System.out.println("nuevo jdbc: "+nuevoJdbc);
      System.out.println("nuevo usuario: "+nuevoUsuario);
      System.out.println("nuevo clave: "+nuevoClave);

      Conexion nuevaConexion = new Conexion(nuevoJdbc, nuevoUsuario, nuevoClave);

      insertarNuevaConfiguracion(nuevaConexion, propiedades);
      propiedades.list(System.out);

      teclado.close();
      
   } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
   } finally {
      if (lector!= null) lector.close();
      
 }

   
   }
   public static void insertarNuevaConfiguracion(Conexion conx, Properties properties){
      properties.setProperty("jdbc", conx.getJdbc());
      properties.setProperty("usuario", conx.getUsuario());
      properties.setProperty("clave", conx.getClave());
      properties.setProperty("nuevaClave", "nuevoValor");
   }

   
   
}