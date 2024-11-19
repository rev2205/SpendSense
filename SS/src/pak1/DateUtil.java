/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pak1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author revan
 */
public class DateUtil {
    public static final String[] MONTHS = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    public static Date stringToDate(String DateAsString){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse(DateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String dateToString(Date date){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(date);
    }
    
    public static String getYearAndMonth(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy,MM");
            return df.format(date);
    }
    public static String getMonthName(Integer monthNo){
        return MONTHS[monthNo-1];
    }
    
    public static Integer getYear(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return new Integer(df.format(date));
    }
}
