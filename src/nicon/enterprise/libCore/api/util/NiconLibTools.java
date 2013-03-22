/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore.api.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * Esta clase es como una Kit de herramientas basicas para operaciones comunes del sistema, provee metodos especificos
 * que ayudara a cumplir con ciertas tareas de forma limpia y de facil acceso, en ella se podrán encontrar herramientas
 * para el parseo de datos, manejo de ficheros, efectos, y acciones en todo tipo.
 * 
 *  
 * @author Frederick Adolfo Salazar Sanchez
 * @version 2.0
 */
public class NiconLibTools {

    private static Date date;
    private static SimpleDateFormat format;
    private static Calendar systemDate;
    
    private static String dateFormated;
    private static String currentDir;
    private static boolean state;
    
    private static File file;
    private static ObjectOutputStream OOS;
    private static ObjectInputStream OIS;
    
    private static String paternDirectory;
    private static String pathFile;
    private static int option;
    private static AdminConector conect;
    

    /**
     * este metodo permite al sistema obtener el actual directorio de ttrabajo en el que se esta ejecutando el jar,
     * o desde donde ha sido ejecutado el jar.
     * 
     * @return String currentDir
     */
    public static String currentDir() {
        currentDir = System.getProperty("user.dir");
        return currentDir;
    }
    
    /**
     * Este metodo permite obtener el classPath de la aplicacion, y ademas retorna el directorio padre desde donde
     * se esta ejecutando el sistema.
     * 
     * @return String paternDirectory
     */
    public static String getClassPath() throws IOException {
        currentDir = System.getProperty("java.class.path");
        System.out.println(currentDir);
        file = new File(currentDir);
        paternDirectory = file.getParent();
        System.out.println(paternDirectory);
        return paternDirectory;
    }

    /**
     * Este metodo permite convertir un objeto de tipo String a un objeto de tipo Date en el forma,usa la interfaz
     * SimpleDateFormat y parsea la fecha en String a un formato Date del tipo dd-mm-yyyy que será retornado a
     * quien hace el llamado.
     * 
     * @param fecha
     * @return String parseString
     * @throws ParseException 
     */
    public static Date parseStringToDate(String fecha) throws ParseException {        
            format = new SimpleDateFormat("dd-mm-yyyy");
            date=format.parse(fecha);        
        return date;
    }

    /**
     * este metodo permite convertir un objeto del tipo Java.util.Date a un formato representativo Date Mysql
     * String, es usado para el registro de datos del tipo Date dentro de una entidad Mysql, recibe como parametro 
     * el objeto Date a convertir y retorna un Objeto Date en formato Mysql
     * 
     * @param ToFormat
     * @return String date
     */
    public static String parseToMysqlStringDate(Date ToFormat) throws ParseException {        
            format = new SimpleDateFormat("yyyy-MM-dd");
            dateFormated = format.format(ToFormat);    
        return dateFormated;
    }
    
    /**
     * este metodo permite agregar meses a una fecha que recibe del sistema, esta
     * suma de meses se hace a traves del API de Calendar que define el manejo de
     * fechas, al hacer el calculo retorna un String con el valor de la nueva
     * fecha en formato de fecha de Mysql
     * 
     * @param months
     * @param inputDate
     * @return String converterdate
     * @throws ParseException 
     */
    public static String sumMonthsToDate(int months,Date inputDate) throws ParseException{
        systemDate=Calendar.getInstance();
        systemDate.setTime(inputDate);
        systemDate.add(Calendar.MONTH, months);
        return parseToMysqlStringDate(date=systemDate.getTime());
    }

