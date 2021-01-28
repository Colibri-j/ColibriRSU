/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu.syst;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author учень 1
 * клас містить методи сортування даних
 */
public class Sorted {

    /**
     * Метод сортування файлів за алфавітом
     */
    public static ArrayList<File> sortFilesToArrayList(File[] list) {
        ArrayList<File> start = Ather.copy(list);
        ArrayList<File> end = new ArrayList<File>();
        while(start.size() > 0){
            int indMin = 0;
            char[] fName = Ather.cutTo(start.get(0).getName(), '.').toCharArray();//отримуэмо масив символыв ымены першого файлу
            for(int i = 1; i < start.size(); i++){
                char[] second = Ather.cutTo(start.get(i).getName(), '.').toCharArray();//отримуэмо масив символыв ымены необхідного файлу
                for(int ii = 0; ii < start.size(); ii++){
                    if(second[ii] < fName[ii]){
                        //назва менша
                        indMin = i;
                        fName = second;
                    }
                    else if(second[ii] == fName[ii]){
                        if(i + 1 == fName.length){
                            break;
                        }
                        else if(i + 1 == second.length){
                            //назва менша
                            indMin = i;
                            fName = second;
                        }
                    }
                    else{
                        break;
                    }
                }
            }
            end.add(start.get(indMin));
            start.remove(indMin);
        }
        return end;
    }
        
}
