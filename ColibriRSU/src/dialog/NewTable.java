/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author учень 1
 */
public class NewTable extends Framer{
    private static JDialog informationEntryForm;
    private static JTextArea inputField;
    private static JTextField text;

    public static void setInformation(final String dbName) {
        informationEntryForm = new JDialog();
        informationEntryForm.setSize(getSyze(15, 30));
        informationEntryForm.setLocation(NewDb.getPoint());
        Box workspace = Box.createVerticalBox();
            text = new JTextField("table" + getTableNamber(), 15);
            JPanel p = new JPanel();
                JButton ok = new JButton("гаразд");
                JButton concel = new JButton("відмінити");
                    concel.addActionListener((ActionEvent e) -> {
                        informationEntryForm.setVisible(false);
                    });
            p.add(ok);
            p.add(concel);
        workspace.add(text);
        workspace.add(p);
        JScrollPane sp = new JScrollPane(workspace, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setPreferredSize(OpenDb.getSecondSyze(95));
        informationEntryForm.getContentPane().add(sp);
        informationEntryForm.setVisible(true);
    }

    private static long getTableNamber() {
        long i = -1;
        try {
            Scanner sc = new Scanner(new File("res" + NewDb.SLH + "tbn.txt"));//файл збегiгання поточного номеру бази даних
            i = sc.nextLong();
            i++;
            sc.close();
        } catch (FileNotFoundException ex) {
            i = 0;
        }
        try {
            PrintWriter pw = new PrintWriter("res" + NewDb.SLH + "tbn.txt", "utf-8");
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
