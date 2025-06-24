/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import util.Inputter;

/**
 *
 * @author asus
 */
public class Menu {
    ArrayList<String> optionList = new ArrayList<>();
    
    //method
    public int getUserChoice(BrandList brandList){
        int count = 1;
        for (Brand item : brandList.brandList) {
            System.out.println(count+". "+brandList.brandList.get(count - 1));
            count++;
        }
        return Inputter.getAnInterger("Enter option: ", "Unvalid option", 0, brandList.brandList.size());
    }
    
    public Brand getObjUserChoice(BrandList brandList){
        int pos = getUserChoice(brandList);
        return brandList.brandList.get(pos - 1);
    }
    
}
