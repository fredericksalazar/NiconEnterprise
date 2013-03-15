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

import com.mysql.jdbc.Connection;

import java.io.File;
import java.io.InputStream;

import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Esta clase NiconAdminReport es el gestor central para la generacion de reportes de la informacion del
 * sistema, hace uso de las librerias JasperReport para poder gestionar la generacion y compilacion de los
 * reportes del sistema.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class NiconAdminReport {
    
    private static final String URL_EXPORT_FILES_REPORT="./Archivos/Reportes";
    
    private JasperPrint     jasperPrint;
    private JasperReport    jasperReport;
    private JasperViewer    jasperViewer;
    private JRExporter      exportManager;
    private InputStream     inputObject;
    
    private AdminConector   conection;
    private Connection      conect;
    private boolean         state;

    /**
     * Esta clase es la administradora central para la gestion e immpresion
     * de reportes con informacion obtenida de la fuente de datos, AdminReport
     * define los metodos necesarios para exportar toda la informacion contenida
     * en la fuente de datos a distinto tipo de documentos entre los que se
     * encuentran archivos, pdf, doc, xls, cvs etc.
     */
    public NiconAdminReport() {
        conection = AdminConector.getInstance();
        conect =  conection.getConnectionSGBD();
    }
    
    public String setDir() {
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }

    /**
     * Este metodo permite cargar y compilar un archivo .Jasper y convertirlo
     * en un objeto JasperReport, este archivo JasperReportes retornado a la
     * clase que solicita la compilacion y carga del archivo.
     * 
     * @param URL
     * @return JasperPrint jasperPrint
     * @throws JRException 
     */
    public JasperPrint buildReport(String URL)throws JRException {
        inputObject = getClass().getResourceAsStream(URL);
        jasperReport =  (JasperReport) JRLoader.loadObject(inputObject);
        jasperPrint = JasperFillManager.fillReport(jasperReport, null,conect);        
        return jasperPrint;
    }

    /**
     * Este metodo permite cargar y compilar un archivo .jasper y convertirlo
     * en un objeto JasperReport, a diferencia del anterior metodo este 
     * cargar reportes que recibe parametros como claves, idpersonales, o las 
     * denominadas Primary Key que permite obtener informacion de un registro de
     * ntro de una fuente de datos Mysql, al obtener y cargar el archivo hace
     * un llenado con los datos y el Map que es pasado como parametro lo cual
     * retorna un objeto JasperPrint listo para ser usado por el usuario.
     * 
     * @param URL
     * @param parameters
     * @return JasperPrint jasperPrint
     * @throws JRException 
     */
    public JasperPrint buildReportParameter(String URL, Map parameters) throws JRException {
        inputObject = getClass().getResourceAsStream(URL);
        jasperReport = (JasperReport) JRLoader.loadObject(inputObject);
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,conect);
        return jasperPrint;
    }

    /**
     * Este metodo permite guardar un archivo JasperPrint con datos y generar
     * un archivo .pdf que será almacenado dentro del disco duro y dentro de 
     * el sistema de archivos de NiconEnterprise, recibe como parametros el
     * archivo JasperPrint que contiene todos los datos y que ha sido compilado
     * previamente.
     * 
     * @param print 
     */
    public void saveReportToPDF(JasperPrint print,String directory,String nameFile) {        
            exportManager = new JRPdfExporter();
            exportManager.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exportManager.setParameter(JRExporterParameter.OUTPUT_FILE, new File("./reportes/Lista Clientes.pdf"));
    }

    /**
     * este metodo permite visualizar el contenido de un archivo JasperPrint 
     * que ha sido compilado y llenado con datos en un visor empotrado 
     * @param jasper 
     */
    public void viewerReport(JasperPrint jasper) {
        jasperViewer = new JasperViewer(jasper,false);
        jasperViewer.setTitle(GlobalConfigSystem.getAplicationTitle());
        jasperViewer.setAlwaysOnTop(true);
        jasperViewer.setVisible(true);
    }

    /**
     * 
     * @param jasper
     * @return
     * @throws JRException 
     */
    public boolean printerReport(JasperPrint jasper)throws JRException {        
            state = JasperPrintManager.printReport(jasperPrint, false);        
        return state;
    }
}