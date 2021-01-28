/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package сolibriVM;

import colibrirsu.Colibri;
import colibrirsu.syst.Ather;
import db.CBase;
import db.Table;
import java.util.ArrayList;

/**
 *
 * @author учень 1
 * Клас виконавець команд Сolibri та інтерпретації її текстової прозмітки
 *      команди Сolibri
 *      ~db::name; // Створюэ нову базу даних. якщо база з таким іменев вже існує, відкриває її для роботи
 *      ~nameDB.tab::name; - створює нову таблицю
 *      ~nameDB.col::name, type, value;//створюэ колонку таблиці
 *      #nameDB::save; - збереження бази даних
 */
public class CRunner extends Colibri{
    
    /**
     * Метод виконання команд
     * @param String s, команда, повина закінчуватися ;
     */
    public static void run(String s){
        s = s.trim();
        try{
        s = s.substring(0, s.indexOf(";"));
        }catch(StringIndexOutOfBoundsException ex){
            System.err.println("Невірний синтаксис команди");
        }
        //визначаэмо перший символ команди:
        char c = s.charAt(0);
        if(c == '~'){
            //Створення нового об'єкту, його відкриття та робота з ним
            String[] st = s.split("::");//відділяємо тип обэкту від його назви, або передаваних в об'экт параметрів
            if(st[0].trim().substring(1).equals("db")){
                //Створюємо нову базу данних
                createDataBase(st[1].trim());
            }
            else if(st[0].contains(".")){
                //робота із внутрішніми складовими об'єкту
                String[] str = Ather.cut(st[0].trim().substring(1), '.');//розрызаємо на назву юд та команду
                if(str[1].trim().equals("tab")){
                    Table t = new Table(st[1].trim());
                    activ.get(str[0].trim()).addTable(t);
                }
                
            }
        }
        else if(c == '#'){
            //робота з об'єктами
            String[] st = s.split("::");
            if(st[1].trim().equals("save")){
                //збереження бази даних
                activ.get(st[0].trim().substring(1)).save();
            }
        }
    }

    /**
     * метод створення нової бази даних
     * @param String trim - ім'я бази даних
     */
    private static void createDataBase(String trim) {
        CBase cb = new CBase(trim);//створюємо базу даних
        activ.put(trim, cb);//Додаємо базу даних в список активних
    }
    
}
