/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.Sorted;
import db.CBase;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

/**
 *
 * @author учень 1
 * клас відкриття існуючої бази даних. виконує пошук, необхідної бази та додає 
 * її до списку активних
 */
public class OpenDb extends Framer{

    public static JDialog f;

    public static void open() {
        f = new JDialog();
        f.setSize(getSyze(25, 40));
        f.setLocation(NewDb.getPoint());
        Box x = Box.createVerticalBox();
            ArrayList<File> databaseFileList = getFileList();//отримуэмо выдсортований список файлів
            //створюэмо список 
            for(int i = 0; i < databaseFileList.size(); i++){
                if(!ifEmpty(databaseFileList.get(i))){
                    JButton b = new JButton(databaseFileList.get(i).getName());
                        final int a = i;
                        b.setHorizontalAlignment(JButton.LEFT);
                        b.addActionListener((ActionEvent e) -> {
                            try {
                                CBase cb = new CBase(databaseFileList.get(a));
                                activ.put(cb.getName(), cb);
                                f.setVisible(false);
                                OpenDb.openDBlist();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(OpenDb.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                x.add(b);
                }
            }
        JScrollPane sp = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setPreferredSize(OpenDb.getSecondSyze(95));
        f.getContentPane().add(sp);
        f.setVisible(true);
    }

    private static ArrayList<File> getFileList() {
        File f = new File("res" + SLH + "database");
        return Sorted.sortFilesToArrayList(f.listFiles());
    }

    private static boolean ifEmpty(File get) {
        return activ.containsKey(get.getName());
    }
    
}
