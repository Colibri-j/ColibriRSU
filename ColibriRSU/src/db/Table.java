/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import colibrirsu.Colibri;
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
    
    public String getName(){
        return name;
    }

    public String getTablesInform() {
        return inf;
    }

    public void setTablesInformation(String text) {
        inf = text;
    }

    public Date getDateOfCreateTable() {
        return dataOfCreate;
    }

    public Date getDataOfCorectTable() {
        return dataOfRedact;
    }

    /**
     * клас опису колонок таблиці
     * @param String name - ім'я колонки
     * @param int type - тип змінної: 
     *      0 - нулл        *      1 - ціле число     *      2 - дробне число
     *      3 - строка      *      4 - id             *      5 - зображення
     *      6 - файл        *      7 - об'єкт         *      8 - дата
     *      9 - text
     */
    protected static class Column{
        private static final long serialVersionUID = -9223372036854775791l;
        String name;//ім'я колонки
        int type;//тип змінної
        ArrayList<String> value = new ArrayList<String>();
        boolean autoAdd;
        int typeAuto;

        public Column(String n, String t, String defoultVal) {
            
        }
    }
    
}
