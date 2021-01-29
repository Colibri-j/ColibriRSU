/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import db.CBase;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author учень 1
 */
public class NewDb extends Framer{

    private static JDialog f;
    private static JTextField tf;
    private static String s = "base" + getDataNamber();

    public static void createNewDatabase() {
        f = new JDialog();
        f.setSize(getSyze(25));
        f.setLocation(NewDb.getPoint());
        //f.setUndecorated(true);
        Box x = Box.createVerticalBox();
            JPanel p = new JPanel();
                tf = new JTextField(s, 15);
                JButton b = new JButton("ok");
                    b.addActionListener((ActionEvent e) -> {
                        String name = tf.getText();
                        //перевіряємо чи існує база даних з таким іменем
                        File f = new File("res" + NewDb.SLH + "database" + NewDb.SLH + name + ".cdb");
                        if(f.exists()){
                            JLabel l = new JLabel("база даних " + name + " вже існує");
                        }
                        else{
                            //створюємо нову базу даних
                            CBase cb = new CBase(name.trim());
                            activ.put(cb.getName(), cb);
                            //виводимо список активних баз даних
                            NewDb.openDBlist();
                            NewDb.f.setVisible(false);
                        }
                    });
                JButton b1 = new JButton("відмінити");
                    b1.addActionListener((ActionEvent e) -> {
                        f.setVisible(false);
                    });
            p.add(tf);
            p.add(b);
            p.add(b1);
        x.add(p);
        f.getContentPane().add(x);
        f.setVisible(true);
    }

    private static long getDataNamber() {
        long i = -1;
        try {
            Scanner sc = new Scanner(new File("res" + NewDb.SLH + "dbn.txt"));//файл збегiгання поточного номеру бази даних
            i = sc.nextLong();
            i++;
            sc.close();
        } catch (FileNotFoundException ex) {
            i = 0;
        }
        try {
            PrintWriter pw = new PrintWriter("res" + NewDb.SLH + "dbn.txt", "utf-8");
            pw.print(i);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    
    
}
