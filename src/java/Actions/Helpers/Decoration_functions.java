/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Helpers;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author JVega
 */
public class Decoration_functions {
    public static String convert_date_spanish(String the_date)
    {
        //System.out.println("Entro a la función "+the_date);
        /*Locale esLocale = new Locale("es", "ES");//para trabajar en español
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtf = dtf.withLocale(esLocale);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(the_date,dtf);

        int number_day = date.getDayOfMonth();
        String month = date.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
        String day = date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
        int year = date.getYear();
        //System.out.println(day+" "+number_day+" "+month+" "+year);
        //System.out.println(Arrays.toString(data.get(0)));
        String string_fec_autorizacion = day+" "+number_day+" "+month+" "+year;
        //System.out.println(string_fec_autorizacion);
        return string_fec_autorizacion;*/
        return the_date;
    }
}
