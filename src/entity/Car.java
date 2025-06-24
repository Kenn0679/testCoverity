/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Car{
    private String carID;
    public Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    
    //getter

    public String getCarID() {
        return carID;
    }

    public Brand getBrand() {
        return brand;
    }


    public String getColor() {
        return color;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }
    //setter

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }
    
    //method
    
    public int compareTo(Car c){
        return this.getBrand().getBrandName().compareTo(c.getBrand().getBrandName());
    }

    @Override
    public String toString () {
        return carID + "," + brand.getBrandID() + "," + color + "," + frameID + "," + engineID;
    }

    
    
    //no idea wtf this shit for
    public String screenString(){
        String str = String.format("%s \n %s %s %s %s", brand.toString(), carID, color, frameID, engineID);
        return str;
    }

}
