/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore;

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

public class NiconAdminReport {

    private JasperPrint jasperPrint;
    private JasperReport reporte;
    private JasperViewer jasperViewer;
    private JRExporter exportManager;
    private InputStream inputObject;
    private Conection coneccion;
    private Connection conect;
    private boolean state;

    public NiconAdminReport() {
        this.coneccion = Conection.obtenerInstancia();
        this.conect = ((Connection) this.coneccion.obtenerConeccion());
    }

    public String SetDir() {
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }

    public JasperPrint compilarReporte(String URL)
            throws JRException {
        this.inputObject = getClass().getResourceAsStream(URL);
        this.reporte = ((JasperReport) JRLoader.loadObject(this.inputObject));
        this.jasperPrint = JasperFillManager.fillReport(this.reporte, null, this.conect);
        return this.jasperPrint;
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