/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu;

import colibrirsu.syst.Ather;
import db.Table;
import dialog.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author учень 1
 * клас створення графіцного інтерфейсц користувача
 */
public class Gui extends Colibri{

    public static JFrame win;//власне вікно
    public static final Dimension syzeWindow = Toolkit.getDefaultToolkit().getScreenSize();//розмыр вікна підігнаний під розмір екрану
    public static String readDb;
    public static String activTab;
    
    public static void guiStart(){
        win = new JFrame("Colibri runQuest system of Ukraine 1.0.9");
        win.setSize(syzeWindow);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setJMenuBar(getMenuBar());//Створення меню управляння
        win.setVisible(true);
    }

    private static JMenuBar getMenuBar() {
        JMenuBar mb = new JMenuBar();
            JMenu m = new JMenu("файл");
                JMenuItem mi = new JMenuItem("Вийти");
                    mi.addActionListener((ActionEvent e) -> {
                        System.exit(0);
                    });
            m.add(mi);
            JMenu m0 = new JMenu("бази даних");
                JMenuItem mi0 = new JMenuItem("нова db");
                    mi0.addActionListener((ActionEvent e) -> {
                        NewDb.createNewDatabase();
                    });
                JMenuItem mi1 = new JMenuItem("відкрити db");
                    mi1.addActionListener((ActionEvent e) -> {
                        OpenDb.open();
                    });
            m0.add(mi0);
            m0.add(mi1);
        mb.add(m);
        mb.add(m0);
        return mb;
    }
    
