/*
format gia tien
 */
package DEV;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Format_money {

    public static String format(float num) {
        BigDecimal trieu = new BigDecimal(num);
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmoney = NumberFormat.getCurrencyInstance(vietnam);

        return fmoney.format(trieu);
    }
}
