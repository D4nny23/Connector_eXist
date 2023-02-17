/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connector_exist;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author dev
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        XQDataSource xqs = new ExistXQDataSource();

        try {
            xqs.setProperty("serverName", "localhost");
            xqs.setProperty("port", "8080");

        } catch (XQException ex) {
        }

        XQConnection conn = null;
        try {
            conn = xqs.getConnection();//Establece la conexion
        } catch (XQException ex) {
        }

        XQPreparedExpression xqpe = null;//Como el resulSet
        try {
            xqpe = conn.prepareExpression("for $x in/EMPLEADOS/EMP_ROW return $x");
        } catch (XQException ex) {
        }

        XQResultSequence rs = null;

        try {
            rs = xqpe.executeQuery();//guardamos los resultados
        } catch (XQException ex) {
        }

        try {
            while (rs.next()) {
                System.out.println(rs.getItemAsString(null));
            }
        } catch (Exception e) {
        }
    }

}
