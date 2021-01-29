/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu;

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
            Box work = Box.createVerticalBox();
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
        String[] arrayOfDatabases = activ.isEmpty() ? null : activ.keySet().toArray(new String[0]);;
        Arrays.sort(arrayOfDatabases);
        Box x = Box.createVerticalBox();
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
            Box work = Box.createVerticalBox();//панель виведеня та редагування таблицы
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
            JButton newTable = new JButton("Нова таблиця");
                newTable.addActionListener((ActionEvent e) -> {
                    
                });
            //додаэмо ліст
        x.add(headLabel);
        x.add(newTable);
        return x;
    }
        // </editor-fold>

}
