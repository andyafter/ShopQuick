package sg.edu.nus.iss.ussa.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.ussa.exception.DataInputException;

/**
 *
 * @author andy pan
 *
 */
public class Util {

    public static String comma = ",";
    public static String dataForm = "yyyy-MM-dd";
    public static DecimalFormat decForm = new DecimalFormat("0.00");


    public static String testString(String s) throws DataInputException {

        if (s.contains(comma)) {

            throw new DataInputException(s + " contains unexpected char(',')");
        } else {
            return s;
        }

    }


    public static int strToInt(String s) throws DataInputException {

        int result;
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new DataInputException(s + " is not integer");
        }

        return result;

    }


    public static double strToDouble(String s) throws DataInputException {

        double result;

        try {
            result = Double.parseDouble(s);

        } catch (NumberFormatException e) {
            throw new DataInputException(s + " is not float");
        }

        return result;
    }

    public static Date strToDate(String s) throws DataInputException {

        Date result;
        SimpleDateFormat sdf = new SimpleDateFormat(dataForm);

        try {
            result = sdf.parse(s);

        } catch (ParseException e) {
            throw new DataInputException(s + " is not a valid date");
        }

        return result;
    }


    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dataForm);
        return sdf.format(date);
    }


    public static Date dateInc(Date date, int period) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, period);
        return cal.getTime();
    }


    public static String priceForm(double price) {
        return decForm.format(price);
    }

}
