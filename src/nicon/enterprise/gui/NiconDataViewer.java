/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;

/**
 *
 * @author frederick
 */
public class NiconDataViewer{
    
    private JRViewer viewer;
    private JasperPrint print;
    
    public NiconDataViewer(JasperPrint jasperPrint){
        this.print=jasperPrint;
        init();
    }
    
    private void init(){
        viewer=new JRViewer(print);
        viewer.setBackground(GlobalConfigSystem.getBackgroundAplication());
        viewer.setFont(GlobalConfigSystem.getFontAplicationText());
    }
    
    public JRViewer getViewer(){
        return this.viewer;
    }
    
}
