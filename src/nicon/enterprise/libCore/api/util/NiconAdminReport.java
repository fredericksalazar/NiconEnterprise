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

import nicon.enterprise.libCore.api.util.AdminConector;
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

    private JasperPrint     jasperPrint;
    private JasperReport    reporte;
    private JasperViewer    jasperViewer;
    private JRExporter      exportManager;
    private InputStream     inputObject;
    
    private AdminConector       coneccion;
    private Connection      conect;
    private boolean         state;

    /**
     * 
     */
    public NiconAdminReport() {
        coneccion = AdminConector.getInstance();
        conect =  coneccion.getConnectionSGBD();
    }

    public String SetDir() {
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }

    /**
     * Este metodo permite compilar una plantilla de los reportes
     * @param URL
     * @return
     * @throws JRException 
     */
    public JasperPrint compilarReporte(String URL)throws JRException {
        inputObject = getClass().getResourceAsStream(URL);
        reporte =  (JasperReport) JRLoader.loadObject(inputObject);
        jasperPrint = JasperFillManager.fillReport(reporte, null,conect);
        
        return jasperPrint;
    }

    public JasperPrint compilarReporteConParametros(String URL, Map parametros)
            throws JRException {
        this.inputObject = getClass().getResourceAsStream(URL);
        this.reporte = ((JasperReport) JRLoader.loadObject(this.inputObject));
        this.jasperPrint = JasperFillManager.fillReport(this.reporte, parametros, this.conect);

        return this.jasperPrint;
    }

    public void exportarReportePDF(JasperPrint print) {
        try {
            this.exportManager = new JRPdfExporter();
            this.exportManager.setParameter(JRExporterParameter.JASPER_PRINT, print);
            this.exportManager.setParameter(JRExporterParameter.OUTPUT_FILE, new File("./reportes/Lista Clientes.pdf"));
        } catch (Exception e) {
            System.err.println("Ocurrio un error en <b> NiconAdminReport.exportarReportePDF():" + e);
        }
    }

    public void verReporte(JasperPrint jasper) {
        this.jasperViewer = new JasperViewer(jasper, false);
        this.jasperViewer.setTitle(GlobalConfigSystem.getAplicationTitle());
        this.jasperViewer.setAlwaysOnTop(true);
        this.jasperViewer.setVisible(true);
    }

    public boolean servicioImpresion(JasperPrint jasper)
            throws JRException {
        if (jasper != null) {
            this.state = JasperPrintManager.printReport(this.jasperPrint, false);
        }
        return this.state;
    }
}