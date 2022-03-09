package csvamysql;

import csvamysql.Conexion;
import com.csvreader.CsvReader;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xcheko51x
 */
public class CSVaMySQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        usuarios = importarCSV();

        insertarMySQL(usuarios);
    }

    public static List<Usuario> importarCSV() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            CsvReader leerUsuarios = new CsvReader("pysclarors20220308.csv");
            leerUsuarios.readHeaders();

            while (leerUsuarios.readRecord()) {
                String factura = leerUsuarios.get(0);
                String valor = leerUsuarios.get(1);
                String valor_adicional = leerUsuarios.get(2);
                String transaccion = leerUsuarios.get(3);
                String fecha_pago = leerUsuarios.get(4);

                usuarios.add(new Usuario(factura, valor, valor_adicional, transaccion, fecha_pago));
            }

            leerUsuarios.close();

            System.out.println("LISTA DE USUARIOS DEL CSV\n");
            for (Usuario user : usuarios) {
                System.out.println(
                        user.getFactura() + ", "
                        + user.getValor() + ", "
                        + user.getValor_adicional() + ", "
                        + user.getTransaccion() + ", "
                        + user.getFecha_pago()
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;

    }

    public static void insertarMySQL(List<Usuario> usuarios) {

        System.out.println("\nSE VAN A INSERTA: " + usuarios.size() + " REGISTROS\n");

        try {
            Conexion sql = new Conexion();
            sql.ConexionPostgres();

            String query = "INSERT INTO ingreso(factura, valor, valor adicional, transaccion, fecha de pago) VALUES(?,?,?,?,?)";
            sql.actualizar(query);

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);

            for (int i = 0; i < usuarios.size(); i++) {
                ps.setString(1, usuarios.get(i).getFactura());
                ps.setString(2, usuarios.get(i).getValor());
                ps.setString(3, usuarios.get(i).getValor_adicional());
                ps.setString(4, usuarios.get(i).getTransaccion());
                ps.setString(5, usuarios.get(i).getFecha_pago());
                //log(ps);

                ps.executeUpdate();

                System.out.println("Se inserto el elemento: " + (i + 1) + "/" + usuarios.size());
            }

            ps.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CSVaMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CSVaMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CSVaMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CSVaMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
