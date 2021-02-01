package view;

/**
 *
 * @author jorge
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Mail;

public class MailListReaderBD {
    private static String url = "jdbc:sqlite:KATA5.db";
    
    public static List<Mail> read() throws FileNotFoundException{
        String sql = "SELECT * FROM EMAIL";
        List<Mail> list = new ArrayList<>();
        
        try (   Connection conn = connect();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql)){
            
            //Recorrer registros
            while (rs.next()){
                Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(rs.getString("direccion"));
                if (m.matches()) {
                    Mail mail = new Mail(rs.getString("direccion"));
                    list.add(mail);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public static Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión a SQLite establecida");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }

}