    /**
     * метод виведення списку активних (відкритих баз даних)
     */
    // <editor-fold defaultstate="collapsed" desc="openDatabaselist methods. Click on the + sign on the left to edit the code.">
    public static void openDBlist(){
        JPanel databasePanel = new JPanel();//панель роботи з базами даних
            Box areaOfWorkWithDatabases = Box.createVerticalBox();
                JPanel p = new JPanel();
                    Box dbList = getDatabaseList();
                p.add(dbList);
                JPanel pp = new JPanel();
                    Box tabList = readDb != null ? createListOfTables() : Box.createVerticalBox();
                pp.add(tabList);
                JScrollPane sp1 = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp1.setPreferredSize(Framer.getSyze(28, 40));
                JScrollPane sp3 = new JScrollPane(pp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp3.setPreferredSize(Framer.getSyze(28, 54));
            areaOfWorkWithDatabases.add(sp1);
            areaOfWorkWithDatabases.add(sp3);
            Box work = activTab != null ? createTable() : Box.createVerticalBox();
            JScrollPane sp = new JScrollPane(areaOfWorkWithDatabases, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                sp.setPreferredSize(Framer.getSyze(25, 90));;
            JScrollPane sp2 = new JScrollPane(work, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                sp2.setPreferredSize(Framer.getSyze(71, 90));
        databasePanel.add(sp);
        databasePanel.add(sp2);
        win.getContentPane().removeAll();
        win.getContentPane().add(databasePanel);
        win.validate();
    }
    
    private static Box getDatabaseList() {
        String[] arrayOfDatabases = activ.isEmpty() ? null : activ.keySet().toArray(new String[0]);
        Box x = Box.createVerticalBox();
        if(arrayOfDatabases != null){
        Arrays.sort(arrayOfDatabases);
            for(String key : arrayOfDatabases){
                JPanel p = new JPanel();
                final String databaseName = key;
                JButton b = new JButton(activ.get(key).getName());
                    b.addActionListener((ActionEvent e) -> {
                        readDb = activ.get(key).getName();
                        openTablesOfDatabase();
                    });
                JButton b1 = new JButton();
                        b1.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "1.jpg"));
                        if(activ.get(key).getDatabaseInform() != null){
                            b1.addActionListener((ActionEvent e) -> {
                                //выткриваемо діалог редагування опису бази даних
                                CorectDBInfo.corectInformation(databaseName);
                            });
                        }
                        else{
                            b1.addActionListener((ActionEvent e) -> {
                                //выткриваемо діалог введення опису бази даних
                                SetDBInfo.setInformation(databaseName);
                            });
                        }
                JLabel b2 = new JLabel();
                        b2.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "2.jpg"));
                        if(activ.get(key).getDatabaseInform() != null){
                            b2.addMouseListener(new MouseListener() {
                                private JFrame fr;
                                @Override
                                public void mouseClicked(MouseEvent e) {
                        
                    }
                                
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    fr = new JFrame();
                                    fr = new JFrame();
                                    fr.setSize(Framer.getSyze(25, 40));
                                    fr.setLocation(Framer.getPoint());
                                    fr.setUndecorated(true);
                                    fr.setVisible(true);
                                    Box x = Box.createVerticalBox();
                                        JLabel l = new JLabel(activ.get(databaseName).getDatabaseInform());
                                    x.add(l);
                                    JScrollPane sp = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                        sp.setPreferredSize(Framer.getSecondSyze(95));
                                    fr.getContentPane().add(sp);
                                }
                            
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    fr.setVisible(false);
                                }
                            
                                @Override
                                public void mouseEntered(MouseEvent e) {
                        
                    }
                            
                                @Override
                                public void mouseExited(MouseEvent e) {
                        
                    }
                            } );
                        }
                        //выткриваемо діалог введення опису бази даних
                p.add(b);
                p.add(b1);
                p.add(b2);
                x.add(p);
            }
        }
        return x;
    }
    // </editor-fold>

    /**
     * методи виведення списку таблиць активної(обраної) бази даних
     */
    // <editor-fold defaultstate="collapsed" desc="методи виведення списку таблиць. Click on the + sign on the left to edit the code.">
    public static void openTablesOfDatabase(){
        JPanel databasePanel = new JPanel();//панель роботи з базами даних
            Box areaOfWorkWithDatabases = Box.createVerticalBox();
                JPanel p = new JPanel();
                    Box dbList = getDatabaseList();
                p.add(dbList);
                JPanel pp = new JPanel();
                    Box tabList = createListOfTables();
                pp.add(tabList);
                JScrollPane sp1 = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp1.setPreferredSize(Framer.getSyze(28, 40));
                JScrollPane sp3 = new JScrollPane(pp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp3.setPreferredSize(Framer.getSyze(28, 54));
            areaOfWorkWithDatabases.add(sp1);
            areaOfWorkWithDatabases.add(sp3);
            Box work = activTab != null ? createTable() : Box.createVerticalBox();//панель виведеня та редагування таблицы
            JScrollPane sp = new JScrollPane(areaOfWorkWithDatabases, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                sp.setPreferredSize(Framer.getSyze(25, 90));;
            JScrollPane sp2 = new JScrollPane(work, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                sp2.setPreferredSize(Framer.getSyze(71, 90));
        databasePanel.add(sp);
        databasePanel.add(sp2);
        win.getContentPane().removeAll();
        win.getContentPane().add(databasePanel);
        win.validate();
    }

    /**
     * метод генерації списку таблиць та панелі інструментів
     */
    private static Box createListOfTables() {
        Box x = Box.createVerticalBox();
            JLabel headLabel = new JLabel("активна база даних - " + activ.get(readDb).getName());
            JPanel workPane = new JPanel();
                JButton newTable = new JButton();
                    newTable.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "3.jpg"));
                    newTable.setToolTipText("нова таблиця");
                    newTable.addActionListener((ActionEvent e) -> {
                        NewTable.setInformation(readDb);
                    });
                JButton closeDB = new JButton();
                    closeDB.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "6.jpg"));
                    closeDB.setToolTipText("закрити базу даниих");
                    closeDB.addActionListener((ActionEvent e) -> {
                        activ.remove(readDb);
                        readDb = null;
                        activTab = null;
                        openDBlist();
                    });
                JButton cleanDatabase = new JButton();
                    cleanDatabase.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "5.jpg"));
                    cleanDatabase.setToolTipText("очистити базу даних");
                    cleanDatabase.addActionListener((ActionEvent e) -> {
                        
                    });
                JButton deleteDatabase = new JButton();
                    deleteDatabase.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "4.jpg"));
                    deleteDatabase.setToolTipText("видалити базу даних");
                    deleteDatabase.addActionListener((ActionEvent e) -> {
                        
                    });
            workPane.add(newTable);
            workPane.add(closeDB);
            workPane.add(cleanDatabase);
            workPane.add(deleteDatabase);
        x.add(headLabel);
        x.add(workPane);
            //додаэмо ліст
            String[] arrayOfTables = activ.get(readDb).getTables().isEmpty() ? null : 
                    activ.get(readDb).getTables().keySet().toArray(new String[0]);
            if(arrayOfTables != null){
                Arrays.sort(arrayOfTables);
            for(int i = 0; i < arrayOfTables.length; i++){
                final String str = arrayOfTables[i];
                JPanel p = new JPanel();
                    JButton b = new JButton(arrayOfTables[i]);
                        b.addActionListener((ActionEvent e) -> {
                            activTab = str;
                            openTable();
                        });
                    JButton b1 = new JButton();
                        b1.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "1.jpg"));
                        if(activ.get(readDb).getTables().get(arrayOfTables[i]).getTablesInform() != null){
                            b1.addActionListener((ActionEvent e) -> {
                                //выткриваемо діалог редагування опису таблиці
                                CorectTablesInfo.corectInformation(str);
                            });
                        }
                        else{
                            b1.addActionListener((ActionEvent e) -> {
                                //выткриваемо діалог введення опису бази даних
                                SetTableInfo.setInformation(str);
                            });
                        }
                JLabel b2 = new JLabel();
                        b2.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "2.jpg"));
                        if(activ.get(readDb).getTables().get(str).getTablesInform() != null){
                            b2.addMouseListener(new MouseListener() {
                                private JFrame fr;
                                @Override
                                public void mouseClicked(MouseEvent e) {
                        
                    }
                                
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    fr = new JFrame();
                                    fr = new JFrame();
                                    fr.setSize(Framer.getSyze(25, 40));
                                    fr.setLocation(Framer.getPoint());
                                    fr.setUndecorated(true);
                                    fr.setVisible(true);
                                    Box x = Box.createVerticalBox();
                                        JLabel l = new JLabel(activ.get(readDb).getTables().get(str).getTablesInform());
                                    x.add(l);
                                    JScrollPane sp = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                        sp.setPreferredSize(Framer.getSecondSyze(95));
                                    fr.getContentPane().add(sp);
                                }
                            
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    fr.setVisible(false);
                                }
                            
                                @Override
                                public void mouseEntered(MouseEvent e) {
                        
                    }
                            
                                @Override
                                public void mouseExited(MouseEvent e) {
                        
                    }
                            } );
                        }
                p.add(b);
                p.add(b1);
                p.add(b2);
                x.add(p);
            }
            }
        return x;
    }
        // </editor-fold>

    /**
     * методи роботи з таблицею
     */
    // <editor-fold defaultstate="collapsed" desc="методи роботи з таблицею. Click on the + sign on the left to edit the code.">
    public static void openTable() {
        JPanel databasePanel = new JPanel();//панель роботи з базами даних
            Box areaOfWorkWithDatabases = Box.createVerticalBox();
                JPanel p = new JPanel();
                    Box dbList = getDatabaseList();
                p.add(dbList);
                JPanel pp = new JPanel();
                    Box tabList = createListOfTables();
                pp.add(tabList);
                JScrollPane sp1 = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp1.setPreferredSize(Framer.getSyze(28, 40));
                JScrollPane sp3 = new JScrollPane(pp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp3.setPreferredSize(Framer.getSyze(28, 54));
            areaOfWorkWithDatabases.add(sp1);
            areaOfWorkWithDatabases.add(sp3);
            Box work = createTable();//панель виведеня та редагування таблицы
            JScrollPane sp = new JScrollPane(areaOfWorkWithDatabases, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                sp.setPreferredSize(Framer.getSyze(25, 90));;
            JScrollPane sp2 = new JScrollPane(work, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                sp2.setPreferredSize(Framer.getSyze(71, 90));
        databasePanel.add(sp);
        databasePanel.add(sp2);
        win.getContentPane().removeAll();
        win.getContentPane().add(databasePanel);
        win.validate();
    }
    
    private static Box createTable() {
        Box x = Box.createVerticalBox();
            JLabel l = new JLabel(getTableInfo());
            JPanel p = getWorkPane();
        x.add(l);
        return x;
    }
    
    /**
     * метод отримання основних даних про таблицю: назва, дата створення, дата редагування
     */
    private static String getTableInfo(){
        Table t = activ.get(readDb).getTables().get(activTab);
        String s = t.getName() + " : " + Ather.getDatePrint(t.getDateOfCreateTable()) 
                + " / " + Ather.getDatePrint(t.getDataOfCorectTable());
        return s;
    }
    
    private static JPanel getWorkPane() {
        JPanel p = new JPanel();
            JButton b = new JButton();
                b.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "7.jpg"));
                b.setToolTipText("додати колонку");
                b.addActionListener((ActionEvent e) -> {
                    AddColumn.add();
                });
            JButton b0 = new JButton();
                b0.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "8.jpg"));
                b0.setToolTipText("ввести дані");
            JButton b1 = new JButton();
                b1.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "5.jpg"));
                b1.setToolTipText("очистити таблицю");
            JButton b2 = new JButton();
                b2.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "4.jpg"));
                b2.setToolTipText("видалити колонку");
            JButton b3 = new JButton();
                b3.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "9.jpg"));
                b3.setToolTipText("зберегти зміни");
            JButton b4 = new JButton();
                b4.setIcon(new ImageIcon("res" + Gui.SLH + "img" + SLH + "but" + SLH + "6.jpg"));
                b4.setToolTipText("закрити таблицю");
        p.add(b);
        p.add(b0);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        return p;
    }
    // </editor-fold>

}
