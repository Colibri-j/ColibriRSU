/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu.syst.abs;

/**
 *
 * @author i++
 * (c) Colibri 16.02.2021
 */
public interface Typeable {
    public static final int NAMBER = 1; //ціле число+
    public static final int DOUBLE = 2; //дробне число+
    public static final int NULL = 0; //NULL+
    public static final int SUBSTANCE = 3; //речовина?-
    public static final int SYMBOL = 4; //символ+
    public static final int STRING = 5; //строка+
    public static final int TEXT = 6; //текст+
    public static final int THIS_DATE = 7; //поточна дата"+
    public static final int IMAGE = 8; //зображення
    public static final int FILE = 9; //файл
    public static final int OBJECT = 10; //об'єкт
    public static final int ID = 11; //числовий ідентефікатор
    public static final int STRING_ID = 12; //строковий ідентифікатор
    public static final int COMBINED_ID = 13; //комбінований ідентифікатор
    public static final int FUNCTION = 14; //функція
    public static final int FORMULA = 15; //формула
    public static final int STUTEMENT = 16; //вираз
    public static final int AUTOINC = 17; //автоінкремент
    public static final int AUTODEC = 18; //автодекремeнт
    public static final int BOOLEAN = 19; //логічне значення
    public static final int DATE = 20; //ДАТА+
    public static final int PSW = 21; //приховані дані+
    
}
