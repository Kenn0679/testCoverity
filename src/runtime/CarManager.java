/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtime;

import entity.Brand;
import entity.BrandList;
import entity.CarList;
import util.Inputter;

/**
 *
 * @author asus
 */
public class CarManager {

    public static void main(String[] args) {
        
        BrandList brandList = new BrandList();
        
        String urlBrand = "C:\\Users\\asus\\School Work\\PRO\\assignment\\BrandList.txt";
        brandList.loadFromFile(urlBrand);
        
        String urlCar = "C:\\Users\\asus\\School Work\\PRO\\assignment\\CarList.txt";
        CarList carList = new CarList(brandList);
        carList.loadFromFile(urlCar);

        
        ui.Menu menu = new ui.Menu("Car manager");
        menu.addOption("List all brands");//1
        menu.addOption("Add a new brand");//2
        menu.addOption("Search a brand based on its ID");//3
        menu.addOption("Update a brand");//4
        menu.addOption("Save brands to the file, brand.txt");//5
        menu.addOption("List all cars in ascending order of brand name");//6
        menu.addOption("List all cars based on a part of an input brand name");//7
        menu.addOption("Add a car");//8
        menu.addOption("Remove a car based on its ID");//9
        menu.addOption("Update a car based on its ID");//10
        menu.addOption("Save cars to the file");//11
        menu.addOption("Exit");
        
        int option;
        do{
            menu.print();
            option = menu.getChoice();
            switch(option){
                case 1:{
                    brandList.showInfor();
                    break;
                }
                case 2:{
                    brandList.addNewBrand();
                    carList.setBrandList(brandList);
                    break;
                }
                case 3:{
                    String newID = Inputter.getAString("Enter ID: ", "Unvalid!", "(?i)^(?i)b[\\w-]+").toUpperCase();
                    Brand brand =  brandList.searchBrandByID(brandList.searchID(newID));
                    if(brand == null) System.out.println("Can't find branđ");
                    else System.out.println(brand.toString());
                    break;
                }
                case 4:{
                    brandList.updateCarByID();
                    carList.setBrandList(brandList);
                    System.out.println("Update complete");
                    break;
                }
                case 5:{
                    brandList.saveToFile(urlBrand);
                    System.out.println("save complete");
                    break;
                }
                case 6:{
                    carList.listCars();
                    break;
                }
                case 7:{
                    carList.printBasedBrandName();
                    break;
                }
                case 8:{
                    carList.addCar();
                    break;
                }
                case 9:{
                    carList.removeCar();
                    System.out.println("Remove done");
                    break;
                }
                case 10:{
                    if(carList.updateCar()){
                        System.out.println("update done");
                    }
                    else{
                        System.out.println("can't update");
                    }
                    break;
                }
                case 11:{
                    if(carList.saveToFile(urlCar)) System.out.println("save done");
                    break;
                }
                case 12:{
                    System.out.println("Exingting....");
                    break;
                }
            }
        }while(option != 12);
        
    }
    
}
