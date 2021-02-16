/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.abs.Typeable;
import javax.swing.JDialog;

/**
 *
 * @author i++
 * клас додавання даних в таблицю бази даних
 * (c) Colibri 16.02.2021
 */
public class AddDatas extends Framer implements Typeable{
    public static JDialog d;

    public static void add() {
        d = new JDialog();
        d.setSize(getSyze(80, 25));
        d.setLocation(getPoint());
        d.setVisible(true);
    }
    
}