    /**
     * Este metodo permite crear un archivo que contendrá un objeto, el archivo será escrito
     * en un directorio del cual se recibe el path, ademas el nombre del archivo es recibido para poder crear
     * el archivo y concatenar el path de directorio, retorna un String con el path del archivo creado, al momento de
     * inicar la creacion verifica si el archiv ya existe, en caso de existir pregunta al usuario si deseea reemplazarlo
     * en un nuevo archivo si el usuario acepta pasa a eliminar el anterior archivo y posteriormente escribe el 
     * nuevo archivo en el directorio.
     * 
     * @param object
     * @param path
     * @param nameFile
     * @return String pathFile
     */
    public static String writeObjectToFile(Object object, String path, String nameFile) throws IOException {
            pathFile=path+"/"+nameFile;
            if(object!=null && path!=null && nameFile!=null){
                   if(verifiExistFile(pathFile)){
                     option = JOptionPane.showConfirmDialog(null, "El archivo: "+pathFile+" ya existe !\n¿ desea reemplazarlo?",GlobalConfigSystem.getAplicationTitle(),JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                     if(option==0){
                            deleteFile(pathFile);
                            file = new File(pathFile);
                            OOS = new ObjectOutputStream(new FileOutputStream(file));
                            OOS.writeObject(object);
                            OOS.close();        
                     }
                   }else{
                       file = new File(pathFile);
                       OOS = new ObjectOutputStream(new FileOutputStream(file));
                       OOS.writeObject(object);
                       OOS.close();  
                   }
            }else{
                JOptionPane.showMessageDialog(null, "El Objeto no se puede crear, los parametros recibidos no son correctos.", GlobalConfigSystem.getAplicationTitle(), JOptionPane.ERROR_MESSAGE);
                pathFile=null;
            }
        return pathFile;
    }

    /**
     * Este metodo permite crear un flujo de salida para escribir nuevos objetos dentro del archivo, abre el flujo del
     * stream y retorna ese flujo para la escritura de los nuevos objetos.
     * @param path
     * @return 
     */
    public static ObjectInputStream readFileObject(String path) throws FileNotFoundException, IOException {        
            OIS = new ObjectInputStream(new FileInputStream(path));       
        return OIS;
    }

    /**
     * 
     * @param Path
     * @return 
     */
    public static ObjectOutputStream getObjectFileToWrite(String Path) {
        try {
            OOS = new ObjectOutputStream(new FileOutputStream(Path));
        } catch (Exception e) {
            System.err.println("Ocurrió un error en NiconLibTools.getObjectFileToWrite():\n" + e);
        }
        return OOS;
    }

    /**
     * 
     * @param url
     * @return 
     */
    public static boolean verifyExistDir(String url) {
        file = new File(url);
        if (file.exists()) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    /**
     * 
     * @param url
     * @return 
     */
    public static boolean verifyAndCreateDir(String url) {
        file = new File(url);
        if (!file.exists()) {
            state = file.mkdir();
        } else {
            state = true;
        }
        return state;
    }

    /**
     * 
     * @param url
     * @return 
     */
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

    /**
     * 
     * @param url
     * @return 
     */
    public static boolean createDirectory(String url) {
        file = new File(url);
        state = file.mkdir();
        return state;
    }

    /**
     * 
     * @param Url
     * @return 
     */
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

    
    
    /**
     * Este metodo permite agregar un evento a un JDialog que permite que el usuario pueda cerrar dicho
     * window al presionar la tecla ESCAPE, este metodo puede ser usado desde cualquier tipo de JDialog
     * e implementa un ActionListener para el manejador de eventos de la ventana, al mismo tiempo
     * a la v¡JDialog le es asignado un registerKeyBoardAction y le es pasado el escAction para cuando el 
     * componente sea el poseedor del foco.
     * 
     * @param windowDialog 
     */
    public static void addEscapeListenerWindowDialog( final JDialog windowDialog) {    
        ActionListener escAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowDialog.dispose();
            }            
        };
        windowDialog.getRootPane().registerKeyboardAction(escAction,
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);        
    }
    
    /**
     * este metodo permite cerrar de forma segura todo el sistema NiconEnterprise
     * 
     * @throws RuntimeException
     */
    public static void closeNiconEnterprise() throws RuntimeException {
        conect=AdminConector.getInstance();
            if(conect.isConected()){
                conect.disconect();
                Runtime.getRuntime().exit(0);
            }                
    }
}