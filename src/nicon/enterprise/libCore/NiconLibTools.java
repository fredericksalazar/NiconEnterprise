/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class NiconLibTools {

    private static Date formatDate;
    private static SimpleDateFormat format;
    private static DateFormat formateador;
    private static String dateFormated;
    private static String currentDir;
    private static boolean state;
    private static File file;
    private static ObjectOutputStream OOS;
    private static ObjectInputStream OIS;
    private static PrintService printer;

    public static String currentDir() {
        currentDir = System.getProperty("user.dir");
        return currentDir;
    }

    public static String setPathDirectory() {
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }

    public static Date formatDate(String fecha) {
        try {
            format = new SimpleDateFormat("dd-mm-yyyy");
            format.parse(fecha);
        } catch (Exception e) {
            System.err.println("Ocurrió un error en NiconLibTools.formatDate():\n" + e);
        }
        return formatDate;
    }

    public static String dateFormatSimple(Date ToFormat) {
        try {
            format = new SimpleDateFormat("yyyy-MM-dd");
            dateFormated = format.format(ToFormat);
        } catch (Exception e) {
            System.err.println("Ocurrió un error en NiconLibTools.dateFormatSimple():\n" + e);
        }
        return dateFormated;
    }

    public static String writeObjectToFile(Object object, String path, String nameFile) {
        try {
            file = new File(path + "/" + nameFile);
            System.out.println("Iniciando escritura de archivo en: " + file.getAbsolutePath());
            OOS = new ObjectOutputStream(new FileOutputStream(file));
            OOS.writeObject(object);
            OOS.close();
        } catch (IOException ex) {
            Logger.getLogger(NiconLibTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file.getPath();
    }

    public static ObjectInputStream readFileObject(String path) {
        try {
            OIS = new ObjectInputStream(new FileInputStream(path));
        } catch (IOException io) {
            System.err.println("Ocurrió un error en NiconLibTools.readFileObject():\n" + io);
        }
        return OIS;
    }

    public static ObjectOutputStream getObjectFileToWrite(String Path) {
        try {
            OOS = new ObjectOutputStream(new FileOutputStream(Path));
        } catch (Exception e) {
            System.err.println("Ocurrió un error en NiconLibTools.getObjectFileToWrite():\n" + e);
        }
        return OOS;
    }

    public static boolean verifyExistDir(String url) {
        file = new File(url);
        if (file.exists()) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    public static boolean verifyAndCreateDir(String url) {
        file = new File(url);
        if (!file.exists()) {
            state = file.mkdir();
        } else {
            state = true;
        }
        return state;
    }

    public static boolean verifiExistFile(String url) {
        try {
            file = new File(url);
            if (file.exists()) {
                state = true;
            } else {
                state = false;
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error en NiconLibTools.verifiExistFile():\n" + e);
        }
        return state;
    }

    public static boolean createDirectory(String url) {
        file = new File(url);
        state = file.mkdir();
        return state;
    }

    public static boolean deleteFile(String Url) {
        try {
            System.out.println("Preparandose para eliminar el archivo: " + Url + " ...");
            file = new File(Url);
            if (file.delete()) {
                System.out.println("El archivo ha sido eliminado exitosamente ...");
                state = true;
            } else {
                System.out.println("El archivo no se pudo eliminar ...");
                state = false;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en NiconLibTools.deleteDirectory():\n" + e);
        }
        return state;
    }

    public static void getPrinterDefault() {
        printer = PrintServiceLookup.lookupDefaultPrintService();
    }
}