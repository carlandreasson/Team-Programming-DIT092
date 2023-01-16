package Utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateHandler {

    public static DateTimeFormatter format(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter;
    }

    public static ArrayList<LocalDate> getBusinessDaysBetween(LocalDate mStartDate, LocalDate mEndDate){

        List<LocalDate> allDays = new ArrayList<>();
        ArrayList<LocalDate> businessDays = new ArrayList<>();
        while (!mStartDate.isAfter(mEndDate)) {
            allDays.add(mStartDate);
            mStartDate = mStartDate.plusDays(1);
        }
        for (LocalDate day : allDays) {
            if (day.getDayOfWeek() != DayOfWeek.SUNDAY && day.getDayOfWeek() != DayOfWeek.SATURDAY) {
                businessDays.add(day);
            }
        }
        return businessDays;
    }
    public static String getCurrentDate(){
        String currentDate = LocalDateTime.now().format(format());
        return currentDate;
    }
    public static String setDate(){
        int check;
        String day="";
        String year="";
        String month="";
        String date;
        do {
                    System.out.println("Day dd");
                    day = Input.fetchInputString("");

                    System.out.println("Month MM");
                    month = Input.fetchInputString("");
                    System.out.println("Year yyyy");
                    year = Input.fetchInputString("");
            date =  day + "/" + month + "/" + year;
        }while(!checkDate(date));
        return date;
    }

    public static boolean checkDate(String date){
        boolean b = false;
        LocalDate mDate;
       try {
            mDate = LocalDate.parse(date, format());
        if(mDate.isAfter(LocalDate.now())){
            b=true;
        }
       }catch(Exception e) {
           System.out.println(Print.ERROR_INPUT);
       }

        return b;
    }
}
