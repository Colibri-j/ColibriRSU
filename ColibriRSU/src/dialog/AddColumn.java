/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.Ather;
import colibrirsu.syst.abs.Typeable;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JButton;
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
 *      corect 16.02.2021
 */
public class AddColumn extends Framer implements Typeable{
    public static JDialog d;
    private static JTextField ta;
    private static String type;
    private static String name = "";
    private static String[] types = new String[]{"ціле число", "дробне число", 
                    "речовина", "символ", "строка", "текст", "поточна дата", "зображення", 
                    "файл", "об'єкт", "числовий ідентефікатор", "строковий ідентифікатор",
                    "комбінований ідентифікатор", "функція", "формула", "вираз", 
                    "автоінкремент", "автодекремeнт", "логічне значення", "NULL", "ДАТА", "приховані дані"};
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
                        d.setVisible(false);
                        add();
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
                JButton b = new JButton("Гаразд");
                    b.addActionListener((ActionEvent e) -> {
                        activ.get(readDb).getTables().get(activTab).addColumn(ta.getText(), setType(), setDefoultValue());
                        activ.get(readDb).getTables().get(activTab).setDateOfCorectTable();
                        activ.get(readDb).save();
                        d.setVisible(false);
                        openTable();
                        
                    });
                JButton b0 = new JButton("Відмінити");
                    b0.addActionListener((ActionEvent e) -> {
                        d.setVisible(false);
                    });
            p0.add(b);
            p0.add(b0);
        x.add(p);
        x.add(p0);
        JScrollPane sp = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setPreferredSize(getSecondSyze(95));
        d.getContentPane().add(sp);
        d.setVisible(true);
    }
    
    public static String setDefoultValue(){
        String s = "";
        switch(type){
            case "ціле число" : s = ta1.getText(); break;
            case "дробне число" : s = ta1.getText(); break;
            case "речовина" : s = ta1.getText(); break;
            case "символ" : s = "" + ta1.getText().charAt(0); break;
            case "строка" : s = ta1.getText(); break;
            case "текст" : s = ta1.getText(); break;
            case "поточна дата" : s = Ather.getDatePrint(new Date()); break;
            case "зображення" : s = ""; break;
            case "файл" : s = ""; break;
            case "об'єкт" : s = ""; break;
            case "числовий ідентефікатор" : s = "0"; break;
            case "строковий ідентифікатор" : s = "a"; break;
            case "комбінований ідентифікатор" : s = "00000000"; break;
            case "функція" : s = ta1.getText(); break;
            case "формула" : s = ta1.getText(); break;
            case "вираз" : s = ta1.getText(); break;
            case "автоінкремент" : s = "0"; break;
            case "автодекремeнт" : s = "" + Integer.MAX_VALUE; break;
            case "логічне значення" : s = ta1.getText(); break;
            case "NULL" : s = "null"; break;
            case "ДАТА" : s = ta1.getText(); break;
            case "приховані дані" : s = ta1.getText(); break;
        }
        return s;
    }
    
    public static int setType(){
        int i = 0;
        switch(type){
            case "ціле число" : i = AddColumn.NAMBER; break;
            case "дробне число" : i = AddColumn.DOUBLE; break;
            case "речовина" : i = AddColumn.SUBSTANCE; break;
            case "символ" : i = AddColumn.SYMBOL; break;
            case "строка" : i = AddColumn.STRING; break;
            case "текст" : i = AddColumn.TEXT; break;
            case "поточна дата" : i = AddColumn.THIS_DATE; break;
            case "зображення" : i = AddColumn.IMAGE; break;
            case "файл" : i = AddColumn.FILE; break;
            case "об'єкт" : i = AddColumn.OBJECT; break;
            case "числовий ідентефікатор" : i = AddColumn.ID; break;
            case "строковий ідентифікатор" : i = AddColumn.STRING_ID; break;
            case "комбінований ідентифікатор" : i = AddColumn.COMBINED_ID; break;
            case "функція" : i = AddColumn.FUNCTION; break;
            case "формула" : i = AddColumn.FORMULA; break;
            case "вираз" : i = AddColumn.STUTEMENT; break;
            case "автоінкремент" : i = AddColumn.AUTOINC; break;
            case "автодекремeнт" : i = AddColumn.AUTODEC; break;
            case "логічне значення" : i = AddColumn.BOOLEAN; break;
            case "NULL" : i = AddColumn.NULL; break;
            case "ДАТА" : i = AddColumn.DATE; break;
            case "приховані дані" : i = AddColumn.PSW; break;
        }
        return i;
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
        if(type != null){
            if(type.equals("поточна дата") || type.equals("числовий ідентефікатор") 
                    || type.equals("строковий ідентифікатор") || type.equals("комбінований ідентифікатор")
                    || type.equals("зображення") || type.equals("файл")
                    || type.equals("об'єкт") || type.equals("файл")
                    || type.equals("автоінкремент") || type.equals("автодекремeнт") || type.equals("NULL")){
                addVal = false;
                i = 2;
            }
            else{
                addVal = true;
            }
        }
        return i;
    }
    
}
