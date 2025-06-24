
package util;


import java.util.Scanner;


/*
    inputer là 1 cái khuôn:
        - Không dùng để đúc
        - Chịut trách nhiệm những hành động nhập (nhập số nguyên, số thực, chuỗi,...)
        - Inputter là người chịu trách nhiệm cho những hành động đó
            + nên là các thứ trong Inputter đều là static
*/

public class Inputter {
    public static Scanner sc = new Scanner(System.in);
    
    //trong này chứa các method hỗ trợ cho việc nhập chuẩn
    
    //method: ép nhập số nguyên
    public static int getAnInterger(String inpMsg, String erMsg){
        
        while (true) {
            System.out.printf(inpMsg);
            try {
                int num = Integer.parseInt(sc.nextLine());
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }
    
    //method ép nhập số nguyên trong khoảng
    public static int getAnInterger(String inpMsg, String erMsg, int lowerBound, int upperBound){
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        while (true) {
            System.out.printf(inpMsg);
            try {
                int num = Integer.parseInt(sc.nextLine());
                if(num < lowerBound || num > upperBound){
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }
    //method ép nhập số nguyên tu 1 diem den vo cung
    public static int getAnInterger(String inpMsg, String erMsg, int lowerBound){
        
        while (true) {
            System.out.printf(inpMsg);
            try {
                int num = Integer.parseInt(sc.nextLine());
                if(num < lowerBound){
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }    
    //method: ép nhập số thực
    public static double getADouble(String inpMsg, String erMsg){
        
        while (true) {
            System.out.printf(inpMsg);
            try {
                double num = Double.parseDouble(sc.nextLine());
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }
    //method ép nhập số thực có giới hạnt từ bao nhiêu tới vô cùng
    public static double getADouble(String inpMsg, String erMsg, double lowerBound){
        while (true) {
            System.out.printf(inpMsg);
            try {
                double num = Double.parseDouble(sc.nextLine());
                if(num < lowerBound) throw new Exception();
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }
    //method ép nhập số thực trong khoảng
    public static double getADouble(String inpMsg, String erMsg, double lowerBound, double upperBound){
        if(lowerBound > upperBound){
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        while (true) {
            System.out.printf(inpMsg);
            try {
                double num = Double.parseDouble(sc.nextLine());
                if(num < lowerBound || num > upperBound){
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }
    //số thực từ lowerbound > vô cùng
    public static double getAnDouble(String inpMsg, String erMsg, double lowerBound){
        while (true) {
            System.out.printf(inpMsg);
            try {
                double num = Double.parseDouble(sc.nextLine());
                if(num < lowerBound){
                    throw new Exception();
                }
                return num;
            } catch (Exception e) {
                System.out.println(erMsg);
            }
        }
    }    
    //method bắt nhập string, cấm rỗng
    public static String getAString(String inpMsg, String errMsg){
        while (true) {
            System.out.printf(inpMsg);            
            try {
                String str = sc.nextLine();
                if(str.isEmpty()){
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }
    //method ép nhập string có cấm rỗng và regex
    public static String getAString(String inpMsg, String errMsg, String regex){
        while (true) {
        System.out.printf(inpMsg);            
            try {
                String str = sc.nextLine();
                if(str.isEmpty() || !str.matches(regex)){
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }
    
    //method boolean
    public static boolean getABoolean(String inpMsg, String errMsg){   
        while (true) {  
            System.out.printf(inpMsg);
            try {
                boolean check = Boolean.parseBoolean(sc.nextLine());
                return check;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }
    
    //method get a date
    //dd-mm-uuuu
    public static String getADate(String inpMsg, String errMsg, String dateFormat){
        
        //check xem valid khong
        //check ngày tháng năm nhuận và không
        do {        
            System.out.printf(inpMsg);
            try {
                String str = sc.nextLine();
                if(str.isEmpty() || !ValidDator.isValidDateByFormat(str, dateFormat)){
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        } while (true);
    }
    
    public static String getAValidName(String inpMsg, String errMsg){
        while (true) {            
            System.out.printf(inpMsg);
            String name = "";
            try {
                name = sc.nextLine();
                name = Inputter.trim(name);
                if(!ValidDator.validName(name)){
                    throw new Exception();
                }
                name = Inputter.upperCaseFirstLetter(name);
                return name;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
        
    }
    
    public static String upperCaseFirstLetter(String str){
        str = str.toLowerCase();
        String temp[] = str.split(" ");
        str = "";
        
        for(int i = 0; i < temp.length; i++){
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if(i < temp.length - 1){
                str += " ";
            }
        }
        
        return str;
    }
    
    public static String trim(String str){
        str = str.trim();
        
        while(str.contains("  ")){
            str = str.replaceAll("  ", " ");
        }
        return str;
    }

}
