import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;

public class AccesoConfiguracion {

   public static void main(String[] args) throws IOException {
      Properties propiedades = new Properties();
      String pathFichero = "./src/configuracion.properties";
      FileInputStream lector = null;

      try {
         lector = new FileInputStream(new File("./src/configuracion.properties"));
         propiedades.load(lector);
         System.out.println("***Visualizando con load y list***");
         propiedades.list(System.out);
         System.out.println("***Visualizando con get***");
         System.out.println(propiedades.get("jdbc"));
         System.out.println(propiedades.get("usuario"));
         System.out.println(propiedades.get("clave"));

         Conexion miConexion = new Conexion(
               (String) propiedades.get("jdbc"),
               (String) propiedades.get("usuario"),
               (String) propiedades.get("clave"));

         System.out.println("***Visualizando con conexión***");
         System.out.println(miConexion);

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

         System.out.println("***Mostrar los strings introuducidos***");
         System.out.println("nuevo jdbc: " + nuevoJdbc);
         System.out.println("nuevo usuario: " + nuevoUsuario);
         System.out.println("nuevo clave: " + nuevoClave);

         Conexion nuevaConexion = new Conexion(nuevoJdbc, nuevoUsuario, nuevoClave);

         insertarNuevaConfiguracion(nuevaConexion, propiedades);
         System.out.println("***Visualizando con list los introuducidos con insertarNuevaConfiguracion***");
         propiedades.list(System.out);

         teclado.close();

      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      } finally {
         if (lector != null)
            lector.close();

      }

      // NUEVO TRY-CATCH

      FileOutputStream escritor = null;

      try {
         escritor = new FileOutputStream(new File(pathFichero));
         propiedades.store(escritor, "Propiedades 23/04");

      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      } finally {
         if (escritor != null) {
            escritor.close();

         }
      }

      System.out.println("***Visualizando con leerFichConf***");
      leerFichConf(pathFichero);
   } // EO MAIN

   public static void insertarNuevaConfiguracion(Conexion conx, Properties properties) {
      properties.setProperty("jdbc", conx.getJdbc());
      properties.setProperty("usuario", conx.getUsuario());
      properties.setProperty("clave", conx.getClave());
      properties.setProperty("nuevaClave", "nuevoValor");
   }

   public static void leerFichConf(String pathFichero) throws IOException {
      Properties misProperties = new Properties();

      BufferedReader lectorBuffered = null;

      try {
         lectorBuffered = new BufferedReader(new FileReader(new File(pathFichero)));
         misProperties.load(lectorBuffered);
         misProperties.list(System.out);

      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      } finally {
         if (lectorBuffered != null) {
            lectorBuffered.close();
         }
      }
   }

}// EO CLASS