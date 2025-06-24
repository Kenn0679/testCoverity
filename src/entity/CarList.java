/*
 * To change carlist license header, choose License Headers in Project Properties.
 * To change carlist template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import util.Inputter;

/**
 *
 * @author asus
 */
public class CarList {
    ArrayList<Car> carList = new ArrayList<>();
    private String carID, colour, engineID, frameID;
    private Brand brand;
    Menu menu = new Menu();
    BrandList brandList;
    //constructor: basically create another brand list in car list for unknow fucking reason fpt suck my dick
    public CarList(BrandList blist){
        brandList = blist;
    }
    //getter

    public BrandList getBrandList() {
        return brandList;
    }
    //setter
    public void setBrandList(BrandList brandList) {
        this.brandList = brandList;
    }
    

    //search pos ID hehe
    public int searchByID(String id){
        for(int i = 0; i < carList.size(); i++){
            if(carList.get(i).getCarID().equals(id) || carList.get(i).getFrameID().equals(id)
                ||  carList.get(i).getEngineID().equals(id)){
                return i;
            }
        }
        return -1;
    }
    //searchByID return obj
    public Car searchCarByID(String id){
        int pos = searchByID(id);
        if(pos < 0) return null;
        return carList.get(pos);
    }
    
    /*searchID*/
    //carID
    public String enterCarID(){
        return Inputter.getAString("Enter carID: ", "Unvalid!", "(?i)c\\d{2}").toUpperCase();
    }
    //frameID
    public String enterFrameID(){
        return Inputter.getAString("Enter frameID: ", "Unvalid!", "(?i)f\\d{5}").toUpperCase();
    }
    //engine
    public String enterEngineID(){
        return Inputter.getAString("Enter engineID: ", "Unvalid!", "(?i)e\\d{5}").toUpperCase();
    }
    
    //list car in ascending order
    public void sortList(){
        Collections.sort(carList, new Comparator<Car>() {
            @Override
            public int compare(Car t1, Car t2) {
                return t1.getBrand().getBrandName().compareTo(t2.getBrand().getBrandName());
            }
        });
    }
    public void listCars(){
        sortList();
        int count = 1;
        for (Car item : carList) {
            System.out.println(count+"."+item.screenString());
            count++;
        }
    }
    
    //add brand
    public void addCar(){
        boolean isDup;
        String carID;
        String frameID;
        String engineID;
        //carID not dup
        do{
            isDup = false;
            carID = enterCarID();
            for (Car item : carList) {
                if(item.getCarID().equals(carID)){
                    isDup = true;
                    System.out.println("ID's taken");
                    break;
                }
            }
        }while(isDup);
        //frameID not dup
        do{
            isDup = false;
            frameID = enterFrameID();
            for (Car item : carList) {
                if(item.getFrameID().equals(frameID)){
                    isDup = true;
                    System.out.println("ID's taken");
                    break;
                }
            }
        }while(isDup);
        //engineID not dup
        do {            
            isDup = false;
            engineID = enterEngineID();
            for (Car item : carList) {
                if(item.getEngineID().equals(engineID)){
                    isDup = true;
                    System.out.println("ID's taken");
                    break;
                }
            }
        } while (isDup);
        String colour = Inputter.getAString("Enter a colour: ", "Unvalid").toLowerCase();
        
        Brand b = menu.getObjUserChoice(brandList);
        
        carList.add(new Car(carID, b, colour, frameID, engineID));
        System.out.println("Add successfully");
    }
    
    //public boolean remove car
    public boolean removeCar(){
        String removeCarID = enterCarID();
        Car c =  searchCarByID(removeCarID);
        if(c == null){
            return false;
        }
        carList.remove(c);
        return true;
    }
    //public update
    public boolean updateCar(){
        Car c = searchCarByID(enterCarID());
        if(c == null) return false;
        String updFrame = enterFrameID();
        String updEngine = enterEngineID();
        Brand brand = menu.getObjUserChoice(brandList);
        String color = Inputter.getAString("Enter colour: ", "Unvalid!");
        c.setBrand(brand);
        c.setColor(color);
        c.setEngineID(updEngine);
        c.setFrameID(updFrame);
        return true;
    }
    
    public void printBasedBrandName(){
        String aPartOfBrandName = Inputter.getAString("Enter a part of brand name: ", "Unvalid string");
        int count = 0;
        for (Car item : carList) {
            if(item.getBrand().getBrandName().contains(aPartOfBrandName)){
                System.out.println((count + 1) + ". " + item.screenString());
                count++;
            }
        }
        if(count == 0) System.out.println("No car detected");
    }
    
    public boolean loadFromFile(String url){
        File f = new File(url);
        carList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                String carID = st.nextToken().trim();
                String brandID = st.nextToken().trim();
                String colour = st.nextToken().trim();
                String frameID = st.nextToken().trim();
                String engineID = st.nextToken().trim();
                
                Brand brand = brandList.searchBrandByID(brandList.searchID(brandID));
                
                Car c = new Car(carID, brand, colour, frameID, engineID);
                carList.add(c);
                line = reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Can't load file "+url);
            return false;
        }
    }
    
    public boolean saveToFile(String url){
        File f = new File(url);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            for (Car item : carList) {
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
    
    
}
