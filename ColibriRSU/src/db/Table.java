/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import colibrirsu.Colibri;
import java.io.Serializable;
import java.util.ArrayList;

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
    private String inf;//ім'я таблиці
    
    public Table(String nameT){
        name = nameT;
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

    /**
     * клас опису колонок таблиці
     * @param String name - ім'я колонки
     * @param String type - тип змінної: 
     *      int - ціле число
     *      real - дробне число
     *      boolean - логічнак величина
     *      str - строка
     */
    protected static class Column{
        private static final long serialVersionUID = -9223372036854775791l;
        String name;//ім'я колонки
        String type;//тип змінної
        String value;

        public Column() {
            
        }
    }
    
}
