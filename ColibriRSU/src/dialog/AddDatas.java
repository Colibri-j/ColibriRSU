/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.Ather;
import colibrirsu.syst.abs.Typeable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author i++
 * клас додавання даних в таблицю бази даних
 * (c) Colibri 16.02.2021
 */
public class AddDatas extends Framer implements Typeable{
    public static JDialog d;
    static Map<Integer, JTextField> lines = new HashMap<Integer, JTextField>();
    static Map<Integer, JTextArea> area = new HashMap<Integer, JTextArea>();

    public static void add() {
        d = new JDialog();
        d.setSize(getSyze(80, 25));
        d.setLocation(getPoint());
        d.getContentPane().add(work());
        d.setVisible(true);
    }


    private static Box work() {
        String[] values = new String[activ.get(readDb).getTables().get(activTab).syze()];
        Box x = Box.createVerticalBox();
            JPanel p = new JPanel();
                for(int i = 0; i < values.length; i++){
                    switch(activ.get(readDb).getTables().get(activTab).getColumtType(i)){
                        case NAMBER : 
                            values[i] = activ.get(readDb).getTables().get(activTab).getDefoltValue(i) != null 
                                    ? activ.get(readDb).getTables().get(activTab).getDefoltValue(i) : "0";
                            lines.put(i, new JTextField(values[i], 10));
                            p.add(lines.get(i));
                            break;
                        case DOUBLE : 
                            values[i] = activ.get(readDb).getTables().get(activTab).getDefoltValue(i) != null 
                                    ? activ.get(readDb).getTables().get(activTab).getDefoltValue(i) :"0.0";
                            lines.put(i, new JTextField(values[i], 10));
                            p.add(lines.get(i));
                            break;
                        case SYMBOL : 
                            values[i] = activ.get(readDb).getTables().get(activTab).getDefoltValue(i) != null 
                                    ? activ.get(readDb).getTables().get(activTab).getDefoltValue(i) : "";
                            lines.put(i, new JTextField(values[i], 2));
                            p.add(lines.get(i));
                            break;
                        case STRING : 
                            values[i] = activ.get(readDb).getTables().get(activTab).getDefoltValue(i) != null 
                                    ? activ.get(readDb).getTables().get(activTab).getDefoltValue(i) : "";
                            lines.put(i, new JTextField(values[i], 15));
                            p.add(lines.get(i));
                            break;
                        case PSW : 
                            values[i] = activ.get(readDb).getTables().get(activTab).getDefoltValue(i) != null 
                                    ? activ.get(readDb).getTables().get(activTab).getDefoltValue(i) : "pasword";
                            JPasswordField pf = new JPasswordField(values[i], 15);
                                pf.setEchoChar('*');
                            lines.put(i, pf);
                            p.add(lines.get(i));
                            break;
                        case DATE : 
                            values[i] = activ.get(readDb).getTables().get(activTab).getDefoltValue(i) != null 
                                    ? activ.get(readDb).getTables().get(activTab).getDefoltValue(i) : Ather.getDatePrint(new Date());
                            lines.put(i, new JTextField(values[i], 15));
                            p.add(lines.get(i));
                            break;
                        case NULL : 
                            //NULL
                            values[i] = null;
                            p.add(new JLabel("null"));
                            break;
                        case THIS_DATE : 
                            //NULL
                            values[i] = Ather.getDatePrint(new Date());
                            p.add(new JLabel(values[i]));
                            break;
                        case TEXT : 
                            //NULL
                            values[i] = "";
                            area.put(i, new JTextArea(40, 40));
                            area.get(i).setLineWrap(true);
                            area.get(i).setWrapStyleWord(true);
                            p.add(new JScrollPane(area.get(i)));
                            break;
                    }
                }
            JPanel p1 = new JPanel();
        x.add(p);
        x.add(p1);
        return x;
    }
    
}
