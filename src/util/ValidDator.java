
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.StringTokenizer;

/*
là cái khuôn nhưng không dùng để đúc
    - nó chịu trách nhiệm mấy cái hàm check
    - ví dụ
        +check valid date
        +check is date in past
        + ...
 */
public class ValidDator {
    //method isValidDateByFormat
    //.withResolverStyle(ResolverStyle.STRICT)
    //Kiểm tra ngày tháng năm khi nhâp vào có valid với thực tế hay không
    //theo 1 cách nghiêm ngặt nhất(STRICT);
    public static boolean isValidDateByFormat(String date, String format){
        try {
            LocalDate localDate = LocalDate.parse(date, 
                    DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT));            
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean dateInPast(String date, String format) {
        try {
            LocalDate localDate = LocalDate.parse(date,
                        DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT));
            return localDate.compareTo(localDate.now()) < 0;
        } catch (DateTimeParseException e) {
            System.out.println("Unvalid date");
            return false;
        }
    }
    
    public static boolean validName(String name){
        StringTokenizer token = new StringTokenizer(name, " ");
        
        while(token.hasMoreTokens()){
            String str = token.nextToken();
            if(str.contains("/W|[0-9]")){
                return false;
            }
        }
        return true;
    }
}
