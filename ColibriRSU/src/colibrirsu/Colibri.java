/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu;

import db.CBase;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author учень 1
 */
public class Colibri {
    public static final String SLH = getSlash();//роздільник файлової системи
    public static Map<String, CBase> activ = new HashMap<String, CBase>();//активні бази даних

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gui.guiStart();
    }

    private static String getSlash() {
        File f = new File("/home");
        return f.exists() ? "/" : "\\";
    }
    
}
