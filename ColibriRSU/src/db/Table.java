/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import colibrirsu.syst.abs.Typeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author учень 1
 * клас опису таблиці. містить назву та колонки
 * @param String name - назва таблиці
 * @param ArrayList<Coule> coules - перелік колонок таблиці таблиці зберігаються в файлі бази даних
 */
public class Table implements Serializable{
    private static final long serialVersionUID = -9223372036854775792l;
    private ArrayList<Column> coules = new ArrayList<Column>();
    private String name;//ім'я таблиці
    private String inf;//іопис таблиці
    private Date dataOfCreate;//дата створення
    private Date dataOfRedact;//дата 
    
    public Table(String nameT){
        name = nameT;
        dataOfCreate = new Date();
        dataOfRedact = new Date();
    }
    
    /**
     * метод задає дату останього редагування таблиці
     */
    public void setDateOfCorectTable(){
        dataOfRedact = new Date();
    }
    
    /**
     * метод повертає назву таблиці
     */
    public String getName(){
        return name;
    }

    /**
     * метод повертає інформацію про таблицю
     */
    public String getTablesInform() {
        return inf;
    }

    /**
     * метод задає інформацію про таблицю
     */
    public void setTablesInformation(String text) {
        inf = text;
    }

    /**
     * метод повертає дату створення таблиці
     */
    public Date getDateOfCreateTable() {
        return dataOfCreate;
    }

    /**
     * метод повертає дату редагування таблиці
     */
    public Date getDataOfCorectTable() {
        return dataOfRedact;
    }
    
    /**
     * метод створює нову колонку в таблиці
     */
    public void addColumn(String name, int type, String defoultValue) {
        coules.add(new Column(name, type, defoultValue));
    }
    
    /**
     * метод повертає кількість колонок в таблиці
     */
    public int syze(){
        return coules.size();
    }

    /**
     * метод повертає кількість елементів в колонці таблиці
     */
    public int columnLength() {
        return coules.size() > 0 ? coules.get(0).length() : 0;
    }

    /**
     * метод повертає назву заданої колонки
     */
    public String getColumnName(int a) {
        return coules.get(a).getName();
    }

    /**
     * метод повертає значення заданого елементу
     */
    public String getValue(int a, int i) {
        return coules.get(a).getValue(i);
    }

    /**
     * метод видаляє вказану колонку
     */
    public void removeColumn(int a) {
        coules.remove(a);
    }

    /**
     * метод повертає тип вказаної колонки
     */
    public int getColumtType(int a) {
        return coules.get(a).type;
    }

    /**
     * метод повертає значення за замовчуванням
     */
    public String getDefoltValue(int a) {
        return coules.get(a).def;
    }

    /**
     * метод збільшуе значення за замовчуванням на 1, і це значеня потім має бути передате в блок значень.
     */
    public void autoInc(int i) {
        coules.get(i).autoInc();
    }

    /**
     * метод збільшуе значення строкового ыдентифыкатора на 1, і це значеня потім має бути передате в блок значень.
     */
    public void autoStringInc(int i) {
        coules.get(i).autoStringIncrement();
    }
    
    /**
     * метод збільшуе значення комбінованого ыдентифыкатора на 1, і це значеня потім має бути передате в блок значень.
     */
    public void autoCombiInc(int i) {
        coules.get(i).autoCombiIncrement();
    }

    /**
     * метод зменшує значення  на 1, і це значеня потім має бути передате в блок значень.
     */
    public void autoDec(int i) {
        coules.get(i).autoDecrement();
    }

    /**
     * клас опису колонок таблиці
     * @param String name - ім'я колонки
     * @param int type - тип змінної? перелічені в інтерфейсі Typeable: 
     *      
     */
    protected static class Column implements Typeable, Serializable{
        private static final long serialVersionUID = -9223372036854775791l;
        String name;//ім'я колонки
        int type;//тип змінної
        ArrayList<String> value = new ArrayList<String>();
        String def;//значеня за замовчуванням

        public Column(String n, int t, String defoultVal) {
            name = n;
            type = t;
            def = !defoultVal.equals("") || !defoultVal.equals(" ") || defoultVal != null ? defoultVal : null;
        }

        private int length() {
            return value.size();
        }

        private String getName() {
            return name;
        }

        private String getValue(int i) {
            return value.get(i);
        }

        private void autoInc() {
            def = "" + (Integer.parseInt(def) + 1);
        }

        private void autoStringIncrement() {
            String s = "";
            boolean inc = true;
            boolean is = false;
            for(int i = def.length() - 1; i >= 0; i--){
                if(inc){
                    if(def.charAt(i) < 'z'){
                        s = (def.charAt(i) + 1) + s;
                        inc = false;
                        is = true;
                    }
                    else{
                        s = 'A' + s;
                        is = false;
                    }
                    if(is){
                        s = 'A' + s;
                    }
                }
                else{
                    s = def.charAt(i) + s;
                }
            }
            def = s;
        }

        private void autoCombiIncrement() {
            String s = "";
            boolean inc = true;
            boolean is = false;
            for(int i = def.length() - 1; i >= 0; i--){
                if(inc){
                    if(def.charAt(i) < 'z'){
                        s = (def.charAt(i) + 1) + s;
                        inc = false;
                        is = true;
                    }
                    else{
                        s = '0' + s;
                        is = false;
                    }
                    if(is){
                        s = '0' + s;
                    }
                }
                else{
                    s = def.charAt(i) + s;
                }
            }
            def = s;
        }

        private void autoDecrement() {
            def = "" + (Integer.parseInt(def) - 1);
        }
    }
    
}
