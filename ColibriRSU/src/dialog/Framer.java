/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.Gui;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author учень 1
 * клас управлыння діалоговими вікнами
 */
public class Framer extends Gui{
    static Dimension d;
    
    /**
     * метод вираховує розміри вікна, відносно розмірів екрану
     * @param int proc - відсоток выд розмырыв екрану
     * @return - розмыр вікни
     */
    public static Dimension getSyze(int proc){
        d = new Dimension(Framer.syzeWindow.width * proc / 100, Framer.syzeWindow.height * proc / 100);
        return d;
    }
    
    /**
     * метод вираховує розміри компонента вікна, відносно розмірів екрану
     * @param int proc - відсоток від ширинив екрану
     * @param int prc - відсоток від висоти
     * @return - розмыр вікни
     */
    public static Dimension getSyze(int proc, int prc){
        d = new Dimension(Framer.syzeWindow.width * proc / 100, Framer.syzeWindow.height * prc / 100);
        return d;
    }
    
    /**
     * метод вираховує розміри компонента вікна, відносно його екрану
     * @param int proc - відсоток від ширинив екрану
     * @param int prc - відсоток від висоти
     * @return - розмыр вікни
     */
    public static Dimension getSecondSyze(int proc, int prc){
        d = new Dimension(d.width * proc / 100, d.height * prc / 100);
        return d;
    }
    
    /**
     * метод вираховує розміри компонента вікна, відносно його екрану
     * @param int proc - відсоток від ширинив екрану
     * @param int prc - відсоток від висоти
     * @return - розмыр вікни
     */
    public static Dimension getSecondSyze(int proc){
        d = new Dimension(d.width * proc / 100, d.height * proc / 100);
        return d;
    }
    
    /**
     * метод вираховує положення діалогового вікна в центрі єкрану, і залежності 
     * від розмірів вікна, знайдених за допомогою методу Dimension getSyze(int proc)
     */
    public static Point getPoint(){
        return new Point(Framer.syzeWindow.width / 2 - d.width / 2, Framer.syzeWindow.height / 2 - d.height / 2);
    }
    
}
