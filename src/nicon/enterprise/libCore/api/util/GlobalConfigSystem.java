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

import java.awt.Color;
import java.awt.Font;

public class GlobalConfigSystem {
   
    public static final String URL_FILES="./Archivos";

    private static Color backgroundAplicationFrame;
    private static Color backgroundAplicationPanel;
    private static Color ForegroundAplicationTitle;
    private static Color ForegroundAplicationText;
    private static Color rowSelectedTable;
    private static Color colorActiveStatus;
    private static Color colorInactiveStatus;
    private static Font FontAplicationTitle;
    private static Font FontAplicationText;
    private static Font FontAplicationTextItalic;
    private static Font FontAplicationTextBold;
    private static Font FontAplicationButton;
    private static Font FontAplicationSubTitle;
    private static Font FontAplicationName;
    private static String AplicationTitle;
    private static String iconsPath;
    private static String exportationPath;
    private static String expPathPDF;

    public GlobalConfigSystem() {
        System.out.println("Iniciando todas las configuraciones del sistema ...");
        backgroundAplicationFrame = new Color(29, 28, 28);
        backgroundAplicationPanel = new Color(56, 56, 56);
        ForegroundAplicationTitle = new Color(0, 177, 221);
        ForegroundAplicationText = new Color(217, 217, 217);
        rowSelectedTable = new Color(35, 35, 35);
        colorActiveStatus = new Color(63, 108, 0);
        colorInactiveStatus = new Color(149, 0, 0);

        FontAplicationTitle = new Font("Ubuntu", 0, 40);
        FontAplicationText = new Font("Ubuntu", 0, 15);
        FontAplicationTextItalic = new Font("Ubuntu", 2, 13);
        FontAplicationTextBold = new Font("Ubuntu", 1, 15);
        FontAplicationButton = new Font("Ubuntu", 1, 10);
        FontAplicationSubTitle = new Font("Ubuntu", 0, 18);
        FontAplicationName = new Font("Ubuntu", 0, 37);

        AplicationTitle = "NiconEnterprise V 0.1.55";
        iconsPath = "/nicon/enterprise/gui/Images/";
        exportationPath = "./Archivos";
        expPathPDF = "./Pdf";
    }

    public static Color getBackgroundAplication() {
        return backgroundAplicationFrame;
    }

    public static Color getBackgroundAplicationPanel() {
        return backgroundAplicationPanel;
    }

    public static Font getFontAplicationTitle() {
        return FontAplicationTitle;
    }

    public static Color getForegroundAplicationTitle() {
        return ForegroundAplicationTitle;
    }

    public static Color getColorActiveStatus() {
        return colorActiveStatus;
    }

    public static Color getColorInactiveStatus() {
        return colorInactiveStatus;
    }

    public static Font getFontAplicationText() {
        return FontAplicationText;
    }

    public static Color getForegrounAplicationText() {
        return ForegroundAplicationText;
    }

    public static String getAplicationTitle() {
        return AplicationTitle;
    }

    public static Font getFontAplicationTextBold() {
        return FontAplicationTextBold;
    }

    public static Font getFontAplicationButton() {
        return FontAplicationButton;
    }

    public static Color getrowSelectedTable() {
        return rowSelectedTable;
    }

    public static Color getForegroundAplicationText() {
        return ForegroundAplicationText;
    }

    public static Color getBackgroundAplicationFrame() {
        return backgroundAplicationFrame;
    }

    public static String getIconsPath() {
        return iconsPath;
    }

    public static String getExpPathPDF() {
        return expPathPDF;
    }

    public static String getExportationPath() {
        return exportationPath;
    }

    public static Font getFontAplicationTextItalic() {
        return FontAplicationTextItalic;
    }

    public static Font getFontAplicationSubTitle() {
        return FontAplicationSubTitle;
    }

    public static Font getFontAplicationName() {
        return FontAplicationName;
    }
}