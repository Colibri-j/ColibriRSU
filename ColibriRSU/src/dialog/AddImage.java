/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.Ather;
import colibrirsu.syst.Sorted;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author i++
 * клас додавання шляху файлу
 */
public class AddImage extends AddDatas{
    static String path = getPath();
    private static JDialog da;
    private static String[] std;

    /**
     * метод вибору шляху до необхідного файлу
     */
    public static void browsePath(final int i) {
        da = new JDialog();
        da.setSize(getSyze(30, 50));
        da.setLocation(getPoint());
        da.getContentPane().add(setWork(i));
        da.setVisible(true);
    }

    private static String getPath() {
        File f = new File("res" + SLH + "aiPath.txt");
        String s = "";
        if(f.exists()){}
        else{
            f = new File("/home");
            if(f.exists()){
                s = "/home";
            }
            else{
                s = "C:";
            }
        }
        return s;
    }

    private static Box setWork(final int index) {
        File f = new File(path);
        File[] ff = f.listFiles();//Отримуємо список файлів
        ArrayList<File> dirs = Sorted.sortFilesToArrayList(getAllDirs(ff));//вибираємо всі каталоги
        ArrayList<File> files = Sorted.sortFilesToArrayList(getImgFiles(ff));//вибираємо файли зображень(jpg, png, gif, bmp)
        Box x = Box.createVerticalBox();
            //директорія вибору
            std = f.getAbsolutePath().split("\\");
            std = Ather.invert(std);
            JComboBox cb = new JComboBox(std);
                cb.addActionListener((ActionEvent e) -> {
                    JComboBox box = (JComboBox)e.getSource();
                    String dir = (String)box.getSelectedItem();
                    int ind = 0;
                    //отримуэмо індекс обраного елементу в масиві
                    for(int i = 0; i < std.length; i++){
                        if(dir.equals(std[i])){
                            ind = i;
                            break;
                        }
                    }
                    //обрызамо масив выд знайденого ындексу
                    String[] stdt = new String[std.length - ind];
                    for(int i = 0; i < stdt.length; i++){
                        stdt[i] = std[i + ind];
                    }
                    std = stdt;
                    //обрізаємо path
                    String s = "";
                    for(int i = std.length - 1; i >= 0; i--){
                        s += i > 0 ? std[i] + SLH : std[i];
                    }
                    //перевантажуємо вікно
                    revalidate(index);
                });
        x.add(cb);
            JPanel p = new JPanel();
                Box x0 = Box.createVerticalBox();
                //поле директорій та файлів
                    for(int i = 0; i < dirs.size(); i++){
                        //директорії
                        final JPanel p0 = new JPanel();
                            p0.setBackground(new Color(255, 255, 240));
                            JLabel l = new JLabel();
                                l.setIcon(new ImageIcon("res" + SLH + "img" + SLH + "but" + SLH + "4.jpg"));
                            JLabel l0 = new JLabel(dirs.get(i).getName());
                        p0.add(l);
                        p0.add(l0);
                            p0.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {                            }

                            @Override
                            public void mousePressed(MouseEvent e) {                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                p0.setBackground(new Color(220, 220, 220));
                                p0.revalidate();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                p0.setBackground(new Color(255, 255, 240));
                                p0.revalidate();
                            }
                        });
                        x0.add(p0);
                    }
                    for(int i = 0; i < files.size(); i++){
                        //файли
                        final JPanel p0 = new JPanel();
                            p0.setBackground(new Color(255, 255, 240));
                            JLabel l = new JLabel();
                                l.setIcon(new ImageIcon("res" + SLH + "img" + SLH + "but" + SLH + "4.jpg"));
                            JLabel l0 = new JLabel(dirs.get(i).getName());
                        p0.add(l);
                        p0.add(l0);
                            p0.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {                            }

                            @Override
                            public void mousePressed(MouseEvent e) {                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                p0.setBackground(new Color(216, 191, 216));
                                p0.revalidate();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                p0.setBackground(new Color(255, 255, 240));
                                p0.revalidate();
                            }
                        });
                        x0.add(p0);
                    }
            p.add(new JScrollPane(x0));
        x.add(p);
            //кнопка відміни
            JButton b = new JButton("Відмінити");
                b.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        x.add(b);
        return x;
    }

    private static File[] getAllDirs(File[] ff) {
        ArrayList<File> asf = new ArrayList<File>();
        for(File f : ff){
            if(f.isDirectory()){
                asf.add(f);
            }
        }
        File[] f = new File[asf.size()];
        for(int i = 0; i < asf.size(); i++){
            f[i] = asf.get(i);
        }
        return f;
    }

    private static File[] getImgFiles(File[] ff) {
        ArrayList<File> asf = new ArrayList<File>();
        for(File f : ff){
            if(f.isFile()){
                String name = Ather.cutFrom(f.getName().trim(), '.');
                if(name.equals("jpg") || name.equals("png") || name.equals("gif") || name.equals("bmp")){
                    asf.add(f);
                }
            }
        }
        File[] f = new File[asf.size()];
        for(int i = 0; i < asf.size(); i++){
            f[i] = asf.get(i);
        }
        return f;
    }

    private static void revalidate(int i) {
        da.getContentPane().removeAll();
        da.getContentPane().add(setWork(i));
        da.validate();
    }
    
}
