/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import util.Inputter;


public class BrandList{
    //props
    ArrayList<Brand> brandList = new ArrayList<>();
    //constructorr
    
    
    public int searchID(String newID){
        for(int i = 0; i < brandList.size(); i++){
            if(brandList.get(i).getBrandID().equals(newID)) return i;
        }
        return -1;
    }
    
    public Brand searchBrandByID(int pos){
        return pos == -1 ? null : this.brandList.get(pos);
    }
    
    public void updateCarByID(){
        String newID = Inputter.getAString("Enter ID: ", "Unvalid!", "(?i)^(?i)b[\\w-]+").toUpperCase();
        Brand brand = searchBrandByID(this.searchID(newID));
        if(brand == null){
            System.out.println("Can't find Brand");
            return;
        }
        
        String newBrand = Inputter.getAString("Enter car brand: ", "Unvalid brand!");
        String newSoundBand = Inputter.getAString("Enter soundBand: ", "Unvalid band!");
        double newPrice = Inputter.getAnDouble("Enter new price: ", "Unvalid price!", 0);
        brand.setBrandName(newBrand);
        brand.setSoundBrand(newSoundBand);
        brand.setPrice(newPrice);
    }
    
    public void showInfor(){
        for (Brand item : brandList) {
            System.out.println(item.toString());
        }
    }
    
    public void addNewBrand(){
        String newBrandID;
        boolean checkID = true;
        do {            
            checkID = true;
            newBrandID = Inputter.getAString("Enter new brand ID:  ", "Unvalid brand!", "^(?i)b[\\w-]+").toUpperCase();
            for (Brand item : brandList) {
                if(item.getBrandID().equals(newBrandID)){
                    System.out.println("ID is already taken!");
                    checkID = false;
                    break;
                }
            }
        } while (!checkID);
        String newBrandName = Inputter.getAString("Enter new brand name: ", "Unvalid brand name");
        String newSoundBand = Inputter.getAString("Enter new sound band name: ", "Unvalid name!");
        double newPrice = Inputter.getAnDouble("Enter new price: ", "Unvalid price!", 0);
        brandList.add(new Brand(newBrandID, newBrandName, newSoundBand, newPrice));
        System.out.println("Add complete");
    }
    
    public boolean loadFromFile(String url){
        File f = new File(url);
        brandList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                String brandID = st.nextToken().trim();
                String brandName = st.nextToken().trim();
                String soundBandPrice = st.nextToken().trim();
                String split[] = soundBandPrice.split(":");
                String soundBand = split[0].trim();
                double price = Double.parseDouble(split[1]);
                Brand brand = new Brand(brandID, brandName, soundBand, price);
                brandList.add(brand);
                line = reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Can't load file " + url);
            return false;
        }
    }
    
    public boolean saveToFile(String url){
        File f = new File(url);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            for (Brand item : brandList) {
                writer.write(item.toString());
                writer.write("\n");
            }
            writer.flush();
            return true;
        } catch (Exception e) {
            System.out.println("File error " + e);
            return false;
        }
    }
    
    public Brand getUserChoice(){
        Menu menu = new Menu();
        return menu.getObjUserChoice(this);
    }
    
    
    
    
    
    //-------------------------------------------------------------------------------

    
}
