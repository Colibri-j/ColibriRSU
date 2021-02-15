/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.Ather;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author i++
 * Клас створення новоъ колонки в таблицы бази даних
 * (c)Colibri. 15.02.2021 year.
 */
public class AddColumn extends Framer{
    public static JDialog d;
    private static JTextField ta;
    private static String type;
    private static String name = "";
    private static String[] types = new String[]{"ціле число", "дробне число", 
                    "речовина", "символ", "строка", "текст", "дата", "зображення", 
                    "файл", "об'єкт", "числовий ідентефікатор", "строковий ідентифікатор",
                    "комбінований ідентифікатор", "функція", "формула", "вираз", 
                    "автоінкремент", "автодекремeнт", "логічне значення"};
    private static JComboBox cb;
    private static JTextField ta1;
    private static String autoVal = "0";
    private static boolean addVal = true;

    public static void add() {
        d = new JDialog();
        d.setSize(getSyze(50, 25));
        d.setLocation(getPoint());
        Box x = Box.createVerticalBox();
            JPanel p = new JPanel();
                p.setLayout(new GridLayout(2, getNElement(), 1, 1));
                JLabel l = new JLabel("назва");
                JLabel l0 = new JLabel("тип");
                JLabel l1 = new JLabel("значення");
                ta = new JTextField(name, 15);
                cb = new JComboBox(types);
                    cb.addActionListener((ActionEvent e) -> {
                        JComboBox box = (JComboBox)e.getSource();
                        type = (String)box.getSelectedItem();
                        name = ta.getText();
                        typesRedact();//сортуэмо масив
                        //підбираємо значення за замовчуванням
                        autoVal = setAutoValue();
                        //оновлюємо вікно
                    });
                ta1 = new JTextField(autoVal, 15);
            p.add(l);
            p.add(l0);
            if(addVal){
                p.add(l1);
            }
            p.add(ta);
            p.add(cb);
            if(addVal){
                p.add(ta1);
            }
            JPanel p0 = new JPanel();
        x.add(p);
        x.add(p0);
        JScrollPane sp = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setPreferredSize(getSecondSyze(95));
        d.getContentPane().add(sp);
        d.setVisible(true);
    }

    /**
     * метод замынюэ перший елемент масиву на обраний, р а обраний на перший елемент
     */
    private static void typesRedact() {
        if(type != null){
            String s = types[0];
            types[0] = type;
            for(int i = 1; i < types.length; i++){
                if(types[i].equals(type)){
                    types[i] = s;
                    break;
                }
            }
        }
    }

    private static String setAutoValue() {
        String s = "";
        switch(type){
            case "ціле число" : s = "0"; break;
            case "дробне число" : s = "0.0"; break;
            case "логічне значення" : s = "false"; break;
        }
        return s;
    }

    private static int getNElement() {
        int i = 3;
        if(type.equals("дата") || type.equals("числовий ідентефікатор") 
                || type.equals("строковий ідентифікатор") || type.equals("комбінований ідентифікатор")
                || type.equals("автоінкремент") || type.equals("автодекремeнт")){
            addVal = false;
            i = 2;
        }
        else{
            addVal = true;
        }
        return i;
    }
    
}
