
public class Conexion {
   private String jdbc;
   private String usuario;
   private String clave;
   public Conexion(String jdbc, String usuario, String clave) {
      this.jdbc = jdbc;
      this.usuario = usuario;
      this.clave = clave;
   }
   public String getJdbc() {
      return jdbc;
   }
   public void setJdbc(String jdbc) {
      this.jdbc = jdbc;
   }
   public String getUsuario() {
      return usuario;
   }
   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }
   public String getClave() {
      return clave;
   }
   public void setClave(String clave) {
      this.clave = clave;
   }
   @Override
   public String toString() {
      return "Conexion [jdbc=" + jdbc + ", usuario=" + usuario + ", clave=" + clave + "]";
   }

   
   
}