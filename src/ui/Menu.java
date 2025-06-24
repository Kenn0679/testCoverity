/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import util.Inputter;

/**
 *
 * @author asus
 */
public class Menu {
    //props
    private String title;
    private ArrayList<String> optionList = new ArrayList<>();
    
    //constructor
    public Menu(String title) {
        this.title = title;
    }
    
    //method add option
    public void addOption(String newOption){
        optionList.add(newOption);
    }
    
    //method print 
    public void print(){
        System.out.println("---------"+title+"---------");
        int count = 1;
        for (String item : optionList) {
            System.out.println(count+"."+item);
            count++;
        }
    }
    
    //method getChoice
    public int getChoice(){
        int option = Inputter.getAnInterger("Enter your option(1, "+optionList.size()+"): ", "Unvalid choice!", 1, optionList.size());
        return option;
    }
    
    
    
}
