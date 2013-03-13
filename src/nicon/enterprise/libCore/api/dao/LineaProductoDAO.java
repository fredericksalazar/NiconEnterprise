/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.dao;

import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.obj.LineaProducto;

public class LineaProductoDAO {

    private LineaProducto line;
    private boolean state;
    private String sentence;
    private int ExecuteSentence;
    private int counter;
    private ResultSet Data;
    private ArrayList ListLines;
    private AdminConector coneccion;

    public LineaProductoDAO(LineaProducto line) {
        this.line = line;
        this.coneccion = AdminConector.getInstance();
    }

    public boolean createLine() {
        try {
            System.out.println("Starting creation of Product Line ...");
            this.sentence = ("Insert into Lineas_productos values('" + this.line.getCodeLine() + "','" + this.line.getNameLine() + "','" + this.line.getDescriptionLine() + "','" + this.line.getCodeFamily() + "');");
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);

            if (this.ExecuteSentence == 0) {
                this.state = true;
                System.out.println("The Product Line has created correctly ...");
            } else {
                this.state = false;
                System.out.println("The product Line has not created ...");
            }
            this.line = null;
        } catch (Exception e) {
            System.out.println("A Error Had Ocurred when trying execute operation:\n".getClass().getName() + "\n" + e.getStackTrace());
        }
        return this.state;
    }

    public boolean deleteLine() {
        try {
            this.sentence = ("delete from Lineas_productos where Codigo_Linea='" + this.line.getCodeLine() + "');");
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);

            if (this.ExecuteSentence == 0) {
                this.state = true;
                System.out.println("The Product Line has deleted correctly ...");
            } else {
                this.state = false;
                System.out.println("The product Line has not deleted ...");
            }
            this.line = null;
        } catch (Exception e) {
            System.out.println("A Error Had Ocurred when trying execute operation:\n".getClass().getName() + "\n" + e.getStackTrace());
        }
        return this.state;
    }

    public boolean editLine(String id, String campo, String dato) {
        try {
            this.sentence = ("UPDATE Lineas_Productos SET " + campo + " ='" + dato + "' WHERE Codigo_Linea=" + id + ";");
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
        } catch (Exception e) {
        }
        return this.state;
    }

    public ArrayList getallLines() {
        try {
            this.counter = 0;
            this.ListLines = new ArrayList();
            this.sentence = "Select * from Lineas_Productos;";
            this.Data = this.coneccion.queryData(this.sentence);

            while (this.Data.next()) {
                this.line = new LineaProducto(this.Data.getString(1), this.Data.getString(2), this.Data.getString(3), this.Data.getString(4));
                this.ListLines.add(this.counter, this.line);
                this.counter += 1;
            }
            this.Data.close();
            this.line = null;
        } catch (Exception e) {
            System.out.println("A Error Had Ocurred when trying execute operation:\n".getClass().getName() + "\n" + e.getStackTrace());
        }
        return this.ListLines;
    }
}