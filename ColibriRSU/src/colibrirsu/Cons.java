/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colibrirsu;

import java.util.Scanner;
import сolibriVM.CRunner;

/**
 *
 * @author учень 1
 * клас консольної роботи з середовищем Colibri
 */
public class Cons {
    
    public static void entered(){
        Scanner sc = new Scanner(System.in);
        while(true){
            String s = sc.nextLine();
            if(s.equals("#end")){
                break;
            }
            else{
                CRunner.run(s);
            }
        }
    }
    
}
