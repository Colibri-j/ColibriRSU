/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import colibrirsu.Colibri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author учень 1
 * Клас створеннЯ бази дыних. базак даних мыстить в собы набыр таблиць та почилань на них
 * @param Map<String, Table> tables - Список даблиць базиданих об'єдниній за принципом - назва таблиці - посилання на таблицю
 * бази даних зберігаються за ажресою res/database/*.cdb
 */
public class CBase implements Serializable{
    private static final long serialVersionUID = -9223372036854775793l;
    public final String SLH = Colibri.SLH;//роздільник файлової системи
    private Map<String, Table> tables = new HashMap<String, Table>();
    private String name;
    private final File f;
    private String inf;
    
    public CBase(String nameDb){
        f = new File("res" + this.SLH + "database" + this.SLH + nameDb + ".cdb");
        if(f.exists()){
            
        }
        else{
            //база выдсутня
            name = nameDb;
            save();
            
        }
    }
    
    public CBase(File file) throws FileNotFoundException{
        f = file;
        if(f.exists()){
            try {
                //така база даних вже існує
                FileInputStream fis = new FileInputStream(f.getPath());
                try {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    try {
                        CBase cb = (CBase) ois.readObject();
                        name = cb.getName();
                        inf = cb.getDatabaseInform();
                        tables = cb.getTables();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            throw new FileNotFoundException();
        }
    }
    
    /**
     * Додаэ таблицю до бази даних
     */
    public void addTable(Table t){
        tables.put(t.getName(), t);
    }
    
    public String getName(){
        return name;
    }
    
    public Map<String, Table> getTables(){
        return tables;
    }

    public void save() {
        try {
                FileOutputStream fos = new FileOutputStream(f.getPath());
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(this);
                } catch (IOException ex) {
                    Logger.getLogger(CBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CBase.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public String getDatabaseInform() {
        return inf;
    }

    public void setDatabaseInformation(String st) {
        inf = st;
    }

    public boolean conteinsTable(String tableName) {
        return tables.containsKey(tableName);
    }
    
}
