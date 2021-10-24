package com.nongskuy.nongskuy;

import java.text.NumberFormat;
import java.util.Locale;

public class Helper {

    public String mataUangRupiah(Integer nominal){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMaximumFractionDigits(0);
        return formatRupiah.format(nominal);
    }

}
