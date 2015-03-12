import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class that changes string to date or date to string
 */
public class DateConversion {
    Date date;
    String sDate;

    //converts a date to a string
    public DateConversion(Date date){
        this.date = date;

    }

    //converts string to date
    public DateConversion(String sDate){
        this.sDate = sDate;


    }


    public DateConversion(){

    }

//    public Date stringToDate(String s){
//        Date date;
//
//
//        return date;
//    }
//
//    public String dateToString(Date d){
//        String sDate;
//
//
//        return sDate;
//    }

    public String currentDate(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);

        String currentTime = sdf.format(dt);
        return currentTime;
    }


}
