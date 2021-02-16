/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.abs.Typeable;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

/**
 *
 * @author i++
 * клас видаленя колонки з бази даних
 */
public class RemoveColumn extends Framer implements Typeable{
    public static JDialog d;

    public static void remove() {
        d = new JDialog();
        d.setSize(getSyze(25, 70));
        d.setLocation(getPoint());
        Box x = Box.createVerticalBox();
            for(int i = 0; i < activ.get(readDb).getTables().get(activTab).syze(); i++){
                final int a = i;
                JButton b = new JButton(activ.get(readDb).getTables().get(activTab).getColumnName(i));
                    b.setAlignmentX(JButton.LEFT_ALIGNMENT);
                    b.addActionListener((ActionEvent e) -> {
                        if(isPrimitive(a)){
                            activ.get(readDb).getTables().get(activTab).removeColumn(a);
                            activ.get(readDb).getTables().get(activTab).setDateOfCorectTable();
                            activ.get(readDb).save();
                        }
                        d.setVisible(false);
                        openTable();
                    });
                x.add(b);
            }
        d.getContentPane().add(new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        d.setVisible(true);
    }

    /**
     * метод перевыряэ, чи даны зберыгаються в БД, чи в зовнышных файлах
     */
    private static boolean isPrimitive(int a) {
        int type = activ.get(readDb).getTables().get(activTab).getColumtType(a);
        return type != TEXT || type != IMAGE || type != FILE || type != OBJECT;
    }
    
}
