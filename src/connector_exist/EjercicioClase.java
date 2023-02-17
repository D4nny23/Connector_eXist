/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connector_exist;

import java.util.Scanner;
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
public class EjercicioClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        boolean decision = false;
        int op;
        System.out.print("Dime la dirección IP a la que desea conectarse: ");
        String direccionIP = sc.nextLine();
        System.out.print("Dime el puerto al que desea conectarse: ");
        String puerto = sc.nextLine();

        XQDataSource xqs = new ExistXQDataSource();

        try {
            xqs.setProperty("serverName", direccionIP);
            xqs.setProperty("port", puerto);
        } catch (XQException ex) {
        }

        XQConnection conn = null;
        try {
            conn = xqs.getConnection();//Establece la conexion
        } catch (XQException ex) {
        }
        while (!decision) {
            System.out.println("Elige una opción: ");
            System.out.println("1.Mostrar el nombre y dirección de socios" + "\n"
                    + "2.Mostrar los códigos y nombre de socios asociados segun su direccón." + "\n"
                    + "3.Mostrar el nombre de los socios ordenados por antigüedad"+"\n"
                    + "4.Mostrar las actividades de las que dispone el gimnasio"+"\n"
                    + "5.");
            op = Integer.parseInt(sc.nextLine());
            XQPreparedExpression xqpe = null;//Como el resulSet
            switch (op) {
                case 1:
                    try {
                    xqpe = conn.prepareExpression("for $x in /SOCIOS_GIM/fila_socios return <socio><nombre>{$x/NOMBRE/text()}</nombre><direccion>{$x/DIRECCION/text()}</direccion></socio>");
                    XQResultSequence rs = null;
                    rs = xqpe.executeQuery();//guardamos los resultados
                    while (rs.next()) {
                        System.out.println(rs.getItemAsString(null));
                    }
                } catch (Exception e) {
                }

                break;

                case 2:
                    System.out.print("Dime la dirección: ");
                    String direccion= sc.nextLine();
                    try {
                    xqpe = conn.prepareExpression("for $x in /SOCIOS_GIM/fila_socios where $x/DIRECCION ='"+direccion+"'return <socio><id>{$x/COD/text()}</id><direccion>{$x/DIRECCION/text()}</direccion><nombre>{$x/NOMBRE/text()}</nombre></socio>");
                    XQResultSequence rs = null;
                    rs = xqpe.executeQuery();//guardamos los resultados
                    while (rs.next()) {
                        System.out.println(rs.getItemAsString(null));
                    }
                } catch (Exception e) {
                }
                break;

                case 3:
                    try {
                    xqpe = conn.prepareExpression("<SociosOrdenados>{for $x in /SOCIOS_GIM/fila_socios let $anyo:=substring($x/FECHA_ALT,7,2),$mes:=substring($x/FECHA_ALT,4,2),$dia:=substring($x/FECHA_ALT,1,2) order by $anyo,$mes,$dia return <Nombre>{$x/NOMBRE/text()}</Nombre>}</SociosOrdenados>");
                    XQResultSequence rs = null;
                    rs = xqpe.executeQuery();//guardamos los resultados
                    while (rs.next()) {
                        System.out.println(rs.getItemAsString(null));
                    }
                } catch (Exception e) {
                }

                    break;

                case 4:
                    try {
                    xqpe = conn.prepareExpression("<Actividades>{for $x in /ACTIVIDADES_GIM/fila_actividades return <Nombre>{$x/NOMBRE/text()}</Nombre>}</Actividades>");
                    XQResultSequence rs = null;
                    rs = xqpe.executeQuery();//guardamos los resultados
                    while (rs.next()) {
                        System.out.println(rs.getItemAsString(null));
                    }
                } catch (Exception e) {
                }

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 11:
                    break;
                case 12:
                    System.out.println("Saliendo....");
                    decision = true;
                    break;
            }
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
