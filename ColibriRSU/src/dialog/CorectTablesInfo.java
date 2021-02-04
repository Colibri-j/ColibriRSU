/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import static colibrirsu.Colibri.activ;
import static colibrirsu.Gui.openDBlist;
import static dialog.Framer.getSyze;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author i++
 * (c) Colibri 2021.04.02
 */
public class CorectTablesInfo extends Framer{
    private static JDialog f;
    private static JTextArea ta;

    public static void corectInformation(String nameTable) {
        f = new JDialog();
        f.setSize(getSyze(25, 40));
        f.setLocation(NewDb.getPoint());
        Box x = Box.createVerticalBox();
            ta = new JTextArea(activ.get(readDb).getTables().get(nameTable).getTablesInform());
                ta.setLineWrap(true);
            JPanel p = new JPanel();
                JButton b = new JButton("гаразд");
                    b.addActionListener((ActionEvent e) -> {
                        activ.get(readDb).getTables().get(nameTable).setTablesInformation(ta.getText());
                        activ.get(readDb).save();
                        openTablesOfDatabase();
                        f.setVisible(false);
                    });
                JButton b1 = new JButton("відмінити");
                    b1.addActionListener((ActionEvent e) -> {
                        f.setVisible(false);
                    });
            p.add(b);
            p.add(b1);
        x.add(ta);
        x.add(p);
        JScrollPane sp = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setPreferredSize(OpenDb.getSecondSyze(95));
        f.getContentPane().add(sp);
        f.setVisible(true);
    }
    
}
