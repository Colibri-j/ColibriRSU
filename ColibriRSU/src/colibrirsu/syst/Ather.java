/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu.syst;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author учень 1
 * Рызны робочы методи
 */
public class Ather {
    
    /**
     * метод порізки строки по заданому символу
     * @param  String sub - строка на порізку
     * @param char c - символ по якому йде порізка строки
     */
    public static String[] cut(String sub, char c) {
        ArrayList<String> as = new ArrayList<String>();
        String s = "";
        for(int i = 0; i < sub.length(); i++){
            if(s.charAt(i) == c){
                as.add(s);
                s = "";
            }
            else{
                s += s.charAt(i);
            }
        }
        return copyStr(as);
    }
    
    /**
     * метод порізки строки на задану довжину символів
     * @param  String sub - строка на порізку
     * @param int size - довжина порізки строки
     */
    public static String[] cut(String sub, int size) {
        ArrayList<String> as = new ArrayList<String>();
        String s = "";
        String[] st = sub.split(" ");
        for(int i = 0; i < st.length; i++){
            s += st[i] + " ";
            if(s.length() >= size){
                as.add(s);
                s = "";
            }
        }
        as.add(s);
        return copyStr(as);
    }
    
    /**
     * метод залишає ліву частину строки до вказаного символа
     * @param  String sub - строка на порізку
     * @param char c - символ по якому йде порізка строки
     */
    public static String cutTo(String sub, char c) {
        String s = "";
        for(int i = 0; i < sub.length(); i++){
            if(sub.charAt(i) == c){
                break;
            }
            else{
                s += sub.charAt(i);
            }
        }
        return s;
    }
    
    /**
     * метод копіювання списку в масив
     */
    public static Object[] copy(ArrayList as) {
        Object[] s = new Object[as.size()];
        for(int i = 0; i < as.size(); i++){
            s[i] = as.get(i);
        }
        return s;
    }
    /**
     * метод копіювання списку в масив
     */
    public static String[] copyStr(ArrayList<String> as) {
        String[] s = new String[as.size()];
        for(int i = 0; i < as.size(); i++){
            s[i] = as.get(i);
        }
        return s;
    }
    
    /**
     * метод копіювання масиву в список
     */
    public static ArrayList copy(Object[] list) {
        ArrayList as = new ArrayList();
        for(Object o : list){
            as.add(o);
        }
        return as;
    }

    /**
     * метод переводить дату в зрозумылий формат: yyyy.mouth.dd-hh:mm:ss
     */
    public static String getDatePrint(Date d) {
        String s = "" + (d.getYear() + 1900) + "." 
                + (d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : (d.getMonth() + 1)) + "."
                + formatAsTwo(d.getDate()) + "-"
                + formatAsTwo(d.getHours()) + ":"
                + formatAsTwo(d.getMinutes()) + ":"
                + formatAsTwo(d.getSeconds());
        return s;
    }

    /**
     * метод перетворює число у формат 04, для двозначних чисел
     */
    public static String formatAsTwo(int i) {
        return i < 10 ? "0" + i : "" + i;
    }

    /**
     * метод залишає праву частину строки від останього входження вказаного символа
     * @param  String s - строка на порізку
     * @param char c - символ по якому йде порізка строки
     */
    public static String cutFrom(String s, char c) {
        String str = "";
        for(int i = s.length() - 1; i >= 0; i--){
            if(c != s.charAt(i)){
                str = s.charAt(i) + str;
            }
            else{
                break;
            }
        }
        return str;
    }

    /**
     * метод перевертаэ масив тстрок з переду назад
     */
    public static String[] invert(String[] std) {
        String[] s = new String[std.length];
        for(int i = 0; i < s.length; i++){
            s[i] = std[std.length - (i + 1)];
        }
        return s;
    }
    
}
